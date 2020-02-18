package hu.imsi.mir.service;

import hu.imsi.mir.common.*;
import hu.imsi.mir.dao.BeaconRepository;
import hu.imsi.mir.dao.LayoutRepository;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.RoomRepository;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.mappers.Converter;
import hu.imsi.mir.utils.BeanHelper;
import hu.imsi.mir.utils.ServiceHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ManagementServiceHandler  {
    @Value("${mir.fs.root:null}") String baseDir;

    final static Logger logger = LogManager.getLogger(ManagementServiceHandler.class);

    @Autowired
    ServiceRegistry serviceRegistry;

    @SuppressWarnings("unchecked")
    public <M extends Response, E> M createEntity(M model) {
        if(!ServiceHelper.validateModel(model)) return model;
        final Class<M> modelClass = (Class<M>) model.getClass();
        final Class<E> entityClass = (Class<E>) serviceRegistry.MODEL_ENTITY_CLASS_MAP.get(modelClass);
        final E entity = serviceRegistry.converterRegistry.getConverter(modelClass, entityClass).map(model);
        final JpaRepository<E, ?> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        //prepareEntityBeforeSaveAndFlush(entity);
        final E stored = repository.saveAndFlush(entity);
        return serviceRegistry.converterRegistry.getConverter(entityClass, modelClass).map(stored);
    }

    @SuppressWarnings("unchecked")
    public <M, E, ID> Optional<M> updateEntity(ID id, M model) {
        final Class<M> modelClass = (Class<M>) model.getClass();
        final Class<E> entityClass = (Class<E>) serviceRegistry.MODEL_ENTITY_CLASS_MAP.get(modelClass);
        final JpaRepository<E, ID> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        final Optional<E> entity = repository.findById(id);
        if (!entity.isPresent()) return Optional.empty();
        final E e = entity.get();
        serviceRegistry.converterRegistry.getConverter(modelClass, entityClass).merge(model, e);
        return Optional.of(serviceRegistry.converterRegistry.getConverter(entityClass, modelClass).map(repository.saveAndFlush(e)));
    }

    @SuppressWarnings("unchecked")
    public <M extends Response, E, ID> Optional<M> getModel(ID entityId, final Class<M> modelClass) {
        final Class<E> entityClass = (Class<E>) serviceRegistry.MODEL_ENTITY_CLASS_MAP.get(modelClass);
        final JpaRepository<E, ID> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        final Converter<E, M> converter = serviceRegistry.converterRegistry.getConverter(entityClass, modelClass);
        return repository.findById(entityId).map(converter::map);
    }

    @SuppressWarnings("unchecked")
    public <M extends Response, E> List<M> getModels(ExampleMatcher exampleMatcher, E example){
        final Class<E> entityClass = (Class<E>) example.getClass();
        final Class<M> modelClass = (Class<M>) serviceRegistry.ENTITY_MODEL_CLASS_MAP.get(entityClass);
        final JpaRepository<E, ?> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        return serviceRegistry.converterRegistry.getConverter(entityClass, modelClass).mapList(repository.findAll(Example.of(example, exampleMatcher)));
    }

    @SuppressWarnings("unchecked")
    public <M extends Response, E, ID> Optional<M> deleteEntity(ID entityId, final Class<M> modelClass) {
        final Class<E> entityClass = (Class<E>) serviceRegistry.MODEL_ENTITY_CLASS_MAP.get(modelClass);
        final JpaRepository<E, ID> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        final Converter<E, M> converter = serviceRegistry.converterRegistry.getConverter(entityClass, modelClass);
        final Optional<E> entity = repository.findById(entityId);
        if(!entity.isPresent()) return Optional.empty();
        ResponseStatus responseStatus = ServiceHelper.validateDeleteEntity(entity.get());
        if(responseStatus!=null) {
            M model = converter.map(entity.get());
            model.setResponseStatus(responseStatus);
            return Optional.of(model);
        }
        entity.ifPresent(repository::delete);
        return entity.map(converter::map);
    }

    @SuppressWarnings("unchecked")
    public <E, ID> E getEntityById(ID entityId, final Class<E> entityClass){
        final JpaRepository<E, ID> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        final Optional<E> entity = repository.findById(entityId);
        if(entity.isPresent()){
           return entity.get();
        };
        return null;
    }

    public boolean saveMultipartFileByUUID(MultipartFile file, String uuid){

        Content content = getContentByUUID(uuid);
        if(content==null) return false;

        String filePath = saveMultipartFile(file);

        content.setFileName(file.getName());
        content.setInternalUrl(filePath);

        updateEntity(content.getId(), content);

        return true;
    }

    public String saveMultipartFile(MultipartFile file){
        try {
            String fileName = ServiceHelper.generateSum(file.getBytes());
            Path filePath = Paths.get(baseDir.toString(), fileName);

            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(file.getBytes());
            }
            return filePath.toString();
        }catch (Exception e){
            logger.error("Exception at saveMultipartFile method :",e);
            return null;
        }
    }

    public Resource loadFileByUUID(String uuid){
        Content content = getContentByUUID(uuid);
        if(content==null) return null;
        try {
            Path filePath = Paths.get(content.getInternalUrl());

            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            } else {
                logger.error("File not found: " + filePath.getFileName());
                return null;
            }
        } catch (MalformedURLException ex) {
            logger.error("File not found for uuid!");
            return null;
        }

    }

    //****************************
    //    Specific functions
    //****************************

    public Museum getMuseumByBeaconUUID(String uuid) {
        final BeaconRepository beaconJpaRepository = (BeaconRepository) serviceRegistry.REPOSITORY_MAP.get(HBeacon.class);
        HBeacon beacon = beaconJpaRepository.findByUuidEquals(uuid);
        if (beacon != null) {
            final LayoutRepository layoutRepository = (LayoutRepository) serviceRegistry.REPOSITORY_MAP.get(HLayout.class);
            HLayout layout = layoutRepository.findByBeaconEquals(beacon);
            if(layout != null) {
                return serviceRegistry.converterRegistry.getConverter(HMuseum.class, Museum.class).map(layout.getRoom().getMuseum());
            }
        }

        return null;
    }



    public List<Poi> getPoisByMuseumId(Integer museumId){
        List<HRoom> rooms = getRoomsByMuseumId(museumId);
        if(!CollectionUtils.isEmpty(rooms)) {
            List<Poi> pois = new ArrayList<>();
            for (HRoom room : rooms) {
                List<Poi> roomPois = getPoisByRoom(room);
                if (roomPois != null) pois.addAll(roomPois);
            }
            return pois;
        }
        return null;
    }

    public List<Poi> getPoisByRoomId(Integer roomId){
        Optional<HRoom> room = findRoomById(roomId);
        if(room.isPresent()){
            return getPoisByRoom(room.get());
        }
        return null;
    }

    private List<Poi> getPoisByRoom(HRoom room){
        List<HLayout> layouts = room.getLayouts();
        if(!CollectionUtils.isEmpty(layouts)){
            List<Poi> pois = new ArrayList<>();
            for(HLayout layout : layouts){
                if(layout.getPoi()!=null){
                    pois.add(serviceRegistry.converterRegistry.getConverter(HPoi.class, Poi.class).map(layout.getPoi()));
                }
            }
            return pois;
        }
        return null;
    }

    public Poi getPoiByBeaconUUID(String uuid) {
        final BeaconRepository beaconJpaRepository = (BeaconRepository) serviceRegistry.REPOSITORY_MAP.get(HBeacon.class);
        HBeacon beacon = beaconJpaRepository.findByUuidEquals(uuid);
        if (beacon != null) {
            final LayoutRepository layoutRepository = (LayoutRepository) serviceRegistry.REPOSITORY_MAP.get(HLayout.class);
            HLayout layout = layoutRepository.findByBeaconEquals(beacon);
            if(layout != null && layout.getPoi()!=null) {
                return serviceRegistry.converterRegistry.getConverter(HPoi.class, Poi.class).map(layout.getPoi());
            }
        }
        return null;
    }



    public List<Layout> getLayoutsByMuseumId(Integer museumId){
        List<HRoom> rooms = getRoomsByMuseumId(museumId);
        if(!CollectionUtils.isEmpty(rooms)) {
            List<Layout> layouts = new ArrayList<>();
            for (HRoom room : rooms) {
                layouts.addAll(getLayoutsByRoom(room));
            }
            return layouts;
        }
        return null;
    }

    public List<Layout> getLayoutsByRoomId(Integer roomId){
        Optional<HRoom> room = findRoomById(roomId);
        if(room.isPresent()){
            return getLayoutsByRoom(room.get());
        }
        return null;
    }

    public List<Layout> getLayoutsByRoom(HRoom room){
        List<HLayout> roomLayouts = room.getLayouts();
        return serviceRegistry.converterRegistry.getConverter(HLayout.class, Layout.class).mapList(roomLayouts);
    }

    private List<HRoom> getRoomsByMuseumId(Integer museumId){
        final MuseumRepository museumRepository = (MuseumRepository) serviceRegistry.REPOSITORY_MAP.get(HMuseum.class);
        Optional<HMuseum> museum = museumRepository.findById(museumId);
        if(museum.isPresent()) {
            HMuseum hMuseum = museum.get();
            return hMuseum.getRooms();
        }
        return null;
    }

    private Optional<HRoom> findRoomById(Integer roomId){
        final RoomRepository roomRepository = (RoomRepository) serviceRegistry.REPOSITORY_MAP.get(HRoom.class);
         return roomRepository.findById(roomId);
    }

    public Layout getLayoutByBeaconUUID(String uuid) {
        final BeaconRepository beaconJpaRepository = (BeaconRepository) serviceRegistry.REPOSITORY_MAP.get(HBeacon.class);
        HBeacon beacon = beaconJpaRepository.findByUuidEquals(uuid);
        if (beacon != null) {
            final LayoutRepository layoutRepository = (LayoutRepository) serviceRegistry.REPOSITORY_MAP.get(HLayout.class);
            HLayout layout = layoutRepository.findByBeaconEquals(beacon);
            if(layout != null) {
                return serviceRegistry.converterRegistry.getConverter(HLayout.class, Layout.class).map(layout);
            }
        }
        return null;
    }

    public Content getContentByUUID(String uuid){
        final HContent example = new HContent();
        example.setUuid(uuid);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("uuid", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());

        final JpaRepository<HContent, ?> repository = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HContent.class);
        Optional<HContent> content = repository.findOne(Example.of(example, matcher));
        if(content.isPresent()) return serviceRegistry.converterRegistry.getConverter(HContent.class, Content.class).map(content.get());
        return null;
    }


    private <E> void prepareEntityBeforeSaveAndFlush(E entity){
        if(entity instanceof HExhibitionTour){
            for(HExhibitionTourLayout exhibitionTourLayout : ((HExhibitionTour) entity).getExhibitionTourLayouts()){
                if(exhibitionTourLayout.getExhibitionTour()==null) exhibitionTourLayout.setExhibitionTour((HExhibitionTour)entity);
            }
        }
    }

}
