package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Poi;
import hu.imsi.mir.dao.entities.HPoi;
import hu.imsi.mir.dto.RsPoi;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PoiMapper {

    RsPoi toDto(Poi inner);
    Poi toInner(HPoi entity);

    HPoi toEntity(Poi inner);
    @Mapping(ignore = true, target = "id")
    Poi toInnerIn(RsPoi dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsPoi> toDtoList(List<Poi> entities);
    List<Poi> toInnerList(List<HPoi> entities);

    List<HPoi> toEntityList(List<Poi> entities);
    List<Poi> toInnerInList(List<RsPoi> entities);

    @Mapping(ignore = true, target = "id")
    HPoi mergeOnto(Poi inner, @MappingTarget HPoi target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsPoi mapWithoutResponseStatus(Poi inner);
}
