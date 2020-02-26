package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsNavigationPoint extends RsResponse{
    private String uuid;
    private Integer order;
    private Integer roomId;
    private Integer layoutId;
    private Integer doorId;
    private RsRoom room;
    private RsLayout layout;
    private RsDoor door;

}