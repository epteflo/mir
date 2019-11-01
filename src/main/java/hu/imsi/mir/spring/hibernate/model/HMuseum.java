package hu.imsi.mir.spring.hibernate.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "museums")
public class HMuseum {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "address")
    private String address;

    @Column(name = "num_of_rooms")
    private Integer numOfRooms;

    @Column(name = "history")
    @Type(type = "text")
    private String history;

    @Column(name = "curiosity")
    @Type(type = "text")
    private String curiosity;

    @Column(name = "open_hours")
    private String openHours;

    @Column(name = "other_services")
    @Type(type = "text")
    private String otherServices;

    @Column(name = "prices")
    private String prices;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Integer numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCuriosity() {
        return curiosity;
    }

    public void setCuriosity(String curiosity) {
        this.curiosity = curiosity;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(String otherServices) {
        this.otherServices = otherServices;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }
}