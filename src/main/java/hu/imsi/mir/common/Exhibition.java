package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exhibition extends Response{

    private Integer id;
    private String name;
    private String description;
    private Integer museumId;
    private String type;
    private String style;

}
