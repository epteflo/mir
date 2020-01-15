package hu.imsi.mir.configuration;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.dto.RsMuseum;
import hu.imsi.mir.dto.RsRoom;
import hu.imsi.mir.mappers.Converter;
import hu.imsi.mir.mappers.ConverterRegistry;
import hu.imsi.mir.mappers.MuseumMapper;
import hu.imsi.mir.mappers.RoomMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConverterConfiguration {

    @Bean
    public Converter<Museum, RsMuseum> toDto(final MuseumMapper museumMapper) {
        return new Converter<>(Museum.class, RsMuseum.class, museumMapper::toDto, null, museumMapper::toDtoList);
    }
    @Bean
    public Converter<HMuseum, Museum> toInner(final MuseumMapper museumMapper) {
        return new Converter<>(HMuseum.class, Museum.class, museumMapper::toInner, null, museumMapper::toInnerList);
    }
    @Bean
    public Converter<Museum, HMuseum> toEntity(final MuseumMapper museumMapper) {
        return new Converter<>(Museum.class, HMuseum.class, museumMapper::toEntity, museumMapper::mergeOnto, museumMapper::toEntityList);
    }
    @Bean
    public Converter<RsMuseum, Museum> toInnerIn(final MuseumMapper museumMapper) {
        return new Converter<>(RsMuseum.class, Museum.class, museumMapper::toInnerIn, null, museumMapper::toInnerInList);
    }

    @Bean
    public Converter<Room, RsRoom> toRDto(final RoomMapper roomMapper) {
        return new Converter<>(Room.class, RsRoom.class, roomMapper::toDto, null, roomMapper::toDtoList);
    }
    @Bean
    public Converter<HRoom, Room> toRInner(final RoomMapper roomMapper) {
        return new Converter<>(HRoom.class, Room.class, roomMapper::toInner, null, roomMapper::toInnerList);
    }
    @Bean
    public Converter<Room, HRoom> toREntity(final RoomMapper roomMapper) {
        return new Converter<>(Room.class, HRoom.class, roomMapper::toEntity, roomMapper::mergeOnto, roomMapper::toEntityList);
    }
    @Bean
    public Converter<RsRoom, Room> toRInnerIn(final RoomMapper roomMapper) {
        return new Converter<>(RsRoom.class, Room.class, roomMapper::toInnerIn, null, roomMapper::toInnerInList);
    }





    @Bean
    public ConverterRegistry converterRegistry(final List<Converter<?, ?>> converters) {
        return new ConverterRegistry(converters);
    }
}
