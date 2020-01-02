package hu.imsi.mir.common;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Content {

    private Integer id;
    private String name;
    private String uuid;
    private String type;
    private String description;
    private String contentUrl;

}
