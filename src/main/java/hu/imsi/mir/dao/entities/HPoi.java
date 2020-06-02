package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pois")
@Getter
@Setter
public class HPoi {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "age")
    private String age;

    @Column(name = "creation_place")
    private String creationPlace;

    @Column(name = "material")
    private String material;

    @Column(name = "style")
    private String style;

    @Column(name = "category")
    private String category;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "description")
    private String description;






}