package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<HContent, Integer> {
}
