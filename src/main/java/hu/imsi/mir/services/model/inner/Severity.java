package hu.imsi.mir.services.model.inner;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

public enum Severity {

    ERROR,
    WARNING,
    INFO;

    public String value() {
        return name();
    }

    public static Severity fromValue(String v) {
        return valueOf(v);
    }

}
