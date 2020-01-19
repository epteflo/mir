package hu.imsi.mir.utils;


import hu.imsi.mir.common.Message;
import hu.imsi.mir.common.Response;
import hu.imsi.mir.common.ResponseStatus;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ResponseMessageHelper {;

    public static String getCode(final ResponseMessage responseMessage) {
        return responseMessage.getCode();
    }

    public static String getDescription(final ResponseMessage responseMessage) {
        return responseMessage.getDescription();
    }

    public static String getSeverity(final ResponseMessage responseMessage) {
        return responseMessage.getSeverity();
    }

    public static Message convertToMessage(final ResponseMessage responseMessage) {
        return convertToMessage(responseMessage, null, null);
    }

    public static Message convertToMessage(final ResponseMessage responseMessage, final String refElement) {
        return convertToMessage(responseMessage, refElement, null);
    }

    public static Message formatAndConvertToMessage(final ResponseMessage responseMessage,final String refElement,
                                                    final Object refValue) {
        return convertToMessageAndFormatDescription(responseMessage, refElement, refValue,
                refValue != null ? refValue.toString() : null);
    }

    public static Message convertToMessageAndFormatDescription(final ResponseMessage responseMessage,
                                                               final String refElement, final Object refValue,
                                                               String ... descriptionElements) {
        final Message result = convertToMessage(responseMessage, refElement, refValue);
        if (descriptionElements != null && descriptionElements.length > 0) {
            result.setDescription(String.format(responseMessage.getDescription(), descriptionElements));
        }

        return result;
    }

    public static Message convertToMessage(final ResponseMessage responseMessage, final String refElement,
                                           final Object refValue) {
        return convertToMessage(responseMessage, refElement, refValue != null ? refValue.toString() : null, responseMessage.getDescription());
    }

    public static Message convertToMessage(final ResponseMessage responseMessage, final String refElement,
                                           final String refValue, final String description) {
        return createMessage(responseMessage.getCode(), responseMessage.getSeverity(), description, refElement, refValue);
    }

    public static Message createExceptionMessage(final Exception exception) {
        // TODO: Dönteni, hogy toString vagy getMessage
        return createErrorMessage(ResponseMessage.CAUGHT_EXCEPTION, exception.toString());
    }

    public static Message createErrorMessage(final ResponseMessage responseMessage) {
        return createMessage(responseMessage.getCode(), "ERROR", responseMessage.getDescription(), null, null);
    }

    public static Message createErrorMessage(final ResponseMessage responseMessage, final String errorDescription) {
        return createMessage(responseMessage.getCode(), "ERROR", errorDescription, null, null);
    }

    @Deprecated
    public static Message createErrorMessage(final String code, final String errorDescription) {
        return createMessage(code, "ERROR", errorDescription, null, null);
    }

    public static Message createMessage(final String code, final String severity, final String description){
        return createMessage(code, severity, description, null, null);
    }

    public static Message createMessage(final String code, final String severity, final String description, final String refElement, final String refValue){

        final Message result = new Message();
        result.setCode(code);
        result.setDescription(description);
        result.setSeverity(severity);
        result.setRefValue(refValue);
        result.setRefElement(refElement);
        return result;
    }

    public static <T extends Response> void addToResponse(Message message, T entity){
        addToResponse(message, entity.getResponseStatus());
    }

    public static void addToResponse(Message message, ResponseStatus responseStatus){
        if(responseStatus==null){
            responseStatus = new ResponseStatus();
            responseStatus.setMessages(getMessageList(message));
        } else if(CollectionUtils.isEmpty(responseStatus.getMessages())){
            responseStatus.setMessages(getMessageList(message));
        } else {
            responseStatus.getMessages().add(message);
        }
        responseStatus.setCode(1);
    }

    private static List<Message> getMessageList(Message m){
        List<Message> messages = new ArrayList<>();
        messages.add(m);
        return messages;
    }

}
