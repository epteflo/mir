package hu.imsi.mir.utils;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Response;
import hu.imsi.mir.dto.RsResponse;
import liquibase.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class ServiceHelper {

    public static <T extends RsResponse> ResponseEntity<T> createResponse(T response){
        if(!CollectionUtils.isEmpty(response.getResponseStatus().getMessages())){
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

        return true;
    }

    private static boolean validateMuseum(Museum m){
        if(StringUtils.isEmpty(m.getName())){
            ResponseMessageHelper.addToResponse(ResponseMessageHelper.convertToMessage(ResponseMessage.MUSEUM_NAME_EMPTY),m);
        }

        if(m.getResponseStatus().getCode()==0) return true;
        else return false;
    }




}
