package hu.imsi.mir.common;

import lombok.Data;

@Data
public class Beacon {

    private Integer id;
    private String uuid;
    private String type;
    private String color;

}
