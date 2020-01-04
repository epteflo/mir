package hu.imsi.mir.utils;

import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBHelper {

    @Autowired
    ServiceRegistry serviceRegistry;


    public HMuseum createMuseum(String name, String desc, String address, Integer numOfRooms, String history, String curiosity, String openHours, String otherServices, String prices){
        HMuseum museum = new HMuseum();
        museum.setName(name);
        museum.setDescription(desc);
        museum.setAddress(address);
        museum.setNumOfRooms(numOfRooms);
        museum.setHistory(history);
        museum.setCuriosity(curiosity);
        museum.setOpenHours(openHours);
        museum.setOtherServices(otherServices);
        museum.setPrices(prices);
        serviceRegistry.REPOSITORY_MAP.get(HMuseum.class).saveAndFlush(museum);
        return museum;
    }

    public HRoom createRoom(String name, String description, HMuseum museum, Integer x, Integer y){
        HRoom room = new HRoom();
        room.setName(name);
        room.setDescription(description);
        room.setMuseum(museum);
        room.setSizeX(x);
        room.setSizeY(y);
        serviceRegistry.REPOSITORY_MAP.get(HRoom.class).saveAndFlush(room);
        return room;
    }

    public HBeacon createBeacon(String uuid, String type, String color){
        HBeacon beacon = new HBeacon();
        beacon.setUuid(uuid);
        beacon.setType(type);
        beacon.setColor(color);
        serviceRegistry.REPOSITORY_MAP.get(HBeacon.class).saveAndFlush(beacon);
        return beacon;
    }

}
