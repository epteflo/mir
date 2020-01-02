package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HContent;
import hu.imsi.mir.dao.entities.HDoor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository extends JpaRepository<HDoor, Integer> {
}
