package hu.imsi.mir.common;

import hu.imsi.mir.dao.entities.HRoom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class Door {

    private Integer id;
    private Integer roomAId;
    private Integer roomBId;
    private Integer roomAX;
    private Integer roomAY;
    private Integer roomBX;
    private Integer roomBY;

}
