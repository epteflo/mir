package hu.imsi.mir.dto;

import hu.imsi.mir.dao.entities.HRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsDoor {

    private Integer id;
    private HRoom roomA;
    private HRoom roomB;
    private Integer roomAX;
    private Integer roomAY;
    private Integer roomBX;
    private Integer roomBY;

}
