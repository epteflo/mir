package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceLogRepository extends JpaRepository<HServiceLog, Integer> {
}
