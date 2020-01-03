package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends JpaRepository<HExhibition, Integer> {
}
