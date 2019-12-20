package hu.imsi.mir.mappers;

import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dto.RsBeacon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface BeaconMapper {
    RsBeacon toDto(HBeacon entity);
    List<RsBeacon> toDtoList(List<HBeacon> entities);

    @Mapping(target="uuid", source="uuid", defaultExpression = "java( UUID.randomUUID().toString() )")
    HBeacon toEntity(RsBeacon dto);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "uuid")
    HBeacon mergeOnto(RsBeacon dto, @MappingTarget HBeacon target);
}
