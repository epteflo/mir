package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Layout extends Response{
    private Integer id;
    private Integer roomId;
    private Integer beaconId;
    private Integer exhibitionId;
    private Integer poiId;
    private Integer roomX;
    private Integer roomY;
    private Poi poi;
}