package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsMuseum;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MuseumMapper {

    RsMuseum toDto(Museum inner);
    Museum toInner(HMuseum entity);

    HMuseum toEntity(Museum inner);
    Museum toInnerIn(RsMuseum dto);

    List<RsMuseum> toDtoList(List<HMuseum> entities);
    List<Museum> toInnerList(List<HMuseum> entities);

    List<HMuseum> toEntityList(List<Museum> entities);
    List<Museum> toInnerInList(List<RsMuseum> entities);

    @Mapping(ignore = true, target = "id")
    HMuseum mergeOnto(Museum inner, @MappingTarget HMuseum target);

    List<hu.imsi.mir.museums.WsMuseum> toWsList(List<HMuseum> entities);
}
