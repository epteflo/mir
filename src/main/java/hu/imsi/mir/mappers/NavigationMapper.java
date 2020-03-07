package hu.imsi.mir.mappers;

import hu.imsi.mir.common.NavigationPoint;
import hu.imsi.mir.dto.RsNavigationPoint;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NavigationMapper {

    RsNavigationPoint toDto(NavigationPoint inner);
    NavigationPoint toInnerIn(RsNavigationPoint dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsNavigationPoint> toDtoList(List<NavigationPoint> entities);

    List<NavigationPoint> toInnerInList(List<RsNavigationPoint> entities);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsNavigationPoint mapWithoutResponseStatus(NavigationPoint inner);
}
