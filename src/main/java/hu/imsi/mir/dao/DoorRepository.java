package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HContent;
import hu.imsi.mir.dao.entities.HDoor;
import hu.imsi.mir.dao.entities.HRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoorRepository extends JpaRepository<HDoor, Integer> {

    List<HDoor> findAllByRoomAOrRoomB(final HRoom roomA, final HRoom roomB);
}
