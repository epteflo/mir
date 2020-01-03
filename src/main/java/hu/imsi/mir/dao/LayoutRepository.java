package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayoutRepository extends JpaRepository<HLayout, Integer> {
}
