package hu.imsi.mir.services.model.inner;

import hu.imsi.mir.services.model.ws.WSSeverity;

public class Message {


    protected Severity severity;

    protected String code;

    protected String description;

    protected String refElement;

    protected String refValue;


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
