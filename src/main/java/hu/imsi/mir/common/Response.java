package hu.imsi.mir.common;

import hu.imsi.mir.dto.RsResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private ResponseStatus responseStatus;

    public Response() {
        this.responseStatus = new ResponseStatus();
    }
}
