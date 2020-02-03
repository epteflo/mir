package hu.imsi.mir;

import hu.imsi.mir.common.Layout;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.RsLayout;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/layouts")
public class LayoutResource extends BaseResource{


    @PostMapping()
    public ResponseEntity<RsLayout> createLayout(@RequestHeader(USER_NAME) String userName,
                                                 @RequestBody final RsLayout rsLayout) {
        return super.createEntity(rsLayout, Layout.class, userName, "createLayout");
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<RsLayout> getLayout(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsLayout.class, Layout.class, userName, id, "getLayout");
    }

    @GetMapping()
    public ResponseEntity<List<RsLayout>> getLayouts(@RequestHeader(USER_NAME) String userName,
                                                     @RequestParam(value = "roomId", required = false) final Integer roomId,
                                                     @RequestParam(value = "beaconId", required = false) final Integer beaconId,
                                                     @RequestParam(value = "exhibitionId", required = false) final Integer exhibitionId,
                                                     @RequestParam(value = "poiId", required = false) final Integer poiId) {

        final HLayout example = new HLayout();
        HRoom room = getEntityById(roomId, HRoom.class);
        HBeacon beacon = getEntityById(roomId, HBeacon.class);
        HExhibition exhibition = getEntityById(roomId, HExhibition.class);
        HPoi poi = getEntityById(roomId, HPoi.class);

        if((room==null && roomId !=null) || (beacon==null && beaconId!=null) || (exhibition==null && exhibitionId!=null) || (poi==null && poiId!=null)) return ResponseEntity.notFound().build();
        example.setRoom(room);
        example.setPoi(poi);
        example.setBeacon(beacon);
        example.setExhibition(exhibition);

        final ExampleMatcher matcher = ExampleMatcher.matchingAll();
                matcher.withMatcher("room", ExampleMatcher.GenericPropertyMatchers.exact());
                matcher.withMatcher("poi", ExampleMatcher.GenericPropertyMatchers.exact());
                matcher.withMatcher("exhibition", ExampleMatcher.GenericPropertyMatchers.exact());
                matcher.withMatcher("beacon", ExampleMatcher.GenericPropertyMatchers.exact());

        return super.getModels(matcher, example, Layout.class, RsLayout.class, userName, "getLayouts");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsLayout> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsLayout rsLayout) {
        return super.updateEntity(rsLayout, Layout.class, userName, id,"updateLayout");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsLayout> deleteLayout(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsLayout.class, Layout.class, userName, id, "deleteLayout");
    }

    //Specific requests
    @GetMapping(path = "/beacon/{uuid}")
    public ResponseEntity<RsLayout> getLayoutByBeaconUUID(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "uuid") String uuid) {
        return super.getLayoutByBeaconUUID(uuid, userName);
    }

}
