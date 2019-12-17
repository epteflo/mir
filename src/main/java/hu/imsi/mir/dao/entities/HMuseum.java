package hu.imsi.mir.dao.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "museums")
@Getter
@Setter
public class HMuseum {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "num_of_rooms")
    private Integer numOfRooms;

    @Column(name = "history")
    private String history;

    @Column(name = "curiosity")
    private String curiosity;

    @Column(name = "open_hours")
    private String openHours;

    @Column(name = "other_services")
    private String otherServices;

    @Column(name = "prices")
    private String prices;
}