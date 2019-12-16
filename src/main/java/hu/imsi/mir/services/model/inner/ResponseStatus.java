package hu.imsi.mir.services.model.inner;

import hu.imsi.mir.services.model.ws.WSMessageList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


public class ResponseStatus {


    protected Integer code;
    protected MessageList messages;


    public Integer getCode() {
        return code;
    }
    public void setCode(Integer value) {
        this.code = value;
    }

    public MessageList getMessages() {
        return messages;
    }
    public void setMessages(MessageList value) {
        this.messages = value;
    }

}
