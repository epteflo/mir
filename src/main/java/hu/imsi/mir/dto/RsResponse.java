package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsResponse {
    private RsResponseStatus responseStatus;

    public RsResponse() {
        this.responseStatus = new RsResponseStatus();
    }
}
