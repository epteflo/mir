package hu.imsi.mir.services.model.inner;

import hu.imsi.mir.services.model.ws.WSMessage;

import java.util.ArrayList;
import java.util.List;


public class MessageList {

    protected List<Message> message;

    public List<Message> getMessage() {
        if (message == null) {
            message = new ArrayList<>();
        }
        return this.message;
    }

}
