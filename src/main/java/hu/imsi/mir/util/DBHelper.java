package hu.imsi.mir.util;

import hu.imsi.mir.spring.hibernate.model.HMuseum;
import hu.imsi.mir.spring.hibernate.service.MirService;

public class DBHelper {

    private MirService mirService;

    public HMuseum createMuseum(String name, String desc, String address, Integer numOfRooms, String history, String curiosity, String openHours, String otherServices, String prices){
        HMuseum museum = new HMuseum();
        museum.setName(name);
        museum.setDesc(desc);
        museum.setAddress(address);
        museum.setNumOfRooms(numOfRooms);
        museum.setHistory(history);
        museum.setCuriosity(curiosity);
        museum.setOpenHours(openHours);
        museum.setOtherServices(otherServices);
        museum.setPrices(prices);
        mirService.saveMuseum(museum);
        return museum;
    }

    public MirService getMirService() {
        return mirService;
    }

    public void setMirService(MirService mirService) {
        this.mirService = mirService;
    }
}
