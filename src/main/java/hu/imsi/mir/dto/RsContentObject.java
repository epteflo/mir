package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsContentObject extends RsResponse{
    private Integer id;
    private Integer contentId;
    private Integer museumId;
    private Integer poiId;
    private Integer roomId;
    private String type;
    private String description;
}