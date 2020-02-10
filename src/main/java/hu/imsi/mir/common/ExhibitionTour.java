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
    private Integer exhibitionId;
    private List<ExhibitionTourLayout> exhibitionTourLayouts;

}
