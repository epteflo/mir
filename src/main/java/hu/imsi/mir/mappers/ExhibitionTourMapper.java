package hu.imsi.mir.mappers;

import hu.imsi.mir.common.ExhibitionTour;
import hu.imsi.mir.common.ExhibitionTourLayout;
import hu.imsi.mir.dao.entities.HExhibitionTour;
import hu.imsi.mir.dao.entities.HExhibitionTourLayout;
import hu.imsi.mir.dto.RsExhibitionTour;
import hu.imsi.mir.dto.RsExhibitionTourLayout;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExhibitionTourMapper {

    RsExhibitionTour toDto(ExhibitionTour inner);
    @Mapping(target="exhibitionId", expression = "java( entity.getExhibition().getId() )")
    ExhibitionTour toInner(HExhibitionTour entity);

    @Mapping(target="exhibition", expression = "java( hu.imsi.mir.utils.MapperHelper.getExhibition(inner.getExhibitionId()) )")
    HExhibitionTour toEntity(ExhibitionTour inner);
    @Mapping(ignore = true, target = "id")
    ExhibitionTour toInnerIn(RsExhibitionTour dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsExhibitionTour> toDtoList(List<ExhibitionTour> entities);
    List<ExhibitionTour> toInnerList(List<HExhibitionTour> entities);

    List<HExhibitionTour> toEntityList(List<ExhibitionTour> entities);
    List<ExhibitionTour> toInnerInList(List<RsExhibitionTour> entities);

    @Mapping(ignore = true, target = "id")
    HExhibitionTour mergeOnto(ExhibitionTour inner, @MappingTarget HExhibitionTour target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsExhibitionTour mapWithoutResponseStatus(ExhibitionTour inner);
    @Mapping(ignore = true, target = "responseStatus")
    RsExhibitionTourLayout mapWithoutResponseStatus(ExhibitionTourLayout inner);




    //ExhibitionTourLayout-hoz
   // RsExhibitionTourLayout toDto(ExhibitionTourLayout inner);
    @Mapping(target="exhibitionTourId", expression = "java( entity.getExhibitionTour().getId() )")
    @Mapping(target="layoutId", expression = "java( entity.getLayout().getId() )")
    ExhibitionTourLayout toInner(HExhibitionTourLayout entity);

    @Mapping(target="exhibitionTour", expression = "java( inner.getExhibitionTourId()==null?null:hu.imsi.mir.utils.MapperHelper.getExhibitionTour(inner.getExhibitionTourId()) )")
    @Mapping(target="layout", expression = "java( hu.imsi.mir.utils.MapperHelper.getLayout(inner.getLayoutId()) )")
    HExhibitionTourLayout toEntity(ExhibitionTourLayout inner);
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "exhibitionTourId")
    ExhibitionTourLayout toInnerIn(RsExhibitionTourLayout dto);

    @Mapping(ignore = true, target = "id")
    HExhibitionTourLayout mergeOnto(ExhibitionTourLayout inner, @MappingTarget HExhibitionTourLayout target);

}
