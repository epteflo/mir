package hu.imsi.mir.services;

import hu.imsi.mir.services.model.inner.Message;
import hu.imsi.mir.services.model.inner.Severity;

import static hu.imsi.mir.services.ResponseMessage.CAUGHT_EXCEPTION;

public enum ResponseMessageHelper {;

    public static String getCode(final ResponseMessage responseMessage) {
        return responseMessage.getCode();
    }

    public static String getDescription(final ResponseMessage responseMessage) {
        return responseMessage.getDescription();
    }

    public static Severity getSeverity(final ResponseMessage responseMessage) {
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
        return createErrorMessage(CAUGHT_EXCEPTION, exception.toString());
    }

    public static Message createErrorMessage(final ResponseMessage responseMessage) {
        return createMessage(responseMessage.getCode(), Severity.ERROR, responseMessage.getDescription(), null, null);
    }

    public static Message createErrorMessage(final ResponseMessage responseMessage, final String errorDescription) {
        return createMessage(responseMessage.getCode(), Severity.ERROR, errorDescription, null, null);
    }

    @Deprecated
    public static Message createErrorMessage(final String code, final String errorDescription) {
        return createMessage(code, Severity.ERROR, errorDescription, null, null);
    }

    public static Message createMessage(final String code, final Severity severity, final String description){
        return createMessage(code, severity, description, null, null);
    }

    public static Message createMessage(final String code, final Severity severity, final String description, final String refElement, final String refValue){

        final Message result = new Message();
        result.setCode(code);
        result.setDescription(description);
        result.setSeverity(severity);
        result.setRefValue(refValue);
        result.setRefElement(refElement);
        return result;
    }

}
