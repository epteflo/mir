package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsMuseum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuseumMapper extends BaseMapper{

    RsMuseum toDto(Museum inner);
    Museum toInner(HMuseum entity);

    HMuseum toEntity(Museum inner);
    Museum toInnerIn(RsMuseum dto);

    List<RsMuseum> toDtoList(List<HMuseum> entities);
    List<Museum> toInnerList(List<HMuseum> entities);

    @Mapping(ignore = true, target = "id")
    HMuseum mergeOnto(Museum inner, @MappingTarget HMuseum target);

    List<hu.imsi.mir.museums.WsMuseum> toWsList(List<HMuseum> entities);
}
