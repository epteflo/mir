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

    DOOR_NOT_AT_THE_WALL_PATH           ("MIR_E0200", "Az ajtó nem a fall vonalán van!", "ERROR"),
    X_COORD_MISSING                     ("MIR_E0201", "Hiányzik az entitáshoz az X koordináta!", "ERROR"),
    Y_COORD_MISSING                     ("MIR_E0202", "Hiányzik az entitáshoz az Y koordináta!", "ERROR"),
    X_COORD_RECOMMENDED                 ("MIR_E0203", "Az entitáshoz az X koordináta megadása javasolt!", "WARNING"),
    Y_COORD_RECOMMENDED                 ("MIR_E0204", "Az entitáshoz az Y koordináta megadása javasolt!", "WARNING"),
    X_SIZE_MISSING                      ("MIR_E0201", "Hiányzik az entitáshoz az X koordináta méret!", "ERROR"),
    Y_SIZE_MISSING                      ("MIR_E0202", "Hiányzik az entitáshoz az Y koordináta!", "ERROR"),


    EXHIBITION_MUSEUM_ID_EMPTY          ("MIR_E0009", "A tárlathoz kötelező megadni múzeum azonosítót!", "ERROR"),
    EXHIBITION_MUSEUM_NOT_EXISTS        ("MIR_E0010", "A tárlathoz megadott múzeum nem létezik!", "ERROR"),
    EXHIBITION_NAME_EMPTY               ("MIR_E0011", "A tárlat neve nem lehet üres!", "ERROR"),
    POI_NAME_EMPTY                      ("MIR_E0012", "A poi neve nem lehet üres!", "ERROR"),
    POI_TYPE_EMPTY                      ("MIR_E0013", "A poi típusa nem lehet üres!", "ERROR"),

    LAYOUT_ROOM_ID_EMPTY                ("MIR_E0013", "Az elrendezéshez kötelező megadni szoba azonosítót!", "ERROR"),
    LAYOUT_ROOM_NOT_EXISTS              ("MIR_E0014", "Az elrendezéshez megadott szoba nem létezik!", "ERROR" ),
    LAYOUT_BEACON_ID_EMPTY              ("MIR_E0015", "Az elrendezéshez nem adott beacon azonosítót!", "WARNING"),
    LAYOUT_BEACON_NOT_EXISTS            ("MIR_E0016", "Az elrendezéshez megadott beacon nem létezik!", "ERROR" ),
    LAYOUT_POI_ID_EMPTY                 ("MIR_E0017", "Az elrendezéshez kötelező megadni poi azonosítót!", "ERROR"),
    LAYOUT_POI_NOT_EXISTS               ("MIR_E0018", "Az elrendezéshez megadott poi nem létezik!", "ERROR" ),
    LAYOUT_EXHIBITION_ID_EMPTY          ("MIR_E0019", "Az elrendezéshez nem adott tárlat azonosítót!", "WARNING"),
    LAYOUT_EXHIBITION_NOT_EXISTS        ("MIR_E0020", "Az elrendezéshez megadott tárlat nem létezik!", "ERROR" ),
    LAYOUT_BEACON_ALREADY_PAIRED        ("MIR_E0021", "A beacon már hozzá lett rendelve egy másik elrendezéshez!", "ERROR" ),
    LAYOUT_COORDS_OUT_OF_ROOM           ("MIR_E0022", "Az elrendezéshez megadott koordináta túlmutat szoba határain!", "ERROR" ),
    LAYOUT_EXHIBITION_AND_ROOM_MUSEUM_NOT_EQUALS           ("MIR_E0023", "Az elrendezéshez megadott tárlat múzeum azonosítója és az elrendezéshez adott szoba múzeum azonosítója nem egyezik!", "ERROR" ),

    EXHTOUR_EXHIBITION_ID_EMPTY         ("MIR_E0025", "A tárlat vezetéshez kötelező megadni a tárlat azonosítót!", "ERROR"),
    EXHTOUR_EXHIBITION_NOT_EXISTS       ("MIR_E0026", "A tárlat vezetéshez megadott tárlat nem létezik!", "ERROR" ),

    EXHTOUR_LAYOUT_NOT_EXISTS           ("MIR_E0027", "A tárlat vezetéshez megadott elrendezés nem létezik!", "ERROR" ),
    EXHTOUR_LAYOUT_MUSEUM_AND_EXHIBITON_MUSEUM_NOT_EXISTS   ("MIR_E0028", "A tárlat vezetéshez megadott elrendezés múzeum azonosítója nem egyezik meg a tárlathoz megadott múzeum azonosítójával!", "ERROR" ),

    CONTENT_NAME_EMPTY                  ("MIR_E0029", "A tartalom neve nem lehet üres!", "ERROR"),

    CONTENT_OBJECT_EMPTY                ("MIR_E0041", "A tartalom hozzárendelés üresen nem állhat, valamilyen objektumhoz hozzá kell rendelni!", "ERROR"),
    CONTENT_OBJECT_ROOM_NOT_EXISTS      ("MIR_E0042", "A tartalom hozzárendeléshez megadott szoba nem létezik!", "ERROR"),
    CONTENT_OBJECT_POI_NOT_EXISTS       ("MIR_E0043", "A tartalom hozzárendeléshez megadott poi nem létezik!", "ERROR"),
    CONTENT_OBJECT_MUSEUM_NOT_EXISTS    ("MIR_E0044", "A tartalom hozzárendeléshez megadott múzeum nem létezik!", "ERROR"),
    CONTENT_OBJECT_MUSEUM_AND_ROOM_MUSEUM_NOT_EQUALS    ("MIR_E0045", "A tartalom hozzárendeléshez megadott múzeum azonosító és szobához tartozó múzeum azonosító nem egyezik!", "ERROR"),

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
