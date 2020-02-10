package hu.imsi.mir;

import hu.imsi.mir.common.ExhibitionTour;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.RsExhibitionTour;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/exhibitionTour")
public class ExhibitionTourResource extends BaseResource{


    @PostMapping()
    public ResponseEntity<RsExhibitionTour> createExhibitionTour(@RequestHeader(USER_NAME) String userName,
                                                 @RequestBody final RsExhibitionTour rsExhibitionTour) {
        return super.createEntity(rsExhibitionTour, ExhibitionTour.class, userName, "createExhibitionTour");
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<RsExhibitionTour> getExhibitionTour(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getModel(RsExhibitionTour.class, ExhibitionTour.class, userName, id, "getExhibitionTour");
    }

    @GetMapping()
    public ResponseEntity<List<RsExhibitionTour>> getExhibitionTours(@RequestHeader(USER_NAME) String userName,
                                                     @RequestParam(value = "name", required = false) final String name,
                                                     @RequestParam(value = "description", required = false) final String description,
                                                     @RequestParam(value = "exhibitionId", required = false) final Integer exhibitionId) {

        final HExhibitionTour example = new HExhibitionTour();
        HExhibition exhibition = getEntityById(exhibitionId, HExhibition.class);


        if(exhibition==null && exhibitionId!=null) return ResponseEntity.notFound().build();
        example.setName(name);
        example.setDescription(description);
        example.setExhibition(exhibition);

        final ExampleMatcher matcher = ExampleMatcher.matchingAll();
                matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());
                matcher.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
                matcher.withMatcher("exhibition", ExampleMatcher.GenericPropertyMatchers.exact());

        return super.getModels(matcher, example, ExhibitionTour.class, RsExhibitionTour.class, userName, "getExhibitionTours");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsExhibitionTour> update(@RequestHeader(USER_NAME) String userName,
                                           @PathVariable(value = "id") Integer id,
                                           @RequestBody final RsExhibitionTour rsExhibitionTour) {
        return super.updateEntity(rsExhibitionTour, ExhibitionTour.class, userName, id,"updateExhibitionTour");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsExhibitionTour> deleteExhibitionTour(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.deleteEntity(RsExhibitionTour.class, ExhibitionTour.class, userName, id, "deleteExhibitionTour");
    }

    //Specific requests
    /*@GetMapping(path = "/room/{id}")
    public ResponseEntity<List<RsExhibitionTour>> getExhibitionTourByRoomId(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        return super.getExhibitionToursByRoomId(id, userName);
    }

    @GetMapping(path = "/museum/{id}")
    public ResponseEntity<List<RsExhibitionTour>> getPoisByMuseumId(@RequestHeader(USER_NAME) String userName,
                                                         @PathVariable(value = "id") Integer id) {
        return super.getExhibitionToursByMuseumId(id, userName);
    }*/

}
