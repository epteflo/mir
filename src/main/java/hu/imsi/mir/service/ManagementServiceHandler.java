package hu.imsi.mir.service;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Room;
import hu.imsi.mir.mappers.Converter;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ManagementServiceHandler  {

    @Autowired
    ServiceRegistry serviceRegistry;

    @SuppressWarnings("unchecked")
    private <M, E> M createEntity(M model) {
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
    public <M, E, ID> Optional<M> getModel(ID entityId, final Class<M> modelClass) {
        final Class<E> entityClass = (Class<E>) serviceRegistry.MODEL_ENTITY_CLASS_MAP.get(modelClass);
        final JpaRepository<E, ID> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        final Converter<E, M> converter = serviceRegistry.converterRegistry.getConverter(entityClass, modelClass);
        return repository.findById(entityId).map(converter::map);
    }

    @SuppressWarnings("unchecked")
    public <M, E, ID> Optional<M> deleteEntity(ID entityId, final Class<M> modelClass) {
        final Class<E> entityClass = (Class<E>) serviceRegistry.MODEL_ENTITY_CLASS_MAP.get(modelClass);
        final JpaRepository<E, ID> repository = serviceRegistry.REPOSITORY_MAP.get(entityClass);
        final Converter<E, M> converter = serviceRegistry.converterRegistry.getConverter(entityClass, modelClass);
        final Optional<E> entity = repository.findById(entityId);
        entity.ifPresent(repository::delete);
        return entity.map(converter::map);
    }

    public Museum createMuseum(Museum museum) {
        if (!ServiceHelper.validateMuseum(museum)) return museum;
        return createEntity(museum);
    }

    public Optional<Museum> updateMuseum(Integer id, Museum museum) {
        return updateEntity(id, museum);
    }

    public Optional<Museum> getMuseum(Integer id) {
        return getModel(id, Museum.class);
    }

    public Optional<Museum> deleteMuseum(Integer id) {
        return deleteEntity(id, Museum.class);
    }


    public Room createRoom(Room room) {
        return createEntity(room);
    }

}
