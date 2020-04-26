package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallRepository extends JpaRepository<HWall, Integer> {
}
