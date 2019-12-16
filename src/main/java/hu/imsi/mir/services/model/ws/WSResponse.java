package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlElement;

public abstract class WSResponse {

    protected WSResponseStatus status;
    protected WSParams params;

    @XmlElement(required = true)
    public WSResponseStatus getStatus() {
        return status;
    }

    public void setStatus(WSResponseStatus status) {
        this.status = status;
    }

    @XmlElement
    public WSParams getParams() {
        return params;
    }
    public void setParams(WSParams params) {
        this.params = params;
    }

}
