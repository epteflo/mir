package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentObject extends Response{
    private Integer id;
    private String name;
    private Integer contentId;
    private Integer museumId;
    private Integer poiId;
    private Integer roomId;
    private Integer exhibitionId;
    private Integer exhibitionTourId;
    private String additionalInfo;
    private Content content;
}