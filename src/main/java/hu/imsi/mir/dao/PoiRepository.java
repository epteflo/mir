package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HPoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends JpaRepository<HPoi, Integer> {
}
