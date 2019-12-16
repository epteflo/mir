package hu.imsi.mir.services.model.inner;

import hu.imsi.mir.services.model.ws.WSParams;
import hu.imsi.mir.services.model.ws.WSResponseStatus;

import javax.xml.bind.annotation.XmlElement;

public abstract class Response {

    protected ResponseStatus status;
    protected Params params;


    public ResponseStatus getStatus() {
        return status;
    }
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Params getParams() {
        return params;
    }
    public void setParams(Params params) {
        this.params = params;
    }

}
