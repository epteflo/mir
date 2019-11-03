package hu.imsi.mir.spring.hibernate.query;

public class MuseumQueryParams {

    private Integer id;
    private String name;
    private String desc;
    private String address;
    private Integer numOfRooms;
    private String history;
    private String curiosity;
    private String openHours;
    private String otherServices;
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
