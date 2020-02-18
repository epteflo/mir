package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HLayout;
import hu.imsi.mir.dao.entities.HLayout_;
import hu.imsi.mir.dao.entities.HPoi;
import hu.imsi.mir.dao.entities.HPoi_;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LayoutRepositoryImpl implements CustomLayoutRepository {

    private final EntityManager em;

    public LayoutRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<HLayout> findLayoutsCustom(Integer roomId, String poiName) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<HLayout> q = builder.createQuery(HLayout.class);
        final Root<HLayout> root = q.from(HLayout.class);
        final List<Predicate> predicates = new ArrayList<>();
        if (roomId != null) {
            predicates.add(builder.equal(root.get(HLayout_.room), roomId));
        }
        if (!StringUtils.isEmpty(poiName)) {
            final Join<HLayout, HPoi> poi = root.join(HLayout_.poi);
            predicates.add(builder.like(builder.lower(poi.get(HPoi_.name)), poiName.toLowerCase() + "%"));
        }
        q.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(q).getResultList();
    }
}
