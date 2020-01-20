package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsExhibitionTour extends RsResponse{

    private Integer id;
    private String name;
    private String description;
    private Integer exhibitionId;

}
