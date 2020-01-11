package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentObject {
    private Integer id;
    private Integer contentId;
    private Integer museumId;
    private Integer poiId;
    private Integer roomId;
    private String type;
    private String description;
}