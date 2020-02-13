package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentObject extends Response{
    private Integer id;
    private Content content;
    private Integer museumId;
    private Integer poiId;
    private Integer roomId;
    private String additionalInfo;
}