package hu.imsi.mir.dto;

import hu.imsi.mir.common.Wall;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RsRoom extends RsResponse {
    private Integer id;
    private String name;
    private Integer museumId;
    private String description;

    private Integer size;

    private Integer floor;
    private String type;

    private List<RsWall> walls;
}
