package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<HUser, Integer> {
    HUser findHUsersByName(final String name);
}
