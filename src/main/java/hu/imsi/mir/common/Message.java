package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    protected String severity;

    protected String code;

    protected String description;

    protected String refElement;

    protected String refValue;

    public Message() {
    }

    public Message(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Message(String code, String description, String refElement, String refValue) {
        this.code = code;
        this.description = description;
        this.refElement = refElement;
        this.refValue = refValue;
    }

}
