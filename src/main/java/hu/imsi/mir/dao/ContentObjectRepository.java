package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HContentObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentObjectRepository extends JpaRepository<HContentObject, Integer> {
}