package hu.imsi.mir;

import hu.imsi.mir.common.Content;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.RsContent;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/contents")
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
                                                     @RequestParam(value = "name", required = false) final String name,
                                                     @RequestParam(value = "type", required = false) final String type,
                                                     @RequestParam(value = "description", required = false) final String description) {

        final HContent example = new HContent();
        example.setName(name);
        example.setType(type);
        example.setDescription(description);

        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

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



}
