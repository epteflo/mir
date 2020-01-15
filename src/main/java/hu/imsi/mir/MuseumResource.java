package hu.imsi.mir;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsMuseum;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.SERVICE_CALLED;
import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/museums")
public class MuseumResource extends BaseResource{


    @PostMapping()
    public ResponseEntity<RsMuseum> createMuseum(@RequestHeader(USER_NAME) String userName,
                                                 @RequestBody final RsMuseum rsMuseum) {
        return super.createEntity(rsMuseum, Museum.class, userName, "createMuseum");
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<RsMuseum> getMuseum(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsMuseum.class, Museum.class, userName, id, "getMuseum");
    }

    @GetMapping()
    public ResponseEntity<List<RsMuseum>> getMuseums(@RequestHeader(USER_NAME) String userName,
                                     @RequestParam(value = "name", required = false) final String name,
                                     @RequestParam(value = "description", required = false) final String description) {

        final HMuseum example = new HMuseum();
        example.setName(name);
        example.setDescription(description);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return super.getModels(matcher, example, Museum.class, RsMuseum.class, userName, "getMuseums");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsMuseum> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsMuseum rsMuseum) {
        return super.updateEntity(rsMuseum, Museum.class, userName, id,"updateMuseum");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsMuseum> deleteMuseum(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsMuseum.class, Museum.class, userName, id, "deleteMuseum");
    }

    //Specific requests
    @GetMapping(path = "/beacon/{uuid}")
    public ResponseEntity<RsMuseum> getMuseumByBeaconUUID(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "uuid") String uuid) {
        return super.getMuseumByBeaconUUID(uuid, userName);
    }

}
