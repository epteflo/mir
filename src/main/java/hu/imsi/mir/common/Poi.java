package hu.imsi.mir.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class Poi extends Response{
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