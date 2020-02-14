package hu.imsi.mir.mappers;

import hu.imsi.mir.common.ContentObject;
import hu.imsi.mir.dao.entities.HContentObject;
import hu.imsi.mir.dto.RsContentObject;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContentObjectMapper {

    RsContentObject toDto(ContentObject inner);
    @Mapping(target="roomId", expression = "java( entity.getRoom()==null?null:entity.getRoom().getId() )")
    @Mapping(target="poiId", expression = "java( entity.getPoi()==null?null:entity.getPoi().getId() )")
    @Mapping(target="museumId", expression = "java( entity.getMuseum()==null?null:entity.getMuseum().getId() )")
    @Mapping(target="contentId", expression = "java( entity.getContent().getId() )")
    ContentObject toInner(HContentObject entity);

    @Mapping(target="room", expression = "java( hu.imsi.mir.utils.MapperHelper.getRoom(inner.getRoomId()) )")
    @Mapping(target="poi", expression = "java( hu.imsi.mir.utils.MapperHelper.getPoi(inner.getPoiId()) )")
    @Mapping(target="museum", expression = "java( hu.imsi.mir.utils.MapperHelper.getMuseum(inner.getMuseumId()) )")
    @Mapping(target="content", expression = "java( hu.imsi.mir.utils.MapperHelper.getContent(inner.getContentId()) )")
    HContentObject toEntity(ContentObject inner);
    @Mapping(ignore = true, target = "id")
    ContentObject toInnerIn(RsContentObject dto);

    @IterableMapping(qualifiedByName="mapWithoutResponseStatus")
    List<RsContentObject> toDtoList(List<ContentObject> entities);
    List<ContentObject> toInnerList(List<HContentObject> entities);

    List<HContentObject> toEntityList(List<ContentObject> entities);
    List<ContentObject> toInnerInList(List<RsContentObject> entities);

    @Mapping(ignore = true, target = "id")
    HContentObject mergeOnto(ContentObject inner, @MappingTarget HContentObject target);

    @Named("mapWithoutResponseStatus")
    @Mapping(ignore = true, target = "responseStatus")
    RsContentObject mapWithoutResponseStatus(ContentObject inner);
}
