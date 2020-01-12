package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dao.entities.HMuseum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeaconRepository extends JpaRepository<HBeacon, Integer> {
    HBeacon findByUuidEquals(final String uuid);
}
