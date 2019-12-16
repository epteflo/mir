package hu.imsi.mir.services;

import hu.imsi.mir.services.model.inner.*;
import hu.imsi.mir.spring.hibernate.model.HMuseum;
import hu.imsi.mir.util.BeanHelper;

import static hu.imsi.mir.services.ResponseMessage.CAUGHT_EXCEPTION;

public class ServiceHandler {

    public static CreateMuseumResponse createMuseum(CreateMuseumRequest createMuseumRequest){
        CreateMuseumResponse response = new CreateMuseumResponse();
        buildOkResponse(response);
        HMuseum hMuseum = null;

        try {
            hMuseum = BeanHelper.getDbHelper().createMuseum(createMuseumRequest);
        } catch (Exception exception) {
            updateNokResponse(response, ResponseMessageHelper.createExceptionMessage(exception));
        }

        if(hMuseum==null){
            updateNokResponse(response, ResponseMessageHelper.createErrorMessage(CAUGHT_EXCEPTION, "HMuseum objektum null!"));
        }

        return response;

    }

    private static <T extends Response> void buildOkResponse(T response){
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(0);
        response.setStatus(responseStatus);

    }

    private static <T extends Response> void updateNokResponse(T response, Message message){
        ResponseStatus responseStatus = response.getStatus();
        responseStatus.setCode(1);
        responseStatus.getMessages().getMessage().add(message);
    }

}
