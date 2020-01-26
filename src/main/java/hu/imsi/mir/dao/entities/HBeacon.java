package hu.imsi.mir.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "beacons")
@Data
public class HBeacon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "type")
    private String type;

    @Column(name = "color")
    private String color;

}
