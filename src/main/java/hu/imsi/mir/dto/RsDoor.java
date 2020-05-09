package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsDoor extends RsResponse{

    private Integer id;
    private Integer roomAId;
    private Integer roomBId;
    private Integer coordX;
    private Integer coordY;
    private Integer size;
    private String type;

}
