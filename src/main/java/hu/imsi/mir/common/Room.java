package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room extends Response{
    private Integer id;
    private String name;
    private Integer museumId;
    private String description;

    private Integer sizeX;
    private Integer sizeY;

    private Integer floor;
    private String type;

}
