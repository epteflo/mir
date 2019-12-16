package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "severity")
@XmlEnum
public enum WSSeverity {

    ERROR,
    WARNING,
    INFO;

    public String value() {
        return name();
    }

    public static WSSeverity fromValue(String v) {
        return valueOf(v);
    }

}
