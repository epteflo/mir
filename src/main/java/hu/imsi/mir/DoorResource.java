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
                                                   @RequestParam(value = "name", required = false) final String name,
                                                   @RequestParam(value = "description", required = false) final String description,
                                                   @RequestParam(value = "museumId", required = false) final Integer roomId) {

        final HDoor example = new HDoor();
        example.setRoomA((HRoom)serviceRegistry.REPOSITORY_MAP.get(HRoom.class).findById(roomId).get());
        example.setRoomB((HRoom)serviceRegistry.REPOSITORY_MAP.get(HRoom.class).findById(roomId).get());
        final ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("roomA", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("roomB", ExampleMatcher.GenericPropertyMatchers.exact());
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
