package hu.imsi.mir;

import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsRoom;
import hu.imsi.mir.service.ServiceRegistry;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/rooms")
public class RoomResource extends BaseResource{
    @Autowired
    private ServiceRegistry serviceRegistry;

    @PostMapping()
    public ResponseEntity<RsRoom> createRoom(@RequestHeader(USER_NAME) String userName,
                                             @RequestBody final RsRoom rsRoom) {
        return super.createEntity(rsRoom, Room.class, userName, "createRoom");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RsRoom> getRoom(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsRoom.class, Room.class, userName, id, "getRoom");
    }

    @GetMapping()
    public ResponseEntity<List<RsRoom>> getRooms(@RequestHeader(USER_NAME) String userName,
                                                   @RequestParam(value = "name", required = false) final String name,
                                                   @RequestParam(value = "description", required = false) final String description,
                                                   @RequestParam(value = "museumId", required = false) final Integer museumId) {

        final HRoom example = new HRoom();
        example.setName(name);
        example.setDescription(description);
        HMuseum museum = getEntityById(museumId, HMuseum.class);
        if(museum==null && museumId!=null) return ResponseEntity.notFound().build();
        example.setMuseum(museum);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("museum", ExampleMatcher.GenericPropertyMatchers.exact());
        return super.getModels(matcher, example, Room.class, RsRoom.class, userName, "getRooms");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsRoom> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsRoom rsRoom) {
        return super.updateEntity(rsRoom, Room.class, userName, id,"updateRoom");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsRoom> deleteRoom(@RequestHeader(USER_NAME) String userName,
                                                 @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsRoom.class, Room.class, userName, id, "deleteRoom");
    }

}
