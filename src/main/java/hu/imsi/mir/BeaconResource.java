package hu.imsi.mir;

import hu.imsi.mir.dao.BeaconRepository;
import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dto.RsBeacon;
import hu.imsi.mir.mappers.BeaconMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<RsBeacon> createBeacon(@RequestBody final RsBeacon museum) {
        final HBeacon entity = beaconMapper.toEntity(museum);
        final HBeacon stored = beaconRepository.saveAndFlush(entity);
        return ResponseEntity.ok(beaconMapper.toDto(stored));
    }

    @GetMapping()
    public List<RsBeacon> getBeacons() {
        return beaconMapper.toDtoList(beaconRepository.findAll());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RsBeacon> update(@PathVariable(value = "id") Integer id, @RequestBody final RsBeacon beacon) {
        final Optional<HBeacon> hBeacon = beaconRepository.findById(id);
        if (!hBeacon.isPresent()) return ResponseEntity.notFound().build();
        final HBeacon m = hBeacon.get();
        beaconMapper.mergeOnto(beacon, m);
        return ResponseEntity.ok(beaconMapper.toDto(beaconRepository.saveAndFlush(m)));
    }

}
