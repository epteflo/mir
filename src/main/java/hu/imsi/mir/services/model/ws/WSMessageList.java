package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messageList",
        propOrder = {
        "message"
})
public class WSMessageList {

    @XmlElement
    protected List<WSMessage> message;

    public List<WSMessage> getMessage() {
        if (message == null) {
            message = new ArrayList<>();
        }
        return this.message;
    }

}
