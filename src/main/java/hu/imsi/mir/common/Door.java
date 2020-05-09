package hu.imsi.mir.common;

import hu.imsi.mir.dao.entities.HRoom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class Door extends Response{

    private Integer id;
    private Integer roomAId;
    private Integer roomBId;
    private Integer coordX;
    private Integer coordY;
    private Integer size;
    private String type;

}
