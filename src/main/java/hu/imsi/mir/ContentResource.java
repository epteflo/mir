package hu.imsi.mir;

import hu.imsi.mir.common.Content;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.RsContent;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/layouts")
public class ContentResource extends BaseResource{


    @PostMapping()
    public ResponseEntity<RsContent> createContent(@RequestHeader(USER_NAME) String userName,
                                                 @RequestBody final RsContent rsContent) {
        return super.createEntity(rsContent, Content.class, userName, "createContent");
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<RsContent> getContent(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsContent.class, Content.class, userName, id, "getContent");
    }

    @GetMapping()
    public ResponseEntity<List<RsContent>> getContents(@RequestHeader(USER_NAME) String userName,
                                                     @RequestParam(value = "roomId", required = false) final Integer roomId,
                                                     @RequestParam(value = "beaconId", required = false) final Integer beaconId,
                                                     @RequestParam(value = "exhibitionId", required = false) final Integer exhibitionId,
                                                     @RequestParam(value = "poiId", required = false) final Integer poiId) {

        final HContent example = new HContent();
        HRoom room = getEntityById(roomId, HRoom.class);
        HBeacon beacon = getEntityById(beaconId, HBeacon.class);
        HExhibition exhibition = getEntityById(exhibitionId, HExhibition.class);
        HPoi poi = getEntityById(poiId, HPoi.class);

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

        return super.getModels(matcher, example, Content.class, RsContent.class, userName, "getContents");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsContent> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsContent rsContent) {
        return super.updateEntity(rsContent, Content.class, userName, id,"updateContent");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsContent> deleteContent(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsContent.class, Content.class, userName, id, "deleteContent");
    }

    //Specific requests
    @GetMapping(path = "/room/{id}")
    public ResponseEntity<List<RsContent>> getContentByRoomId(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getContentsByRoomId(id, userName);
    }

    @GetMapping(path = "/museum/{id}")
    public ResponseEntity<List<RsContent>> getContentsByMuseumId(@RequestHeader(USER_NAME) String userName,
                                                         @PathVariable(value = "id") Integer id) {
        return super.getContentsByMuseumId(id, userName);
    }

}
