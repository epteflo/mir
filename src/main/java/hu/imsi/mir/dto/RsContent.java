package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsContent {

    private Integer id;
    private String name;
    private String uuid;
    private String type;
    private String description;
    private String contentUrl;

}
