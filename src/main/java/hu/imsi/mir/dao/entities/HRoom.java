package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class HRoom {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "museum_id")
    private HMuseum museum;

    @Column(name = "description")
    private String description;

    @Column(name = "size")
    private Integer size;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "type")
    private String type;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "room",
            fetch = FetchType.LAZY
    )
    private List<HLayout> layouts;


    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "room",
            fetch = FetchType.LAZY
    )
    private List<HWall> walls;

}
