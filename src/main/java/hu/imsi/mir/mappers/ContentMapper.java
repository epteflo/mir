package hu.imsi.mir.mappers;

import hu.imsi.mir.common.Content;
import hu.imsi.mir.dao.entities.HContent;
import hu.imsi.mir.dto.RsContent;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContentMapper {

    RsContent toDto(Content inner);
    Content toInner(HContent entity);

    HContent toEntity(Content inner);
    @Mapping(ignore = true, target = "id")
    Content toInnerIn(RsContent dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsContent> toDtoList(List<Content> entities);
    List<Content> toInnerList(List<HContent> entities);

    List<HContent> toEntityList(List<Content> entities);
    List<Content> toInnerInList(List<RsContent> entities);

    @Mapping(ignore = true, target = "id")
    HContent mergeOnto(Content inner, @MappingTarget HContent target);


    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsContent mapWithoutResponseStatus(Content inner);
}
