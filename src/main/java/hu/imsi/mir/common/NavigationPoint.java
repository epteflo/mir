package hu.imsi.mir.common;

import hu.imsi.mir.dto.RsResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavigationPoint extends RsResponse{
    private String uuid;
    private Integer order;
    private Integer roomId;
    private Integer layoutId;
    private Integer doorId;
    private Room room;
    private Layout layout;
    private Door door;

}