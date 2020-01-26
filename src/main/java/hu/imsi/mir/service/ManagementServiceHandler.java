package hu.imsi.mir.service;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Poi;
import hu.imsi.mir.common.Response;
import hu.imsi.mir.common.ResponseStatus;
import hu.imsi.mir.dao.BeaconRepository;
import hu.imsi.mir.dao.LayoutRepository;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.RoomRepository;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.mappers.Converter;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ManagementServiceHandler  {

    @Autowired
    ServiceRegistry serviceRegistry;

    @SuppressWarnings("unchecked")
    public <M extends Response, E> M createEntity(M model) {
        if(!ServiceHelper.validateModel(model)) return model;
        final Class<M> modelClass = (Class<M>) model.getClass();
        final Class<E> entityClass = (Class<E>) serviceRegistry.MODEL_ENTITY_CLASS_MAP.get(modelClass);
        final E entity = serviceRegistry.converterRegistry.getConverter(modelClass, entityClass).map(model);
        final JpaRepository<E, ?> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
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
        final MuseumRepository museumRepository = (MuseumRepository) serviceRegistry.REPOSITORY_MAP.get(HMuseum.class);
        Optional<HMuseum> museum = museumRepository.findById(museumId);
        if(museum.isPresent()){
            HMuseum hMuseum = museum.get();
            List<HRoom> rooms = hMuseum.getRooms();
            if(!CollectionUtils.isEmpty(rooms)) {
                List<Poi> pois = new ArrayList<>();
                for (HRoom room : rooms) {
                    List<Poi> roomPois = getPoisByRoom(room);
                    if(roomPois!=null) pois.addAll(roomPois);
                }
                return pois;
            }
        }
        return null;
    }

    public List<Poi> getPoisByRoomId(Integer roomId){
        final RoomRepository roomRepository = (RoomRepository) serviceRegistry.REPOSITORY_MAP.get(HRoom.class);
        Optional<HRoom> room = roomRepository.findById(roomId);
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
}
