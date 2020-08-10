package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.*;
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
    public List<HLayout> findLayoutsCustom(Integer roomId, Integer museumId, Integer beaconId, Integer exhibitionId, Integer poiId,
                                           String poiName, String poiType, String poiShortDesc, String poiDescription, String poiCategory, String poiStyle,
                                           String poiAuthor, String poiAge, String poiCreationPlace, String poiMaterial) {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<HLayout> q = builder.createQuery(HLayout.class);
        final Root<HLayout> root = q.from(HLayout.class);
        final List<Predicate> predicates = new ArrayList<>();

        if(museumId != null) {
            final Join<HLayout, HRoom> room = root.join(HLayout_.room);
            predicates.add(builder.equal(room.get(HRoom_.museum), museumId));
        }

        if (roomId != null) {
            predicates.add(builder.equal(root.get(HLayout_.room), roomId));
        }

        if (beaconId != null) {
            predicates.add(builder.equal(root.get(HLayout_.beacon), exhibitionId));
        }

        if (exhibitionId != null) {
            predicates.add(builder.equal(root.get(HLayout_.exhibition), exhibitionId));
        }

        if (!StringUtils.isEmpty(poiName) ||
                !StringUtils.isEmpty(poiType) ||
                !StringUtils.isEmpty(poiShortDesc) ||
                !StringUtils.isEmpty(poiDescription) ||
                !StringUtils.isEmpty(poiCategory) ||
                !StringUtils.isEmpty(poiStyle) ||
                !StringUtils.isEmpty(poiAuthor) ||
                !StringUtils.isEmpty(poiAge) ||
                !StringUtils.isEmpty(poiCreationPlace) ||
                !StringUtils.isEmpty(poiMaterial)) {

            final Join<HLayout, HPoi> poi = root.join(HLayout_.poi);
            if (!StringUtils.isEmpty(poiName)) predicates.add(builder.like(builder.lower(poi.get(HPoi_.name)), poiName.toLowerCase() + "%"));
            if (!StringUtils.isEmpty(poiType)) predicates.add(builder.equal(builder.lower(poi.get(HPoi_.type)), poiType.toLowerCase()));
            if (!StringUtils.isEmpty(poiShortDesc)) predicates.add(builder.like(builder.lower(poi.get(HPoi_.shortDesc)), "%" + poiShortDesc.toLowerCase() + "%"));
            if (!StringUtils.isEmpty(poiDescription)) predicates.add(builder.like(builder.lower(poi.get(HPoi_.description)), "%" + poiDescription.toLowerCase() + "%"));
            if (!StringUtils.isEmpty(poiCategory)) predicates.add(builder.equal(builder.lower(poi.get(HPoi_.category)), poiCategory.toLowerCase()));
            if (!StringUtils.isEmpty(poiStyle)) predicates.add(builder.equal(builder.lower(poi.get(HPoi_.style)), poiStyle.toLowerCase()));

            if (!StringUtils.isEmpty(poiAuthor)) predicates.add(builder.like(builder.lower(poi.get(HPoi_.author)), poiAuthor.toLowerCase() + "%"));
            if (!StringUtils.isEmpty(poiAge)) predicates.add(builder.like(builder.lower(poi.get(HPoi_.age)), poiAge.toLowerCase() + "%"));
            if (!StringUtils.isEmpty(poiCreationPlace)) predicates.add(builder.like(builder.lower(poi.get(HPoi_.creationPlace)), poiCreationPlace.toLowerCase() + "%"));
            if (!StringUtils.isEmpty(poiMaterial)) predicates.add(builder.like(builder.lower(poi.get(HPoi_.material)), poiMaterial.toLowerCase() + "%"));

            q.orderBy(builder.asc(poi.get(HPoi_.name)));
        }

        if(poiId!=null) {
            predicates.add(builder.equal(root.get(HLayout_.poi), poiId));
        }

        q.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(q).getResultList();
    }
}
