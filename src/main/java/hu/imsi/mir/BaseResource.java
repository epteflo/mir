package hu.imsi.mir;

import hu.imsi.mir.common.Response;
import hu.imsi.mir.dto.RsResponse;
import hu.imsi.mir.service.ManagementServiceHandler;
import hu.imsi.mir.service.ServiceRegistry;
import hu.imsi.mir.utils.LoggerServiceHandler;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static hu.imsi.mir.utils.Constants.SERVICE_CALLED;


public class BaseResource {

    @Autowired
    private ManagementServiceHandler managementServiceHandler;

    @Autowired
    private LoggerServiceHandler loggerServiceHandler;

    @Autowired
    ServiceRegistry serviceRegistry;


    public <D extends RsResponse,E extends Response> ResponseEntity<D> createEntity(D dto, final Class<E> e, final String userName, final String method) {
        loggerServiceHandler.logStart(userName,SERVICE_CALLED, this.getClass().getName(), method);
        final Class<D> dtoClass = (Class<D>) dto.getClass();
        final E entity = serviceRegistry.converterRegistry.getConverter(dtoClass, e).map(dto);
        final E storedEntity = managementServiceHandler.createEntity(entity);
        final D responseDto = serviceRegistry.converterRegistry.getConverter(e, dtoClass).map(storedEntity);
        return ServiceHelper.createResponse(responseDto);
    }

}
