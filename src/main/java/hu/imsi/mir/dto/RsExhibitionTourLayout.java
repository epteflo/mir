package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsExhibitionTourLayout extends RsResponse{

//    private Integer id;
    private Integer exhibitionTourId;
    private Integer layoutId;
    private Integer tourOrder;

}
