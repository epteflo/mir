package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HExhibitionTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionTourRepository extends JpaRepository<HExhibitionTour, Integer> {
}
