package hu.imsi.mir;

import hu.imsi.mir.common.Content;
import hu.imsi.mir.dao.entities.HContent;
import hu.imsi.mir.dto.RsContent;
import hu.imsi.mir.dto.RsNavigationPoint;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static hu.imsi.mir.utils.Constants.USER_NAME;

@RestController
@RequestMapping("/api/navigation")
public class NavigationResource extends BaseResource{


    @GetMapping(path = "{from}/{to}")
    public ResponseEntity<RsNavigationPoint> getNavigation(@RequestHeader(USER_NAME) String userName,
                                                           @PathVariable(value = "from") Integer from,
                                                           @PathVariable(value = "to") Integer to) {
        return super.getNavigation(userName, from, to);
    }



}
