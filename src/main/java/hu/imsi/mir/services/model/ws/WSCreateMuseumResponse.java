package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createMuseumResponse")
@XmlType(name = "createMuseumResponse",
        propOrder = {
                "status",
                "params",
        })
public class WSCreateMuseumResponse extends WSResponse {

}
