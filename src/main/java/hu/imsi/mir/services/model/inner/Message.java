package hu.imsi.mir.services.model.inner;

import hu.imsi.mir.services.model.ws.WSSeverity;

public class Message {


    protected Severity severity;

    protected String code;

    protected String description;

    protected String refElement;

    protected String refValue;

    public Message() {
    }

    public Message(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Message(String code, String description, String refElement, String refValue) {
        this.code = code;
        this.description = description;
        this.refElement = refElement;
        this.refValue = refValue;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity value) {
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
