package hu.imsi.mir.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class Beacon {

    private Integer id;
    private String uuid;
    private String type;
    private String color;

}
