package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wall extends Response{
    private Integer id;
    private Integer roomId;

    private Integer coordX1;
    private Integer coordY1;

    private Integer coordX2;
    private Integer coordY2;

    private String type;
    private Integer wallOrder;
    private String comment;
}
