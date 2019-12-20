package hu.imsi.mir.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class RsBeacon {

    private Integer id;
    private String uuid;
    private String type;
    private String color;

}
