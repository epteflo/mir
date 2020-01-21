package hu.imsi.mir.utils;

import hu.imsi.mir.common.*;
import hu.imsi.mir.dao.entities.HDoor;
import hu.imsi.mir.dao.entities.HExhibition;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsResponse;
import liquibase.util.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

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
        if(list.isEmpty()){
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

    private static ResponseStatus validateDeleteExhibition(HExhibition exhibition){
        if(!exhibition.getExhibitionTours().isEmpty()){
            ResponseStatus responseStatus = new ResponseStatus();
            addMessage(ResponseMessage.ENTITY_NOT_DELETABLE, responseStatus);
            return responseStatus;
        }
        return null;
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


    private static boolean validateRoom(Room r){
        if(StringUtils.isEmpty(r.getName())){
            addMessage(ResponseMessage.ROOM_NAME_EMPTY,r);
        }
        if(r.getMuseumId()==null){
            addMessage(ResponseMessage.ROOM_MUSEUM_ID_EMPTY,r);
        } else if(!BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HMuseum.class).findById(r.getMuseumId()).isPresent()){
            addMessage(ResponseMessage.ROOM_MUSEUM_NOT_EXISTS,r);
        }

        if(r.getResponseStatus()==null || r.getResponseStatus().getCode()==0) return true;
        else return false;
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

    private static boolean validateDoor(Door d){

        if(d.getRoomAId()==null){
            addMessage(ResponseMessage.DOOR_ROOM_A_ID_EMPTY,d);
        }

        if (d.getRoomBId() == null) {
            addMessage(ResponseMessage.DOOR_ROOM_B_ID_EMPTY,d);
        }

        if(!checkResponse(d.getResponseStatus())) return false;

        if (!BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HRoom.class).findById(d.getRoomAId()).isPresent()) {
            addMessage(ResponseMessage.DOOR_ROOM_A_NOT_EXISTS, d);
        }

        if (!BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HRoom.class).findById(d.getRoomBId()).isPresent()) {
            addMessage(ResponseMessage.DOOR_ROOM_B_NOT_EXISTS, d);
        }

        if(d.getResponseStatus()==null || d.getResponseStatus().getCode()==0) return true;
        else return false;
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




}
