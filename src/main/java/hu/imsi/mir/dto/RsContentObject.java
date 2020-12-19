package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsContentObject extends RsResponse{
    private Integer id;
    private String name;
    private Integer contentId;
    private Integer museumId;
    private Integer exhibitionId;
    private Integer exhibitionTourId;
    private Integer poiId;
    private Integer roomId;
    private String additionalInfo;
    private RsContent content;
}