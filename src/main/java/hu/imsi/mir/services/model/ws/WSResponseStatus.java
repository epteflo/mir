package hu.imsi.mir.services.model.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseStatus",
        propOrder = {
        "code",
        "messages"
})
public class WSResponseStatus {


    protected Integer code;
    protected WSMessageList messages;

    @XmlElement
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer value) {
        this.code = value;
    }

    @XmlElement
    public WSMessageList getMessages() {
        return messages;
    }

    public void setMessages(WSMessageList value) {
        this.messages = value;
    }

}
