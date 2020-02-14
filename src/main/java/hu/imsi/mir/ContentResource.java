package hu.imsi.mir;

import hu.imsi.mir.common.Content;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.RsContent;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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

    @PostMapping("/upload/{uuid}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file,
                                     @PathVariable(value = "uuid") String uuid,
                                     @RequestHeader(USER_NAME) String userName) {
        return super.saveMultipartFileByUUID(file, uuid, userName);
    }

    @GetMapping("/download/{uuid}")
    public ResponseEntity<Resource> downloadFile(@RequestHeader(USER_NAME) String userName,
                                                 @PathVariable String uuid,
                                                 HttpServletRequest request) {
        // Load file as Resource
        Resource resource = super.loadFileByUUID(uuid, userName);

        if(resource==null) return ResponseEntity.notFound().build();
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/withFile", method = RequestMethod.POST,
            consumes = {"multipart/form-data"}
             )
    @ResponseBody
    public ResponseEntity<RsContent> executeSampleService(
            @RequestPart("content") @Valid RsContent rsContent,
            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
            @RequestHeader(USER_NAME) String userName) {

        String url = super.saveMultipartFile(file, userName);
        rsContent.setInternalUrl(url);
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

    //Specific requests
    @GetMapping(path = "/uuid/{uuid}")
    public ResponseEntity<RsContent> getContentByUUID(@RequestHeader(USER_NAME) String userName,
                                                         @PathVariable(value = "uuid") String uuid) {
        return super.getContentByUUID(uuid, userName);
    }



}
