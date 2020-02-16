package hu.imsi.mir.utils;

import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dBHelper")
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
        return (HMuseum)serviceRegistry.REPOSITORY_MAP.get(HMuseum.class).saveAndFlush(museum);
    }

    public HRoom createRoom(String name, String description, HMuseum museum, Integer x, Integer y, Integer floor, String type, Integer coordX, Integer coordY){
        HRoom room = new HRoom();
        room.setName(name);
        room.setDescription(description);
        room.setMuseum(museum);
        room.setSizeX(x);
        room.setSizeY(y);
        room.setFloor(floor);
        room.setType(type);
        room.setCoordX(coordX);
        room.setCoordY(coordY);
        return (HRoom)serviceRegistry.REPOSITORY_MAP.get(HRoom.class).saveAndFlush(room);
    }

    public HDoor createDoor(HRoom roomA, HRoom roomB, Integer roomAX, Integer roomAY, Integer roomBX, Integer roomBY){
        HDoor door = new HDoor();
        door.setRoomA(roomA);
        door.setRoomB(roomB);
        door.setRoomAX(roomAX);
        door.setRoomAY(roomAY);
        door.setRoomBX(roomBX);
        door.setRoomBY(roomBY);
        return (HDoor)serviceRegistry.REPOSITORY_MAP.get(HDoor.class).saveAndFlush(door);
    }

    public HExhibition createExhibition(String name, String description, String style, String type, HMuseum museum){
        HExhibition exhibition = new HExhibition();
        exhibition.setName(name);
        exhibition.setDescription(description);
        exhibition.setType(type);
        exhibition.setStyle(style);
        exhibition.setMuseum(museum);
        return (HExhibition)serviceRegistry.REPOSITORY_MAP.get(HExhibition.class).saveAndFlush(exhibition);
    }

    public HPoi createPoi(String name, String type, String shortDesc, String description, String category, String style){
        HPoi poi = new HPoi();
        poi.setName(name);
        poi.setType(type);
        poi.setShortDesc(shortDesc);
        poi.setDescription(description);
        poi.setCategory(category);
        poi.setStyle(style);
        return (HPoi)serviceRegistry.REPOSITORY_MAP.get(HExhibition.class).saveAndFlush(poi);
    }

    public HBeacon createBeacon(String uuid, String type, String color){
        HBeacon beacon = new HBeacon();
        beacon.setUuid(uuid);
        beacon.setType(type);
        beacon.setColor(color);
        return (HBeacon)serviceRegistry.REPOSITORY_MAP.get(HBeacon.class).saveAndFlush(beacon);

    }

    public HLayout createLayout(HRoom room, HBeacon beacon, HExhibition exhibition, HPoi poi, Integer x, Integer y){
        HLayout layout = new HLayout();
        layout.setRoom(room);
        layout.setBeacon(beacon);
        layout.setExhibition(exhibition);
        layout.setPoi(poi);
        layout.setRoomX(x);
        layout.setRoomY(y);
        return (HLayout)serviceRegistry.REPOSITORY_MAP.get(HLayout.class).saveAndFlush(layout);
    }

    public HExhibitionTour createExhibitionTour(String name, String description, HExhibition exhibition, String type){
        HExhibitionTour exhibitionTour = new HExhibitionTour();
        exhibitionTour.setName(name);
        exhibitionTour.setDescription(description);
        exhibitionTour.setExhibition(exhibition);
        exhibitionTour.setType(type);
        return (HExhibitionTour)serviceRegistry.REPOSITORY_MAP.get(HExhibitionTour.class).saveAndFlush(exhibitionTour);
    }

    public HExhibitionTourLayout createExhibitionTourLayout(HExhibitionTour exhibitionTour, HLayout layout, Integer tourOrder){
        HExhibitionTourLayout exhibitionTourLayout = new HExhibitionTourLayout();
        exhibitionTourLayout.setExhibitionTour(exhibitionTour);
        exhibitionTourLayout.setLayout(layout);
        exhibitionTourLayout.setTourOrder(tourOrder);
        return (HExhibitionTourLayout)serviceRegistry.REPOSITORY_MAP.get(HExhibitionTourLayout.class).saveAndFlush(exhibitionTourLayout);
    }

    public HContent createContent(String name, String type, String uuid, String description, String contentURL, String fileName, String internalURL){
        HContent content = new HContent();
        content.setName(name);
        content.setType(type);
        content.setUuid(uuid);
        content.setDescription(description);
        content.setContentUrl(contentURL);
        content.setFileName(fileName);
        content.setInternalUrl(internalURL);
        return (HContent)serviceRegistry.REPOSITORY_MAP.get(HContent.class).saveAndFlush(content);
    }

    public HContentObject createContentObject(HContent content, HMuseum museum, HRoom room, HPoi poi, String additionalInfo){
        HContentObject contentObject = new HContentObject();
        contentObject.setContent(content);
        contentObject.setMuseum(museum);
        contentObject.setRoom(room);
        contentObject.setPoi(poi);
        contentObject.setAdditionalInfo(additionalInfo);
        return (HContentObject)serviceRegistry.REPOSITORY_MAP.get(HContentObject.class).saveAndFlush(contentObject);
    }

}
