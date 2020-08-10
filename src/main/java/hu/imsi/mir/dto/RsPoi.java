package hu.imsi.mir.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RsPoi extends RsResponse{
    private Integer id;
    private String name;
    private String type;
    private String author;
    private Date creationDate;
    private String age;
    private String creationPlace;
    private String material;
    private String shortDesc;
    private String description;
    private String category;
    private String style;


}