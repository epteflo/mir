package hu.imsi.mir;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsMuseum;
import hu.imsi.mir.mappers.MuseumMapper;
import hu.imsi.mir.service.ManagementServiceHandler;
import hu.imsi.mir.service.OperationServiceHandler;
import hu.imsi.mir.utils.LoggerServiceHandler;
import hu.imsi.mir.utils.ServiceHelper;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static hu.imsi.mir.utils.Constants.USER_NAME;
import static hu.imsi.mir.utils.Constants.SERVICE_CALLED;

@RestController
@RequestMapping("/api/museums")
public class MuseumResource {
    @Autowired
    private MuseumRepository museumRepository;

    @Autowired
    private MuseumMapper museumMapper;

    @Autowired
    private OperationServiceHandler operationServiceHandler;

    @Autowired
    private ManagementServiceHandler managementServiceHandler;

    @Autowired
    private LoggerServiceHandler loggerServiceHandler;

    @PostMapping()
    public ResponseEntity<RsMuseum> createMuseum(@RequestHeader(USER_NAME) String userName,
                                                 @RequestBody final RsMuseum rsMuseum) {
        loggerServiceHandler.logStart(userName,SERVICE_CALLED, this.getClass().getName(), "createMuseum");
        final Museum inner = museumMapper.toInner(rsMuseum);
        final Museum storedInner = managementServiceHandler.createMuseum(inner);
        return ServiceHelper.createResponse(museumMapper.toDto(storedInner));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RsMuseum> getMuseum(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {
        final Optional<Museum> storedInner = managementServiceHandler.getMuseum(id);
        if (!storedInner.isPresent()) return ResponseEntity.notFound().build();
        return ServiceHelper.createResponse(museumMapper.toDto(storedInner.get()));
    }

    @GetMapping()
    public List<RsMuseum> getMuseums(@RequestHeader(USER_NAME) String userName,
                                     @RequestParam(value = "name", required = false) final String name,
                                     @RequestParam(value = "uuid", required = false) final String uuid) {
        final HMuseum example = new HMuseum();
        example.setName(name);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());
        return museumMapper.toDtoList(museumRepository.findAll(Example.of(example, matcher)));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsMuseum> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody final RsMuseum rsMuseum) {
        final Museum museum = museumMapper.toInner(rsMuseum);
        final Optional<Museum> storedInner = managementServiceHandler.updateMusem(id, museum);
        if (!storedInner.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(museumMapper.toDto(storedInner.get()));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<RsMuseum> deleteMuseum(@RequestHeader(USER_NAME) String userName,
                                              @PathVariable(value = "id") Integer id) {

        final Optional<Museum> storedInner = managementServiceHandler.deleteMuseum(id);
        if (!storedInner.isPresent()) return ResponseEntity.notFound().build();
        return ServiceHelper.createResponse(museumMapper.toDto(storedInner.get()));
    }

}
