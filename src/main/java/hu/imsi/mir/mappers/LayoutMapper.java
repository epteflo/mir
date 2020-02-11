package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Layout;
import hu.imsi.mir.dao.entities.HLayout;
import hu.imsi.mir.dto.RsLayout;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LayoutMapper {

    RsLayout toDto(Layout inner);
    @Mapping(target="roomId", expression = "java( entity.getRoom()==null?null:entity.getRoom().getId() )")
    @Mapping(target="poiId", expression = "java( entity.getPoi()==null?null:entity.getPoi().getId() )")
    @Mapping(target="beaconId", expression = "java( entity.getBeacon()==null?null:entity.getBeacon().getId() )")
    @Mapping(target="exhibitionId", expression = "java( entity.getExhibition()==null?null:entity.getExhibition().getId() )")
    Layout toInner(HLayout entity);

    @Mapping(target="room", expression = "java( hu.imsi.mir.utils.MapperHelper.getRoom(inner.getRoomId()) )")
    @Mapping(target="poi", expression = "java( hu.imsi.mir.utils.MapperHelper.getPoi(inner.getPoiId()) )")
    @Mapping(target="beacon", expression = "java( hu.imsi.mir.utils.MapperHelper.getBeacon(inner.getBeaconId()) )")
    @Mapping(target="exhibition", expression = "java( hu.imsi.mir.utils.MapperHelper.getExhibition(inner.getExhibitionId()) )")
    HLayout toEntity(Layout inner);
    @Mapping(ignore = true, target = "id")
    Layout toInnerIn(RsLayout dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsLayout> toDtoList(List<Layout> entities);
    List<Layout> toInnerList(List<HLayout> entities);

    List<HLayout> toEntityList(List<Layout> entities);
    List<Layout> toInnerInList(List<RsLayout> entities);

    @Mapping(ignore = true, target = "id")
    HLayout mergeOnto(Layout inner, @MappingTarget HLayout target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsLayout mapWithoutResponseStatus(Layout inner);
}
