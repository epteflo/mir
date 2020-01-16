package hu.imsi.mir.utils;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Response;
import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.entities.HMuseum;
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

    public static <M extends Response> boolean validateEntity(M entity){
        if(entity instanceof Museum){
            return validateMuseum((Museum)entity);
        }
        if(entity instanceof Room){
            return validateRoom((Room)entity);
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
        } else if(BeanHelper.getServiceRegistry().REPOSITORY_MAP.get(HMuseum.class).findById(r.getMuseumId())==null){
            addMessage(ResponseMessage.ROOM_MUSEUM_NOT_EXISTS,r);
        }

        if(r.getResponseStatus()==null || r.getResponseStatus().getCode()==0) return true;
        else return false;
    }


    private static void addMessage(ResponseMessage responseMessage, Response r){
        ResponseMessageHelper.addToResponse(ResponseMessageHelper.convertToMessage(responseMessage),r);
    }




}
