package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createMuseumRequest")
@XmlType(name = "createMuseumRequest", propOrder = {
        "museum",
})
public class WSCreateMuseumRequest {

    WSMuseum museum;

    @XmlElement
    public WSMuseum getMuseum() {
        return museum;
    }

    public void setMuseum(WSMuseum museum) {
        this.museum = museum;
    }
}

