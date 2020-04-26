package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Room extends Response{
    private Integer id;
    private String name;
    private Integer museumId;
    private String description;

    private Integer size;

    private Integer floor;
    private String type;

    private List<Wall> walls;
}
