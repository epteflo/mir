package hu.imsi.mir;

import hu.imsi.mir.common.Beacon;
import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dto.RsBeacon;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/beacons")
public class BeaconResource extends BaseResource{
    @Autowired
    private ServiceRegistry serviceRegistry;

    @PostMapping()
    public ResponseEntity<RsBeacon> createBeacon(@RequestHeader(USER_NAME) String userName,
                                             @RequestBody final RsBeacon rsBeacon) {
        return super.createEntity(rsBeacon, Beacon.class, userName, "createBeacon");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RsBeacon> getBeacon(@RequestHeader(USER_NAME) String userName,
                                          @PathVariable(value = "id") Integer id) {
        return super.getModel(RsBeacon.class, Beacon.class, userName, id, "getBeacon");
    }

    @GetMapping()
    public ResponseEntity<List<RsBeacon>> getBeacons(@RequestHeader(USER_NAME) String userName,
                                                     @RequestParam(value = "uuid", required = false) final String uuid,
                                                     @RequestParam(value = "type", required = false) final String type,
                                                     @RequestParam(value = "color", required = false) final String color)
    {

        final HBeacon example = new HBeacon();
        example.setUuid(uuid);
        example.setType(type);
        example.setColor(color);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("uuid", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());

        return super.getModels(matcher, example, Beacon.class, RsBeacon.class, userName, "getBeacons");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsBeacon> update(@RequestHeader(USER_NAME) String userName,
                                         @PathVariable(value = "id") Integer id,
                                         @RequestBody final RsBeacon rsBeacon) {
        return super.updateEntity(rsBeacon, Beacon.class, userName, id,"updateBeacon");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsBeacon> deleteBeacon(@RequestHeader(USER_NAME) String userName,
                                             @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsBeacon.class, Beacon.class, userName, id, "deleteBeacon");
    }

}
