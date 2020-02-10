package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RsExhibitionTour extends RsResponse{

    private Integer id;
    private String name;
    private String description;
    private Integer exhibitionId;
    private List<RsExhibitionTourLayout> exhibitionTourLayouts;

}
