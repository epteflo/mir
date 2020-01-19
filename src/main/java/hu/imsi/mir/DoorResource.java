package hu.imsi.mir;

import hu.imsi.mir.common.Door;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HDoor;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsDoor;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/doors")
public class DoorResource extends BaseResource{
    @Autowired
    private ServiceRegistry serviceRegistry;

    @PostMapping()
    public ResponseEntity<RsDoor> createDoor(@RequestHeader(USER_NAME) String userName,
                                             @RequestBody final RsDoor rsDoor) {
        return super.createEntity(rsDoor, Door.class, userName, "createDoor");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RsDoor> getDoor(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsDoor.class, Door.class, userName, id, "getDoor");
    }

    @GetMapping()
    public ResponseEntity<List<RsDoor>> getDoors(@RequestHeader(USER_NAME) String userName,
                                                 @RequestParam(value = "roomAId", required = false) final Integer roomAId,
                                                 @RequestParam(value = "roomBId", required = false) final Integer roomBId)
    {

        final HDoor example = new HDoor();
        HRoom roomA = getEntityById(roomAId, HRoom.class);
        HRoom roomB = getEntityById(roomBId, HRoom.class);
        if((roomA==null && roomAId !=null) || (roomB==null && roomBId!=null)) return ResponseEntity.notFound().build();
        example.setRoomA(roomA);
        example.setRoomB(roomB);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll();
                matcher.withMatcher("roomA", ExampleMatcher.GenericPropertyMatchers.exact());
                matcher.withMatcher("roomB", ExampleMatcher.GenericPropertyMatchers.exact());

        return super.getModels(matcher, example, Door.class, RsDoor.class, userName, "getDoors");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsDoor> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsDoor rsDoor) {
        return super.updateEntity(rsDoor, Door.class, userName, id,"updateDoor");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsDoor> deleteDoor(@RequestHeader(USER_NAME) String userName,
                                                 @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsDoor.class, Door.class, userName, id, "deleteDoor");
    }

}
