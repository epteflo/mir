package hu.imsi.mir;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.RoomRepository;
import hu.imsi.mir.dto.RsRoom;
import hu.imsi.mir.mappers.RoomMapper;
import hu.imsi.mir.service.ManagementServiceHandler;
import hu.imsi.mir.utils.LoggerServiceHandler;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*@GetMapping(path = "{id}")
    public ResponseEntity<RsRoom> getRoom(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        loggerServiceHandler.logStart(userName,SERVICE_CALLED, this.getClass().getName(), "getRoomById");
        final Optional<Room> storedInner = managementServiceHandler.getRoom(id);
        if (!storedInner.isPresent()) return ResponseEntity.notFound().build();
        return ServiceHelper.createResponse(roomMapper.toDto(storedInner.get()));
    }

    
    @PutMapping(path = "{id}")
    public ResponseEntity<RsRoom> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsRoom rsRoom) {
        loggerServiceHandler.logStart(userName,SERVICE_CALLED, this.getClass().getName(), "updateRoom");
        final Room room = roomMapper.toInnerIn(rsRoom);
        final Optional<Room> storedInner = managementServiceHandler.updateRoom(id, room);
        if (!storedInner.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roomMapper.toDto(storedInner.get()));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsRoom> deleteRoom(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        loggerServiceHandler.logStart(userName,SERVICE_CALLED, this.getClass().getName(), "deleteRoom");
        final Optional<Room> storedInner = managementServiceHandler.deleteRoom(id);
        if (!storedInner.isPresent()) return ResponseEntity.notFound().build();
        return ServiceHelper.createResponse(roomMapper.toDto(storedInner.get()));
    }*/

}
