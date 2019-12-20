package hu.imsi.mir.mappers;

import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.RsMuseum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuseumMapper {
    RsMuseum toDto(HMuseum entity);
    List<RsMuseum> toDtoList(List<HMuseum> entities);
    HMuseum toEntity(RsMuseum dto);

    @Mapping(ignore = true, target = "id")
    HMuseum mergeOnto(RsMuseum dto, @MappingTarget HMuseum target);

    List<hu.imsi.mir.museums.WsMuseum> toWsList(List<HMuseum> entities);
}
