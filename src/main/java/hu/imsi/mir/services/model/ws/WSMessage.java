package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "message",
        propOrder = {
        "severity",
        "code",
        "description",
        "refElement",
        "refValue"
})
public class WSMessage {

    @XmlElement(required = true)
    protected WSSeverity severity;
    @XmlElement(required = true)
    protected String code;
    @XmlElement(required = true)
    protected String description;
    @XmlElement
    protected String refElement;
    @XmlElement
    protected String refValue;


    public WSSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(WSSeverity value) {
        this.severity = value;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }


    public String getRefElement() {
        return refElement;
    }

    public void setRefElement(String value) {
        this.refElement = value;
    }


    public String getRefValue() {
        return refValue;
    }

    public void setRefValue(String value) {
        this.refValue = value;
    }

}
