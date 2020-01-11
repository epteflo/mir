package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Poi {
    private Integer id;
    private String name;
    private String type;
    private String shortDesc;
    private String description;
    private String category;
    private String style;


}