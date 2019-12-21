package hu.imsi.mir.dto;

import hu.imsi.mir.common.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RsResponseStatus {

    protected List<Message> messages;
}
