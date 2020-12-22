package hu.imsi.mir.utils;

import hu.imsi.mir.common.*;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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

        Museum museumModel = serviceRegistry.converterRegistry.getConverter(HMuseum.class, Museum.class).map(museum);
        if(!ServiceHelper.validateModel(museumModel)) throw new RuntimeException("Validation Error! "+getMessagesAsString(museumModel.getResponseStatus().getMessages()));

        return (HMuseum)serviceRegistry.REPOSITORY_MAP.get(HMuseum.class).saveAndFlush(museum);
    }

    public HRoom createRoom(String name, String description, HMuseum museum, Integer size, Integer floor, String type){
        HRoom room = new HRoom();
        room.setName(name);
        room.setDescription(description);
        room.setMuseum(museum);
        room.setSize(size);
        room.setFloor(floor);
        room.setType(type);

        return (HRoom)serviceRegistry.REPOSITORY_MAP.get(HRoom.class).saveAndFlush(room);
    }

    public HWall createWall(HRoom room, Integer coordX1, Integer coordY1, Integer coordX2, Integer coordY2, String type, String comment, Integer wallOrder) {
        HWall wall = new HWall();
        wall.setComment(comment);
        wall.setRoom(room);
        wall.setCoordX1(coordX1);
        wall.setCoordY1(coordY1);
        wall.setCoordX2(coordX2);
        wall.setCoordY2(coordY2);
        wall.setType(type);
        wall.setWallOrder(wallOrder);

        return (HWall) serviceRegistry.REPOSITORY_MAP.get(HWall.class).saveAndFlush(wall);
    }

    public HDoor createDoor(HRoom roomA, HRoom roomB, Integer coordX, Integer coordY, Integer size, String type){
        HDoor door = new HDoor();
        door.setRoomA(roomA);
        door.setRoomB(roomB);
        door.setCoordX(coordX);
        door.setCoordY(coordY);
        door.setSize(size);
        door.setType(type);

        return (HDoor)serviceRegistry.REPOSITORY_MAP.get(HDoor.class).saveAndFlush(door);
    }

    public HExhibition createExhibition(String name, String description, String style, String type, HMuseum museum){
        HExhibition exhibition = new HExhibition();
        exhibition.setName(name);
        exhibition.setDescription(description);
        exhibition.setType(type);
        exhibition.setStyle(style);
        exhibition.setMuseum(museum);

        Exhibition exhModel = serviceRegistry.converterRegistry.getConverter(HExhibition.class, Exhibition.class).map(exhibition);
        if(!ServiceHelper.validateModel(exhModel)) throw new RuntimeException("Validation Error! "+getMessagesAsString(exhModel.getResponseStatus().getMessages()));

        return (HExhibition)serviceRegistry.REPOSITORY_MAP.get(HExhibition.class).saveAndFlush(exhibition);
    }

    public HPoi createPoi(String type, String name, String author, Date creationDate, String age, String creationPlace, String material, String category, String style, String shortDesc, String description){
        HPoi poi = new HPoi();
        poi.setType(type);
        poi.setName(name);
        poi.setAuthor(author);
        poi.setCreationDate(creationDate);
        poi.setAge(age);
        poi.setCreationPlace(creationPlace);
        poi.setMaterial(material);

        poi.setStyle(style);
        poi.setCategory(category);

        poi.setShortDesc(shortDesc);
        poi.setDescription(description);

        Poi poiModel = serviceRegistry.converterRegistry.getConverter(HPoi.class, Poi.class).map(poi);
        if(!ServiceHelper.validateModel(poiModel)) throw new RuntimeException("Validation Error! "+getMessagesAsString(poiModel.getResponseStatus().getMessages()));

        return (HPoi)serviceRegistry.REPOSITORY_MAP.get(HExhibition.class).saveAndFlush(poi);
    }

    public HBeacon createBeacon(String uuid, String type, String color){
        HBeacon beacon = new HBeacon();
        beacon.setUuid(uuid);
        beacon.setType(type);
        beacon.setColor(color);

        Beacon beaconModel = serviceRegistry.converterRegistry.getConverter(HBeacon.class, Beacon.class).map(beacon);
        if(!ServiceHelper.validateModel(beaconModel)) throw new RuntimeException("Validation Error! "+getMessagesAsString(beaconModel.getResponseStatus().getMessages()));

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

        Content contentModel = serviceRegistry.converterRegistry.getConverter(HContent.class, Content.class).map(content);
        if(!ServiceHelper.validateModel(contentModel)) throw new RuntimeException("Validation Error! "+getMessagesAsString(contentModel.getResponseStatus().getMessages()));

        return (HContent)serviceRegistry.REPOSITORY_MAP.get(HContent.class).saveAndFlush(content);
    }

    public HContentObject createContentObject(String contentName, String contentCode, HContent content, HMuseum museum, HRoom room, HPoi poi, HExhibition exhibition, HExhibitionTour exhibitionTour, String additionalInfo){
        HContentObject contentObject = new HContentObject();
        contentObject.setName(contentName);
        contentObject.setCode(contentCode);
        contentObject.setContent(content);
        contentObject.setMuseum(museum);
        contentObject.setRoom(room);
        contentObject.setPoi(poi);
        contentObject.setExhibition(exhibition);
        contentObject.setExhibitionTour(exhibitionTour);
        contentObject.setAdditionalInfo(additionalInfo);

        return (HContentObject)serviceRegistry.REPOSITORY_MAP.get(HContentObject.class).saveAndFlush(contentObject);
    }

    private String getMessagesAsString(List<Message> messages){
        StringBuffer buffer = new StringBuffer();
        for(Message m : messages){
            buffer.append(m.getCode()+"-"+m.getDescription()+";");
        }
        return buffer.toString();
    }
}
