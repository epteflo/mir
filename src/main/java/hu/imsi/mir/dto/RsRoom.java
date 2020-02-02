package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsRoom extends RsResponse {
    private Integer id;
    private String name;
    private Integer museumId;
    private String description;

    private Integer sizeX;
    private Integer sizeY;

    private Integer floor;
    private String type;

}
