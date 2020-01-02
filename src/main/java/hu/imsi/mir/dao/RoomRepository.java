package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<HRoom, Integer> {
}
