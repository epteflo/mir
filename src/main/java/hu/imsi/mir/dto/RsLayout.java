package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsLayout extends RsResponse{
    private Integer id;
    private Integer roomId;
    private Integer beaconId;
    private Integer exhibitionId;
    private Integer poiId;
    private Integer roomX;
    private Integer roomY;
}