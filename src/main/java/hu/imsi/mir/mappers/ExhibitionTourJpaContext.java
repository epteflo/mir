package hu.imsi.mir.mappers;

import hu.imsi.mir.dao.entities.HExhibitionTour;
import hu.imsi.mir.dao.entities.HExhibitionTourLayout;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

import javax.persistence.EntityManager;

public class ExhibitionTourJpaContext {
    private final EntityManager em;
    private HExhibitionTour tour;

    public ExhibitionTourJpaContext(EntityManager em) {
        this.em = em;
    }

    @BeforeMapping
    public void setEntity(@MappingTarget HExhibitionTour tour) {
        this.tour = tour;
        // you could do stuff with the EntityManager here
    }

    @AfterMapping
    public void establishRelation(@MappingTarget HExhibitionTourLayout layout) {
        layout.setExhibitionTour( tour);
        // you could do stuff with the EntityManager here
    }}
