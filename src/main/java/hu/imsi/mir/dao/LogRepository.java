package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<HLog, Integer> {
}
