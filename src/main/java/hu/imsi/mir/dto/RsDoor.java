package hu.imsi.mir.dto;

import hu.imsi.mir.dao.entities.HRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsDoor {

    private Integer id;
    private Integer roomAId;
    private Integer roomBId;
    private Integer roomAX;
    private Integer roomAY;
    private Integer roomBX;
    private Integer roomBY;

}
