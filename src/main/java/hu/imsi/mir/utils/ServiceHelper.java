package hu.imsi.mir.utils;

import hu.imsi.mir.common.*;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsResponse;
import liquibase.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

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
        
        return true;
    }

    private static boolean validateMuseum(Museum m){
        if(StringUtils.isEmpty(m.getName())){
            addMessage(ResponseMessage.MUSEUM_NAME_EMPTY,m);
        }

        if(m.getResponseStatus()==null || m.getResponseStatus().getCode()==0) return true;
        else return false;
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


    private static void addMessage(ResponseMessage responseMessage, Response r){
        ResponseMessageHelper.addToResponse(ResponseMessageHelper.convertToMessage(responseMessage),r);
    }




}
