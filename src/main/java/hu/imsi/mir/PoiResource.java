package hu.imsi.mir;

import hu.imsi.mir.common.Poi;
import hu.imsi.mir.dao.entities.HPoi;
import hu.imsi.mir.dto.RsPoi;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/pois")
public class PoiResource extends BaseResource{
    @Autowired
    private ServiceRegistry serviceRegistry;

    @PostMapping()
    public ResponseEntity<RsPoi> createPoi(@RequestHeader(USER_NAME) String userName,
                                             @RequestBody final RsPoi rsPoi) {
        return super.createEntity(rsPoi, Poi.class, userName, "createPoi");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RsPoi> getPoi(@RequestHeader(USER_NAME) String userName,
                                          @PathVariable(value = "id") Integer id) {
        return super.getModel(RsPoi.class, Poi.class, userName, id, "getPoi");
    }

    @GetMapping()
    public ResponseEntity<List<RsPoi>> getPois(@RequestHeader(USER_NAME) String userName,
                                                     @RequestParam(value = "name", required = false) final String name,
                                                     @RequestParam(value = "type", required = false) final String type,
                                                     @RequestParam(value = "shortDesc", required = false) final String shortDesc,
                                                     @RequestParam(value = "description", required = false) final String description,
                                                     @RequestParam(value = "category", required = false) final String category,
                                                     @RequestParam(value = "style", required = false) final String style,
                                                     @RequestParam(value = "author", required = false) final String author,
                                                     @RequestParam(value = "age", required = false) final String age,
                                                     @RequestParam(value = "creationPlace", required = false) final String creationPlace,
                                                     @RequestParam(value = "material", required = false) final String material)
    {

        final HPoi example = new HPoi();
        example.setName(name);
        example.setType(type);
        example.setShortDesc(shortDesc);
        example.setDescription(description);
        example.setCategory(category);
        example.setStyle(style);
        example.setAuthor(author);
        example.setAge(age);
        example.setCreationPlace(creationPlace);
        example.setMaterial(material);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("shortDesc", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("style", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("age", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("creationPlace", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("material", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                ;

        return super.getModels(matcher, example, Poi.class, RsPoi.class, userName, "getPois");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsPoi> update(@RequestHeader(USER_NAME) String userName,
                                         @PathVariable(value = "id") Integer id,
                                         @RequestBody final RsPoi rsPoi) {
        return super.updateEntity(rsPoi, Poi.class, userName, id,"updatePoi");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsPoi> deletePoi(@RequestHeader(USER_NAME) String userName,
                                             @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsPoi.class, Poi.class, userName, id, "deletePoi");
    }

    //Specific requests
    @GetMapping(path = "/museum/{id}")
    public ResponseEntity<List<RsPoi>> getPoisByMuseumId(@RequestHeader(USER_NAME) String userName,
                                                          @PathVariable(value = "id") Integer id) {
        return super.getPoisByMuseumId(id, userName);
    }

    @GetMapping(path = "/room/{id}")
    public ResponseEntity<List<RsPoi>> getPoisByRoomId(@RequestHeader(USER_NAME) String userName,
                                                         @PathVariable(value = "id") Integer id) {
        return super.getPoisByRoomId(id, userName);
    }

    @GetMapping(path = "/beacon/{uuid}")
    public ResponseEntity<RsPoi> getPoiByBeaconUUID(@RequestHeader(USER_NAME) String userName,
                                                          @PathVariable(value = "uuid") String uuid) {
        return super.getPoiByBeaconUUID(uuid, userName);
    }

}
