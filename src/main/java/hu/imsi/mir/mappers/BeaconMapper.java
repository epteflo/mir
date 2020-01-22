package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Beacon;
import hu.imsi.mir.dao.entities.HBeacon;
import hu.imsi.mir.dto.RsBeacon;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface BeaconMapper {

    RsBeacon toDto(Beacon inner);
    Beacon toInner(HBeacon entity);

    @Mapping(target="uuid", source="uuid", defaultExpression = "java( UUID.randomUUID().toString() )")
    HBeacon toEntity(Beacon inner);
    Beacon toInnerIn(RsBeacon dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsBeacon> toDtoList(List<Beacon> entities);
    List<Beacon> toInnerList(List<HBeacon> entities);

    List<HBeacon> toEntityList(List<Beacon> entities);
    List<Beacon> toInnerInList(List<RsBeacon> entities);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "uuid")
    HBeacon mergeOnto(Beacon inner, @MappingTarget HBeacon target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsBeacon mapWithoutResponseStatus(Beacon inner);
}
