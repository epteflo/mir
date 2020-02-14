package hu.imsi.mir.utils;

import hu.imsi.mir.common.*;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.RsResponse;
import liquibase.util.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ServiceHelper {

    public static <T extends RsResponse> ResponseEntity<T> createResponse(T response){
        if(response==null){
            return ResponseEntity.notFound().build();
        }
        else if(!CollectionUtils.isEmpty(response.getResponseStatus().getMessages())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
            response.getResponseStatus();
            return ResponseEntity.ok(response);
    }

    public static <C extends Collection> ResponseEntity createResponse(C list){
        if(CollectionUtils.isEmpty(list)){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    public static <E> ResponseStatus validateDeleteEntity(E entity){
        if(entity instanceof HMuseum){
            return validateDeleteMuseum((HMuseum)entity);
        }
        if(entity instanceof HRoom){
            return validateDeleteRoom((HRoom)entity);
        }
        if(entity instanceof HExhibition){
            return validateDeleteExhibition((HExhibition)entity);
        }
        if(entity instanceof HBeacon){
            return validateDeleteBeacon((HBeacon)entity);
        }
        if(entity instanceof HPoi){
            return validateDeletePoi((HPoi) entity);
        }
        if(entity instanceof HLayout){
            return validateDeleteLayout((HLayout) entity);
        }
        if(entity instanceof HContent){
            return validateDeleteContent((HContent) entity);
        }

        return null;
    }

    public static <M extends Response> boolean validateModel(M model){
        if(model instanceof Museum){
            return validateMuseum((Museum)model);
        }
        if(model instanceof Room){
            return validateRoom((Room)model);
        }
        if(model instanceof Door){
            return validateDoor((Door)model);
        }
        if(model instanceof Exhibition){
            return validateExhibition((Exhibition)model);
        }
        if(model instanceof Beacon){
            return validateBeacon((Beacon)model);
        }
        if(model instanceof Poi){
            return validatePoi((Poi)model);
        }
        if(model instanceof Layout){
            return validateLayout((Layout)model);
        }
        if(model instanceof ExhibitionTour){
            return validateExhibitionTour((ExhibitionTour)model);
        }
        if(model instanceof Content){
            return validateContent((Content)model);
        }
        return true;
    }



    private static boolean validateMuseum(Museum m){
        if(StringUtils.isEmpty(m.getName())){
            addMessage(ResponseMessage.MUSEUM_NAME_EMPTY,m);
        }

        if(m.getResponseStatus()==null || m.getResponseStatus().getCode()==0) return true;
        else return false;
    }

    private static ResponseStatus validateDeleteMuseum(HMuseum museum){
        if(!museum.getRooms().isEmpty() || !museum.getExhibitions().isEmpty() || !museum.getContentObjects().isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
    }



    private static boolean validateExhibition(Exhibition e){
        if(StringUtils.isEmpty(e.getName())){
            addMessage(ResponseMessage.EXHIBITION_NAME_EMPTY,e);
        }
        if(e.getMuseumId()==null){
            addMessage(ResponseMessage.EXHIBITION_MUSEUM_ID_EMPTY,e);
        } else if(!BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HMuseum.class).findById(e.getMuseumId()).isPresent()){
            addMessage(ResponseMessage.EXHIBITION_MUSEUM_NOT_EXISTS,e);
        }

        if(e.getResponseStatus()==null || e.getResponseStatus().getCode()==0) return true;
        else return false;
    }

    private static ResponseStatus validateDeleteExhibition(HExhibition exhibition){
        if(!exhibition.getExhibitionTours().isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
    }



    private static boolean validateRoom(Room r){
        if(StringUtils.isEmpty(r.getName())){
            addMessage(ResponseMessage.ROOM_NAME_EMPTY,r);
        }
        if(r.getMuseumId()==null){
            addMessage(ResponseMessage.ROOM_MUSEUM_ID_EMPTY,r);
        } else if(!BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HMuseum.class).findById(r.getMuseumId()).isPresent()){
            addMessage(ResponseMessage.ROOM_MUSEUM_NOT_EXISTS,r);
        }

        if(r.getSizeX()==null){
            addMessage(ResponseMessage.X_SIZE_MISSING,r);
        }

        if(r.getSizeY()==null){
            addMessage(ResponseMessage.Y_SIZE_MISSING,r);
        }

        if(r.getResponseStatus()==null || r.getResponseStatus().getCode()==0) return true;
        else return false;
    }

    private static ResponseStatus validateDeleteRoom(HRoom room){
        HDoor example = new HDoor();
        example.setRoomA(room);
        example.setRoomB(room);
        final ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("roomA", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("roomB", ExampleMatcher.GenericPropertyMatchers.exact());

        final JpaRepository<HDoor, ?> repository = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HDoor.class);
        List l = repository.findAll(Example.of(example, matcher));
        if(!l.isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
    }





    private static boolean validateDoor(Door d){

        if(d.getRoomAId()==null){
            addMessage(ResponseMessage.DOOR_ROOM_A_ID_EMPTY,d);
        }

        if (d.getRoomBId() == null) {
            addMessage(ResponseMessage.DOOR_ROOM_B_ID_EMPTY,d);
        }

        if(!checkResponse(d.getResponseStatus())) return false;

        Optional<HRoom> hRoomA = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HRoom.class).findById(d.getRoomAId());
        if (!hRoomA.isPresent()) {
            addMessage(ResponseMessage.DOOR_ROOM_A_NOT_EXISTS, d);
        }

        Optional<HRoom> hRoomB = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HRoom.class).findById(d.getRoomBId());

        if (!hRoomB.isPresent()) {
            addMessage(ResponseMessage.DOOR_ROOM_B_NOT_EXISTS, d);
        }

        if(!checkResponse(d.getResponseStatus())) return false;

        if(d.getRoomAX()==null){
            addMessage(ResponseMessage.X_COORD_MISSING,d);
        }

        if(d.getRoomAY()==null){
            addMessage(ResponseMessage.Y_COORD_MISSING,d);
        }

        if(d.getRoomBX()==null){
            addMessage(ResponseMessage.X_COORD_MISSING,d);
        }

        if(d.getRoomBY()==null){
            addMessage(ResponseMessage.Y_COORD_MISSING,d);
        }

        if(!isOnTheWall(d.getRoomAX(), d.getRoomAY(), hRoomA.get().getSizeX(), hRoomA.get().getSizeY())){
            addMessage(ResponseMessage.DOOR_NOT_AT_THE_WALL_PATH,d);
        }

        if(!isOnTheWall(d.getRoomBX(), d.getRoomBY(), hRoomB.get().getSizeX(), hRoomB.get().getSizeY())) {
            addMessage(ResponseMessage.DOOR_NOT_AT_THE_WALL_PATH, d);
        }

        if(d.getResponseStatus()==null || d.getResponseStatus().getCode()==0) return true;
        else return false;
    }





    private static boolean validateBeacon(Beacon m){

        if(m.getResponseStatus()==null || m.getResponseStatus().getCode()==0) return true;
        else return false;
    }

    private static ResponseStatus validateDeleteBeacon(HBeacon beacon){
        HLayout example = new HLayout();
        example.setBeacon(beacon);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("beacon", ExampleMatcher.GenericPropertyMatchers.exact());

        final JpaRepository<HLayout, ?> repository = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HLayout.class);
        List l = repository.findAll(Example.of(example, matcher));
        if(!l.isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
    }




    private static boolean validatePoi(Poi p){
        if(StringUtils.isEmpty(p.getName())){
            addMessage(ResponseMessage.POI_NAME_EMPTY,p);
        }
        if(StringUtils.isEmpty(p.getType())){
            addMessage(ResponseMessage.POI_TYPE_EMPTY,p);
        }
        if(p.getResponseStatus()==null || p.getResponseStatus().getCode()==0) return true;
        else return false;
    }

    private static ResponseStatus validateDeletePoi(HPoi poi){
        HLayout example = new HLayout();
        example.setPoi(poi);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("poi", ExampleMatcher.GenericPropertyMatchers.exact());

        final JpaRepository<HLayout, ?> repository = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HLayout.class);
        List l = repository.findAll(Example.of(example, matcher));
        if(!l.isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
    }


    private static boolean validateLayout(Layout layout){
        if(layout.getRoomId()==null){
            addMessage(ResponseMessage.LAYOUT_ROOM_ID_EMPTY,layout);
        }
        if(layout.getPoiId()==null){
            addMessage(ResponseMessage.LAYOUT_POI_ID_EMPTY,layout);
        }

        if(!checkResponse(layout.getResponseStatus())) return false;


        Optional<HRoom> hRoom = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HRoom.class).findById(layout.getRoomId());
        if (!hRoom.isPresent()) {
            addMessage(ResponseMessage.LAYOUT_ROOM_NOT_EXISTS, layout);
        }
        Optional<HPoi> hPoi = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HPoi.class).findById(layout.getPoiId());
        if (!hPoi.isPresent()) {
            addMessage(ResponseMessage.LAYOUT_POI_NOT_EXISTS, layout);
        }
        Optional<HBeacon> hBeacon = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HRoom.class).findById(layout.getBeaconId());
        if (layout.getBeaconId()!=null && !hBeacon.isPresent()) {
            addMessage(ResponseMessage.LAYOUT_BEACON_NOT_EXISTS, layout);
        }
        Optional<HExhibition> hExhibition = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HExhibition.class).findById(layout.getExhibitionId());
        if (layout.getExhibitionId()!=null && !hExhibition.isPresent()) {
            addMessage(ResponseMessage.LAYOUT_EXHIBITION_NOT_EXISTS, layout);
        }


        if(layout.getResponseStatus()==null || layout.getResponseStatus().getCode()==0) return true;
        else return false;
    }

    private static ResponseStatus validateDeleteLayout(HLayout layout){
        HExhibitionTourLayout example = new HExhibitionTourLayout();
        example.setLayout(layout);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("layout", ExampleMatcher.GenericPropertyMatchers.exact());

        final JpaRepository<HExhibitionTourLayout, ?> repository = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HExhibitionTourLayout.class);
        List l = repository.findAll(Example.of(example, matcher));
        if(!l.isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
    }



    private static boolean validateExhibitionTour(ExhibitionTour exhibitionTour){
        if(exhibitionTour.getExhibitionId()==null){
            addMessage(ResponseMessage.EXHTOUR_EXHIBITION_ID_EMPTY,exhibitionTour);
        }

        if(!checkResponse(exhibitionTour.getResponseStatus())) return false;


        Optional<HExhibition> hExhibition = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HExhibition.class).findById(exhibitionTour.getExhibitionId());
        if (exhibitionTour.getExhibitionId()!=null && !hExhibition.isPresent()) {
            addMessage(ResponseMessage.EXHTOUR_EXHIBITION_NOT_EXISTS, exhibitionTour);
        }


        if(exhibitionTour.getResponseStatus()==null || exhibitionTour.getResponseStatus().getCode()==0) return true;
        else return false;
    }


    private static boolean validateContent(Content c){
        if(StringUtils.isEmpty(c.getName())){
            addMessage(ResponseMessage.CONTENT_NAME_EMPTY,c);
        }

        if(c.getResponseStatus()==null || c.getResponseStatus().getCode()==0) return true;
        else return false;
    }

    private static ResponseStatus validateDeleteContent(HContent content){
        HContentObject example = new HContentObject();
        example.setContent(content);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.exact());

        final JpaRepository<HContentObject, ?> repository = BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HContentObject.class);
        List l = repository.findAll(Example.of(example, matcher));
        if(!l.isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
    }


    private static boolean checkResponse(ResponseStatus responseStatus){
        if(responseStatus==null || responseStatus.getCode()==0) return true;
        else return false;
    }

    private static void addMessage(ResponseMessage responseMessage, ResponseStatus responseStatus) {
        ResponseMessageHelper.addToResponse(ResponseMessageHelper.convertToMessage(responseMessage),responseStatus);
    }


    private static void addMessage(ResponseMessage responseMessage, Response r){
        ResponseMessageHelper.addToResponse(ResponseMessageHelper.convertToMessage(responseMessage),r);
    }


    public static boolean isOnTheWall(Integer x, Integer y, Integer sizeX, Integer sizeY){
        if((0<=x && x<=sizeX && (y==0 || y==sizeY )) || (0<=y && y<=sizeY && (x==0 || x==sizeX))){
            return true;
        }
        return false;
    }

    public static String generateSum(byte[] content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return byte2Hex(md.digest(content));
    }

    private static String byte2Hex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();

        for(int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(255 & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }
}
