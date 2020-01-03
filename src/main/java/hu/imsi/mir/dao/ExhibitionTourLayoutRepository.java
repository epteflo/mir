package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HExhibitionTourLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionTourLayoutRepository extends JpaRepository<HExhibitionTourLayout, Integer> {
}
