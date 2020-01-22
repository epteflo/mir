package hu.imsi.mir.service;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Response;
import hu.imsi.mir.common.ResponseStatus;
import hu.imsi.mir.dao.BeaconRepository;
import hu.imsi.mir.dao.LayoutRepository;
import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dao.entities.HLayout;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.mappers.Converter;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

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

    //Museum specific functions
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
}
