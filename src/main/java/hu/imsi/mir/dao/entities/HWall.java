package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "walls")
@Getter
@Setter
public class HWall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private HRoom room;

    @Column(name = "description")
    private String description;

    @Column(name = "coord_x1")
    private Integer coordX1;

    @Column(name = "coord_y1")
    private Integer coordY1;

    @Column(name = "coord_x2")
    private Integer coordX2;

    @Column(name = "coord_y2")
    private Integer coordY2;

    @Column(name = "type")
    private String type;

    @Column(name = "comment")
    private String comment;

    @Column(name = "wall_order")
    private Integer wallOrder;

}
