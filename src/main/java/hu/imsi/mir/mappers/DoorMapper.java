package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Door;
import hu.imsi.mir.dao.entities.HDoor;
import hu.imsi.mir.dto.RsDoor;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DoorMapper {

    RsDoor toDto(Door inner);
    @Mapping(target="roomAId", expression = "java( entity.getRoomA().getId() )")
    @Mapping(target="roomBId", expression = "java( entity.getRoomB().getId() )")
    Door toInner(HDoor entity);

    @Mapping(target="roomA", expression = "java( hu.imsi.mir.utils.MapperHelper.getRoom(inner.getRoomAId()) )")
    @Mapping(target="roomB", expression = "java( hu.imsi.mir.utils.MapperHelper.getRoom(inner.getRoomBId()) )")
    HDoor toEntity(Door inner);
    Door toInnerIn(RsDoor dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsDoor> toDtoList(List<Door> entities);
    List<Door> toInnerList(List<HDoor> entities);

    List<HDoor> toEntityList(List<Door> entities);
    List<Door> toInnerInList(List<RsDoor> entities);

    @Mapping(ignore = true, target = "id")
    HDoor mergeOnto(Door inner, @MappingTarget HDoor target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsDoor mapWithoutResponseStatus(Door inner);
}
