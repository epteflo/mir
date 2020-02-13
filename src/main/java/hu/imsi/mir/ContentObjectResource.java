package hu.imsi.mir;

import hu.imsi.mir.common.ContentObject;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.RsContentObject;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/contentObjects")
public class ContentObjectResource extends BaseResource{


    @PostMapping()
    public ResponseEntity<RsContentObject> createContentObject(@RequestHeader(USER_NAME) String userName,
                                                 @RequestBody final RsContentObject rsContentObject) {
        return super.createEntity(rsContentObject, ContentObject.class, userName, "createContentObject");
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<RsContentObject> getContentObject(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsContentObject.class, ContentObject.class, userName, id, "getContentObject");
    }

    @GetMapping()
    public ResponseEntity<List<RsContentObject>> getContentObjects(@RequestHeader(USER_NAME) String userName,
                                                                   @RequestParam(value = "type", required = false) final String type,
                                                                   @RequestParam(value = "description", required = false) final String description,
                                                                   @RequestParam(value = "roomId", required = false) final Integer roomId,
                                                                   @RequestParam(value = "poiId", required = false) final Integer poiId,
                                                                   @RequestParam(value = "museumId", required = false) final Integer museumId,
                                                                   @RequestParam(value = "contentId", required = false) final Integer contentId) {

        final HContentObject example = new HContentObject();
        HRoom room = getEntityById(roomId, HRoom.class);
        HContent content = getEntityById(contentId, HContent.class);
        HMuseum museum = getEntityById(museumId, HMuseum.class);
        HPoi poi = getEntityById(poiId, HPoi.class);

        if((room==null && roomId !=null) || (content==null && contentId!=null) || (museum==null && museumId!=null) || (poi==null && poiId!=null)) return ResponseEntity.notFound().build();
        example.setRoom(room);
        example.setPoi(poi);
        example.setMuseum(museum);
        example.setContent(content);
        //example.setType(type);
        //example.setDescription(description);

        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("room", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("poi", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("museum", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.exact());

        return super.getModels(matcher, example, ContentObject.class, RsContentObject.class, userName, "getContentObjects");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsContentObject> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsContentObject rsContentObject) {
        return super.updateEntity(rsContentObject, ContentObject.class, userName, id,"updateContentObject");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsContentObject> deleteContentObject(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsContentObject.class, ContentObject.class, userName, id, "deleteContentObject");
    }

    //Specific requests

}
