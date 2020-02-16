package hu.imsi.mir;

import hu.imsi.mir.common.Exhibition;
import hu.imsi.mir.dao.entities.HExhibition;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsExhibition;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/exhibitions")
public class ExhibitionResource extends BaseResource{
    @Autowired
    private ServiceRegistry serviceRegistry;

    @PostMapping()
    public ResponseEntity<RsExhibition> createExhibition(@RequestHeader(USER_NAME) String userName,
                                             @RequestBody final RsExhibition rsExhibition) {
        return super.createEntity(rsExhibition, Exhibition.class, userName, "createExhibition");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RsExhibition> getExhibition(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsExhibition.class, Exhibition.class, userName, id, "getExhibition");
    }

    @GetMapping()
    public ResponseEntity<List<RsExhibition>> getExhibitions(@RequestHeader(USER_NAME) String userName,
                                                             @RequestParam(value = "name", required = false) final String name,
                                                             @RequestParam(value = "description", required = false) final String description,
                                                             @RequestParam(value = "type", required = false) final String type,
                                                             @RequestParam(value = "style", required = false) final String style,
                                                             @RequestParam(value = "museumId", required = false) final Integer museumId) {

        return getListResponseEntity(userName, name, description, type, style, museumId);
    }

    private ResponseEntity<List<RsExhibition>> getListResponseEntity(@RequestHeader(USER_NAME) String userName, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "style", required = false) String style, @RequestParam(value = "museumId", required = false) Integer museumId) {
        final HExhibition example = new HExhibition();
        example.setName(name);
        example.setDescription(description);
        example.setType(type);
        example.setStyle(style);
        HMuseum museum = getEntityById(museumId, HMuseum.class);
        if(museum==null && museumId!=null) return ResponseEntity.notFound().build();
        example.setMuseum(museum);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("style", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("museum", ExampleMatcher.GenericPropertyMatchers.exact());
        return super.getModels(matcher, example, Exhibition.class, RsExhibition.class, userName, "getExhibitions");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsExhibition> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsExhibition rsExhibition) {
        return super.updateEntity(rsExhibition, Exhibition.class, userName, id,"updateExhibition");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsExhibition> deleteExhibition(@RequestHeader(USER_NAME) String userName,
                                                 @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsExhibition.class, Exhibition.class, userName, id, "deleteExhibition");
    }

    //Specific requests
    @GetMapping(path = "/museum/{id}")
    public ResponseEntity<List<RsExhibition>> getExhibitionsByMuseumId(@RequestHeader(USER_NAME) String userName,
                                                            @PathVariable(value = "id") Integer id) {
        return getListResponseEntity(userName, null, null, null,null, id);
    }

}
