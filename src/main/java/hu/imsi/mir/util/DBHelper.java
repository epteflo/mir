package hu.imsi.mir.util;

import hu.imsi.mir.services.model.inner.CreateMuseumRequest;
import hu.imsi.mir.spring.hibernate.model.HBeacon;
import hu.imsi.mir.spring.hibernate.model.HMuseum;
import hu.imsi.mir.spring.hibernate.service.MirService;

public class DBHelper {


    private MirService mirService;

    public HMuseum createMuseum(CreateMuseumRequest request){
        return createMuseum(request.getMuseum().getName(), request.getMuseum().getDesc(), request.getMuseum().getAddress(),
                request.getMuseum().getNumOfRooms(), request.getMuseum().getHistory(), request.getMuseum().getCuriosity(),
                request.getMuseum().getOpenHours(), request.getMuseum().getOpenHours(), request.getMuseum().getPrices()
        );
    }

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

    public HBeacon createBeacon(String uuid, String type, String color){
        HBeacon beacon = new HBeacon();
        beacon.setUuid(uuid);
        beacon.setType(type);
        beacon.setColor(color);
        mirService.saveBeacon(beacon);
        return beacon;
    }

    public MirService getMirService() {
        return mirService;
    }

    public void setMirService(MirService mirService) {
        this.mirService = mirService;
    }
}
