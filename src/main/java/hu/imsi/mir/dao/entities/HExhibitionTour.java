package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exhibition_tours")
@Getter
@Setter
public class HExhibitionTour {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private HExhibition exhibition;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "exhibitionTour",
            fetch = FetchType.LAZY
    )
    private List<HExhibitionTourLayout> exhibitionTourLayouts;

}
