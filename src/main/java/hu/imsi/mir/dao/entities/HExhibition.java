package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exhibitions")
@Getter
@Setter
public class HExhibition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "museum_id")
    private HMuseum museum;

    @Column(name = "type")
    private String type;


    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "exhibition",
            fetch = FetchType.LAZY
    )
    private List<HExhibitionTour> exhibitionTours;


}
