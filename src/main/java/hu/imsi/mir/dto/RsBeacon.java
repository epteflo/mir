package hu.imsi.mir.dto;

import lombok.Data;

@Data
public class RsBeacon extends RsResponse {

    private Integer id;
    private String uuid;
    private String type;
    private String color;

}
