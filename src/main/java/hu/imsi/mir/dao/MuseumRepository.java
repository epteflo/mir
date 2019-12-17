package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HMuseum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuseumRepository extends JpaRepository<HMuseum, Integer> {
    List<HMuseum> findAllByNameStartsWithIgnoreCase(final String name);
}
