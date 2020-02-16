package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExhibitionTour extends Response{

    private Integer id;
    private String name;
    private String description;
    private String type;
    private Integer exhibitionId;
    private Exhibition exhibition;
    private List<ExhibitionTourLayout> exhibitionTourLayouts;

}
