package hu.imsi.mir;

import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsMuseum;
import hu.imsi.mir.mappers.MuseumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/museums")
public class MuseumResource {
    @Autowired
    private MuseumRepository museumRepository;

    @Autowired
    private MuseumMapper museumMapper;

    @PostMapping()
    public ResponseEntity<RsMuseum> createMuseum(@RequestBody final RsMuseum museum) {
        final HMuseum entity = museumMapper.toEntity(museum);
        final HMuseum stored = museumRepository.saveAndFlush(entity);
        return ResponseEntity.ok(museumMapper.toDto(stored));
    }

    @GetMapping()
    public List<RsMuseum> getMuseums(@RequestParam(value = "name", required = false) final String name) {
        final HMuseum example = new HMuseum();
        example.setName(name);
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());
        return museumMapper.toDtoList(museumRepository.findAll(Example.of(example, matcher)));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsMuseum> update(@PathVariable(value = "id") Integer id, @RequestBody final RsMuseum museum) {
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        if (!hMuseum.isPresent()) return ResponseEntity.notFound().build();
        final HMuseum m = hMuseum.get();
        museumMapper.mergeOnto(museum, m);
        return ResponseEntity.ok(museumMapper.toDto(museumRepository.saveAndFlush(m)));
    }

}
