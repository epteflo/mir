package hu.imsi.mir.services.model.ws;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "museum",
        propOrder = {
                "id",
                "name",
                "desc",
                "address",
                "numOfRooms",
                "history",
                "curiosity",
                "openHours",
                "otherServices",
                "prices"
        }
)
public class WSMuseum {

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

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement
    public Integer getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Integer numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    @XmlElement
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @XmlElement
    public String getCuriosity() {
        return curiosity;
    }

    public void setCuriosity(String curiosity) {
        this.curiosity = curiosity;
    }

    @XmlElement
    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    @XmlElement
    public String getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(String otherServices) {
        this.otherServices = otherServices;
    }

    @XmlElement
    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }
}
