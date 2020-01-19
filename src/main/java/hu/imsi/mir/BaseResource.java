package hu.imsi.mir;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Response;
import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dto.RsMuseum;
import hu.imsi.mir.dto.RsResponse;
import hu.imsi.mir.service.ManagementServiceHandler;
import hu.imsi.mir.service.ServiceRegistry;
import hu.imsi.mir.utils.EntityAction;
import hu.imsi.mir.utils.LoggerServiceHandler;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

import static hu.imsi.mir.utils.Constants.SERVICE_CALLED;


public class BaseResource {

    @Autowired
    private ManagementServiceHandler managementServiceHandler;

    @Autowired
    private LoggerServiceHandler loggerServiceHandler;

    @Autowired
    ServiceRegistry serviceRegistry;

    //General functions

    public <D extends RsResponse,M extends Response> ResponseEntity<D> createEntity(D dto, final Class<M> m, final String userName, final String method) {
        loggerServiceHandler.logStart(userName,SERVICE_CALLED, this.getClass().getName(), method);
        final Class<D> dtoClass = (Class<D>) dto.getClass();
        final M model = serviceRegistry.converterRegistry.getConverter(dtoClass, m).map(dto);
        final M storedModel = managementServiceHandler.createEntity(model);
        final D responseDto = serviceRegistry.converterRegistry.getConverter(m, dtoClass).map(storedModel);
        return ServiceHelper.createResponse(responseDto);
    }

    public <D extends RsResponse,M extends Response> ResponseEntity<D> getModel(final Class<D> d, final Class<M> m, final String userName, final Integer id, final String method) {
        return actionEntity(d,m,userName,id,method,EntityAction.GET);
    }

    public <D extends RsResponse,M extends Response> ResponseEntity<D> deleteEntity(final Class<D> d, final Class<M> m, final String userName, final Integer id, final String method) {
        return actionEntity(d,m,userName,id,method,EntityAction.DELETE);
    }

    public <D extends RsResponse,M extends Response> ResponseEntity<D> updateEntity(D dto, final Class<M> m, final String userName, final Integer id, final String method) {
        loggerServiceHandler.logStart(userName, SERVICE_CALLED, this.getClass().getName(), method);
        final Class<D> dtoClass = (Class<D>) dto.getClass();
        final M model = serviceRegistry.converterRegistry.getConverter(dtoClass, m).map(dto);
        final Optional<M> updatedModel = managementServiceHandler.updateEntity(id, model);
        if (!updatedModel.isPresent()) return ResponseEntity.notFound().build();
        final D responseDto = serviceRegistry.converterRegistry.getConverter(m, dtoClass).map(updatedModel.get());
        return ServiceHelper.createResponse(responseDto);
    }

    public <C extends Collection, D extends RsResponse, M extends Response,E> ResponseEntity<C> getModels(ExampleMatcher exampleMatcher, E example, final Class<M> m, final Class<D> d, final String userName, final String method){
        loggerServiceHandler.logStart(userName, SERVICE_CALLED, this.getClass().getName(), method);
        return ServiceHelper.createResponse(serviceRegistry.converterRegistry.getConverter(m,d).mapList(managementServiceHandler.getModels(exampleMatcher, example)));
    }

    public <E,ID> E getEntityById(ID id, final Class<E> entityClass){
        if(id==null) return null;
        return managementServiceHandler.getEntityById(id, entityClass);
    }


    private <D extends RsResponse,M extends Response> ResponseEntity<D> actionEntity(final Class<D> d, final Class<M> m, final String userName, final Integer id, final String method, final EntityAction entityAction) {
        loggerServiceHandler.logStart(userName, SERVICE_CALLED, this.getClass().getName(), method);
        final Optional<M> storedModel = EntityAction.DELETE.equals(entityAction) ? managementServiceHandler.deleteEntity(id, m) : managementServiceHandler.getModel(id, m);
        if (!storedModel.isPresent()) return ResponseEntity.notFound().build();
        final D responseDto = serviceRegistry.converterRegistry.getConverter(m, d).map(storedModel.get());
        return ServiceHelper.createResponse(responseDto);
    }

    //Specific functions - MUSEUM
    public ResponseEntity<RsMuseum> getMuseumByBeaconUUID(String uuid, String userName){
        loggerServiceHandler.logStart(userName, SERVICE_CALLED, this.getClass().getName(), "getMuseumByBeaconUUID");
        return ServiceHelper.createResponse(serviceRegistry.converterRegistry.getConverter(Museum.class, RsMuseum.class).map(managementServiceHandler.getMuseumByBeaconUUID(uuid)));
    }

}
