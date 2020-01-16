package hu.imsi.mir.utils;

import hu.imsi.mir.common.Message;

public enum ResponseMessage {
    MUSEUM_NAME_EMPTY                   ("MIR_E0001", "A múzeum neve nem lehet üres!", "ERROR"),
    ROOM_NAME_EMPTY                     ("MIR_E0002", "A szoba neve nem lehet üres!", "ERROR"),
    ROOM_MUSEUM_ID_EMPTY                ("MIR_E0003", "A szobához kötelező megadni múzeum azonosítót!", "ERROR"),
    ROOM_MUSEUM_NOT_EXISTS              ("MIR_E0004", "A szobához megadott múzeum nem létezik!", "ERROR"),
    CAUGHT_EXCEPTION                    ("INTERNAL_ERROR", "Belső hiba történt", "ERROR")
    ;

    private String code;
    private String description;
    private String severity;

    ResponseMessage(String code, String description, String severity) {
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

    public String getSeverity() {
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
