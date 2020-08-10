package hu.imsi.mir;

import hu.imsi.mir.common.Layout;
import hu.imsi.mir.dto.RsLayout;
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
                                                     @RequestParam(value = "museumId", required = false) final Integer museumId,
                                                     @RequestParam(value = "beaconId", required = false) final Integer beaconId,
                                                     @RequestParam(value = "exhibitionId", required = false) final Integer exhibitionId,
                                                     @RequestParam(value = "poiId", required = false) final Integer poiId,
                                                     @RequestParam(value = "poiName", required = false) final String poiName,
                                                     @RequestParam(value = "poiType", required = false) final String poiType,
                                                     @RequestParam(value = "poiShortDesc", required = false) final String poiShortDesc,
                                                     @RequestParam(value = "poiDescription", required = false) final String poiDescription,
                                                     @RequestParam(value = "poiCategory", required = false) final String poiCategory,
                                                     @RequestParam(value = "poiStyle", required = false) final String poiStyle,
                                                     @RequestParam(value = "author", required = false) final String poiAuthor,
                                                     @RequestParam(value = "age", required = false) final String poiAge,
                                                     @RequestParam(value = "creation_place", required = false) final String poiCreationPlace,
                                                     @RequestParam(value = "material", required = false) final String poiMaterial)
    {
        return super.getLayoutsCustom(userName, roomId, museumId, beaconId, exhibitionId,
                poiId, poiName, poiType, poiShortDesc, poiDescription, poiCategory, poiStyle, poiAuthor, poiAge, poiCreationPlace, poiMaterial);
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
    @GetMapping(path = "/room/{id}")
    public ResponseEntity<List<RsLayout>> getLayoutByRoomId(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getLayoutsByRoomId(id, userName);
    }

    @GetMapping(path = "/museum/{id}")
    public ResponseEntity<List<RsLayout>> getLayoutByMuseumId(@RequestHeader(USER_NAME) String userName,
                                                         @PathVariable(value = "id") Integer id) {
        return super.getLayoutsByMuseumId(id, userName);
    }

    @GetMapping(path = "/beacon/{uuid}")
    public ResponseEntity<RsLayout> getLayoutByBeaconUUID(@RequestHeader(USER_NAME) String userName,
                                                    @PathVariable(value = "uuid") String uuid) {
        return super.getLayoutByBeaconUUID(uuid, userName);
    }

}
