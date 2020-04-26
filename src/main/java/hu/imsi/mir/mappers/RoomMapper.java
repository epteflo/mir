package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Room;
import hu.imsi.mir.common.Wall;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dao.entities.HWall;
import hu.imsi.mir.dto.RsRoom;
import hu.imsi.mir.dto.RsWall;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {

    @IterableMapping(qualifiedByName="mapWithResponseStatus")
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

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsWall mapWithoutResponseStatus(Wall inner);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsWall> toWallDtoList(List<Wall> entities);


    //Wall-hoz
    RsWall toDto(Wall inner);
    @Mapping(target="roomId", expression = "java( entity.getRoom().getId() )")
    Wall toInner(HWall entity);

    @Mapping(target="room", expression = "java( inner.getRoomId()==null?null:hu.imsi.mir.utils.MapperHelper.getRoom(inner.getRoomId()) )")
    HWall toEntity(Wall inner);

    @Mapping(ignore = true, target = "roomId")
    Wall toInnerIn(RsWall dto);

    @Mapping(ignore = true, target = "id")
    HWall mergeOnto(Wall inner, @MappingTarget HWall target);


    @AfterMapping
    default void updateWallParent(Room source, @MappingTarget HRoom target) {
        target.getWalls().forEach(l -> l.setRoom(target));
    }
}
