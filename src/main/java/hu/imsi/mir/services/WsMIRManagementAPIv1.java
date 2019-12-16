package hu.imsi.mir.services;

import hu.imsi.mir.services.model.ws.WSCreateMuseumRequest;
import hu.imsi.mir.services.model.ws.WSCreateMuseumResponse;

import javax.jws.*;

@WebService(name = "WsMIRManagementAPIv1", serviceName = "WsMIRManagementAPIv1")
@HandlerChain(file = "handlers.xml")
public class WsMIRManagementAPIv1  {

    @WebMethod
    @WebResult(name = "response")
    public WSCreateMuseumResponse createMuseum(@WebParam(name = "request") WSCreateMuseumRequest request) {
        WSCreateMuseumResponse createMuseumResponseWS = new WSCreateMuseumResponse();
        return createMuseumResponseWS;
    }
}
