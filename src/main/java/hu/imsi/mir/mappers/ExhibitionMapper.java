package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Exhibition;
import hu.imsi.mir.dao.entities.HExhibition;
import hu.imsi.mir.dto.RsExhibition;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExhibitionMapper {

    RsExhibition toDto(Exhibition inner);
    @Mapping(target="museumId", expression = "java( entity.getMuseum().getId() )")
    Exhibition toInner(HExhibition entity);

    @Mapping(target="museum", expression = "java( hu.imsi.mir.utils.MapperHelper.getMuseum(inner.getMuseumId()) )")
    HExhibition toEntity(Exhibition inner);
    @Mapping(ignore = true, target = "id")
    Exhibition toInnerIn(RsExhibition dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsExhibition> toDtoList(List<Exhibition> entities);
    List<Exhibition> toInnerList(List<HExhibition> entities);

    List<HExhibition> toEntityList(List<Exhibition> entities);
    List<Exhibition> toInnerInList(List<RsExhibition> entities);

    @Mapping(ignore = true, target = "id")
    HExhibition mergeOnto(Exhibition inner, @MappingTarget HExhibition target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsExhibition mapWithoutResponseStatus(Exhibition inner);
}
