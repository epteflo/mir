package hu.imsi.mir.services;

import hu.imsi.mir.services.model.inner.Message;
import hu.imsi.mir.services.model.inner.Severity;

public enum ResponseMessage {
    MUSEUM_NAME_EMPTY                   ("MIR_E0001", "A múzeum neve nem lehet üres!", Severity.ERROR),
    CAUGHT_EXCEPTION                    ("INTERNAL_ERROR", "Belső hiba történt", Severity.ERROR)
    ;

    private String code;
    private String description;
    private Severity severity;

    ResponseMessage(String code, String description, Severity severity) {
        this.code = code;
        this.description = description;
        this.severity = severity;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Message convertToMessage() {
        return ResponseMessageHelper.convertToMessage(this);
    }

    public Message convertToMessage(final String refElement) {
        return ResponseMessageHelper.convertToMessage(this, refElement);
    }

    public Message formatAndConvertToMessage(final String refElement, final String refValue) {
        return ResponseMessageHelper.formatAndConvertToMessage(this, refElement, refValue);
    }

    public Message formatAndConvertToMessage(final String refElement, final String refValue, String ... descriptionRefs) {
        return ResponseMessageHelper.convertToMessageAndFormatDescription(this, refElement, refValue, descriptionRefs);
    }

    public Message convertToMessage(final String refElement, final String refValue) {
        return ResponseMessageHelper.convertToMessage(this, refElement, refValue);
    }

    public Message convertToMessage(final String refElement, final String refValue, final String description) {
        return ResponseMessageHelper.convertToMessage(this, refElement, refValue, description);
    }

}
