package hu.imsi.mir.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class Museum {
    private Integer id;
    private String name;
    private String description;
    private String address;
    private Integer numOfRooms;
    private String history;
    private String curiosity;
    private String openHours;
    private String otherServices;
    private String prices;
}
