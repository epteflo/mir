package hu.imsi.mir;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.RoomRepository;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsRoom;
import hu.imsi.mir.mappers.RoomMapper;
import hu.imsi.mir.service.ManagementServiceHandler;
import hu.imsi.mir.utils.LoggerServiceHandler;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.SERVICE_CALLED;
import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/rooms")
public class RoomResource extends BaseResource{
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ManagementServiceHandler managementServiceHandler;

    @Autowired
    private LoggerServiceHandler loggerServiceHandler;

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
                                                   @RequestParam(value = "description", required = false) final String description) {

        final HRoom example = new HRoom();
        example.setName(name);
        example.setDescription(description);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return super.getModels(matcher, example, Room.class, RsRoom.class, userName, "getRooms");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsRoom> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsRoom rsRoom) {
        return super.updateEntity(rsRoom, Room.class, userName, id,"updateRoom");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsRoom> deleteMuseum(@RequestHeader(USER_NAME) String userName,
                                                 @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsRoom.class, Room.class, userName, id, "deleteRoom");
    }

}
