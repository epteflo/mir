package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsRoom;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {

    RsRoom toDto(Room inner);
    @Mapping(target="museumId", expression = "java( entity.getMuseum().getId() )")
    Room toInner(HRoom entity);

    @Mapping(target="museum", expression = "java( hu.imsi.mir.utils.MapperHelper.getMuseum(inner.getMuseumId()) )")
    HRoom toEntity(Room inner);
    @Mapping(ignore = true, target = "id")
    Room toInnerIn(RsRoom dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsRoom> toDtoList(List<Room> entities);
    List<Room> toInnerList(List<HRoom> entities);

    List<HRoom> toEntityList(List<Room> entities);
    List<Room> toInnerInList(List<RsRoom> entities);

    @Mapping(ignore = true, target = "id")
    HRoom mergeOnto(Room inner, @MappingTarget HRoom target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsRoom mapWithoutResponseStatus(Room inner);
}
