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
    private String type;
    private Integer exhibitionId;
    private RsExhibition exhibition;
    private List<RsExhibitionTourLayout> exhibitionTourLayouts;

}
