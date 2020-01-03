package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "content_objects")
@Getter
@Setter
public class HContentObject {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private HContent content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "museum_id")
    private HMuseum museum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poi_id")
    private HPoi poi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private HRoom room;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;
}