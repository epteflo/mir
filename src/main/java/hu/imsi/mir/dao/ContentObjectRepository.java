package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HContentObject;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HPoi;
import hu.imsi.mir.dao.entities.HRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentObjectRepository extends JpaRepository<HContentObject, Integer> {
    List<HContentObject> findAllByRoom(HRoom room);
    HContentObject findByRoomAndName(HRoom room, String name);

    List<HContentObject> findAllByMusem(HMuseum museum);
    HContentObject findByMuseumAndName(HMuseum museum, String name);

    List<HContentObject> findAllByPoi(HPoi poi);
    HContentObject findByPoiAndName(HPoi poi, String name);
}
