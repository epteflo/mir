package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {

    RsRoom toDto(Room inner);

    @Mapping(target="museumId", expression = "java( entity.getMuseum().getId() )")
    Room toInner(HRoom entity);

    @Mapping(target="museum", expression = "java( hu.imsi.mir.utils.MapperHelper.getMuseum(inner.getMuseumId()) )")
    HRoom toEntity(Room inner);
    Room toInnerIn(RsRoom dto);

    @Mapping(ignore = true, target = "id")
    HRoom mergeOnto(Room inner, @MappingTarget HRoom target);
}
