package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "param",
        propOrder = {"key", "value", "ref"}
)
public class WSParam {
    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String value;
    @XmlElement
    protected String ref;

    public WSParam() {
    }

    public WSParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String value) {
        this.ref = value;
    }
}

