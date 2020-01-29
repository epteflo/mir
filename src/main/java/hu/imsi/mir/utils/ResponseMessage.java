package hu.imsi.mir.utils;

import hu.imsi.mir.common.Message;

public enum ResponseMessage {
    MUSEUM_NAME_EMPTY                   ("MIR_E0001", "A múzeum neve nem lehet üres!", "ERROR"),
    ROOM_NAME_EMPTY                     ("MIR_E0002", "A szoba neve nem lehet üres!", "ERROR"),
    ROOM_MUSEUM_ID_EMPTY                ("MIR_E0003", "A szobához kötelező megadni múzeum azonosítót!", "ERROR"),
    ROOM_MUSEUM_NOT_EXISTS              ("MIR_E0004", "A szobához megadott múzeum nem létezik!", "ERROR"),
    DOOR_ROOM_A_ID_EMPTY                ("MIR_E0005", "Az ajtóhoz kötelező megadni 'A' szoba azonosítót!", "ERROR"),
    DOOR_ROOM_B_ID_EMPTY                ("MIR_E0006", "Az ajtóhoz kötelező megadni 'B' szoba azonosítót!", "ERROR"),
    DOOR_ROOM_A_NOT_EXISTS              ("MIR_E0007", "Az ajtóhoz megadott 'A' szoba nem létezik!", "ERROR"),
    DOOR_ROOM_B_NOT_EXISTS              ("MIR_E0008", "Az ajtóhoz megadott 'B' szoba nem létezik!", "ERROR"),
    EXHIBITION_MUSEUM_ID_EMPTY          ("MIR_E0009", "A tárlathoz kötelező megadni múzeum azonosítót!", "ERROR"),
    EXHIBITION_MUSEUM_NOT_EXISTS        ("MIR_E0010", "A tárlathoz megadott múzeum nem létezik!", "ERROR"),
    EXHIBITION_NAME_EMPTY               ("MIR_E0011", "A tárlat neve nem lehet üres!", "ERROR"),
    POI_NAME_EMPTY                      ("MIR_E0012", "A poi neve nem lehet üres!", "ERROR"),
    POI_TYPE_EMPTY                      ("MIR_E0013", "A poi típusa nem lehet üres!", "ERROR"),
    ENTITY_NOT_DELETABLE                ("MIR_E0100", "Az entitás nem törölhető, hivatkozás van rá!", "ERROR"),
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
