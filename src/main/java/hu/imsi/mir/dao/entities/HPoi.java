package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "style")
    private String style;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "layout_id")
    private HLayout layout;


}