package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsDoor extends RsResponse{

    private Integer id;
    private Integer roomAId;
    private Integer roomBId;
    private Integer roomAX;
    private Integer roomAY;
    private Integer roomBX;
    private Integer roomBY;

}
