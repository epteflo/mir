package hu.imsi.mir.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "doors")
@Getter
@Setter
public class HDoor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id_a")
    private HRoom roomA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id_b")
    private HRoom roomB;

    @Column(name = "coord_x")
    private Integer coordX;

    @Column(name = "coord_y")
    private Integer coordY;

    @Column(name = "size")
    private Integer size;

    @Column(name = "type")
    private String type;

}
