package hu.imsi.mir.mappers;

import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dto.Museum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuseumMapper {
    Museum toDto(HMuseum entity);
    List<Museum> toDtoList(List<HMuseum> entities);
    HMuseum toEntity(Museum dto);

    @Mapping(ignore = true, target = "id")
    HMuseum mergeOnto(Museum dto, @MappingTarget HMuseum target);

    List<hu.imsi.mir.museums.WsMuseum> toWsList(List<HMuseum> entities);
}
