package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Museum extends Response{
    private Integer id;
    private String name;
    private String description;
    private String address;
    private Integer numOfRooms;
    private String history;
    private String curiosity;
    private String openHours;
    private String otherServices;
    private String prices;

}
