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

    @Column(name = "room_a_x")
    private Integer roomAX;

    @Column(name = "room_a_y")
    private Integer roomAY;

    @Column(name = "room_b_x")
    private Integer roomBX;

    @Column(name = "room_b_y")
    private Integer roomBY;

}
