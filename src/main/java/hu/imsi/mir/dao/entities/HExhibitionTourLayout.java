package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exhibition_tour_layouts")
@Getter
@Setter
public class HExhibitionTourLayout {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_tour_id")
    private HExhibitionTour exhibitionTour;

    @Column(name = "tour_order")
    private Integer tourOrder;

}
