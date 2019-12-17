package hu.imsi.mir;

import hu.imsi.mir.dao.BeaconRepository;
import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dto.Beacon;
import hu.imsi.mir.mappers.BeaconMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beacons")
public class BeaconResource {
    @Autowired
    private BeaconRepository beaconRepository;

    @Autowired
    private BeaconMapper beaconMapper;

    @PostMapping()
    public ResponseEntity<Beacon> createBeacon(@RequestBody final Beacon museum) {
        final HBeacon entity = beaconMapper.toEntity(museum);
        final HBeacon stored = beaconRepository.saveAndFlush(entity);
        return ResponseEntity.ok(beaconMapper.toDto(stored));
    }

    @GetMapping()
    public List<Beacon> getBeacons() {
        return beaconMapper.toDtoList(beaconRepository.findAll());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Beacon> update(@PathVariable(value = "id") Integer id, @RequestBody final Beacon beacon) {
        final Optional<HBeacon> hBeacon = beaconRepository.findById(id);
        if (!hBeacon.isPresent()) return ResponseEntity.notFound().build();
        final HBeacon m = hBeacon.get();
        beaconMapper.mergeOnto(beacon, m);
        return ResponseEntity.ok(beaconMapper.toDto(beaconRepository.saveAndFlush(m)));
    }

}
