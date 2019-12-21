package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseStatus {

    protected Integer code=0;
    protected List<Message> messages;

}
