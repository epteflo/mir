package hu.imsi.mir.configuration;

import hu.imsi.mir.common.*;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.dto.*;
import hu.imsi.mir.mappers.*;
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
    public Converter<Door, RsDoor> toDDto(final DoorMapper doorMapper) {
        return new Converter<>(Door.class, RsDoor.class, doorMapper::toDto, null, doorMapper::toDtoList);
    }
    @Bean
    public Converter<HDoor, Door> toDInner(final DoorMapper doorMapper) {
        return new Converter<>(HDoor.class, Door.class, doorMapper::toInner, null, doorMapper::toInnerList);
    }
    @Bean
    public Converter<Door, HDoor> toDEntity(final DoorMapper doorMapper) {
        return new Converter<>(Door.class, HDoor.class, doorMapper::toEntity, doorMapper::mergeOnto, doorMapper::toEntityList);
    }
    @Bean
    public Converter<RsDoor, Door> toDInnerIn(final DoorMapper doorMapper) {
        return new Converter<>(RsDoor.class, Door.class, doorMapper::toInnerIn, null, doorMapper::toInnerInList);
    }


    @Bean
    public Converter<Exhibition, RsExhibition> toEDto(final ExhibitionMapper exhibitionMapper) {
        return new Converter<>(Exhibition.class, RsExhibition.class, exhibitionMapper::toDto, null, exhibitionMapper::toDtoList);
    }
    @Bean
    public Converter<HExhibition, Exhibition> toEInner(final ExhibitionMapper exhibitionMapper) {
        return new Converter<>(HExhibition.class, Exhibition.class, exhibitionMapper::toInner, null, exhibitionMapper::toInnerList);
    }
    @Bean
    public Converter<Exhibition, HExhibition> toEEntity(final ExhibitionMapper exhibitionMapper) {
        return new Converter<>(Exhibition.class, HExhibition.class, exhibitionMapper::toEntity, exhibitionMapper::mergeOnto, exhibitionMapper::toEntityList);
    }
    @Bean
    public Converter<RsExhibition, Exhibition> toEInnerIn(final ExhibitionMapper exhibitionMapper) {
        return new Converter<>(RsExhibition.class, Exhibition.class, exhibitionMapper::toInnerIn, null, exhibitionMapper::toInnerInList);
    }


    @Bean
    public Converter<Beacon, RsBeacon> toBDto(final BeaconMapper beaconMapper) {
        return new Converter<>(Beacon.class, RsBeacon.class, beaconMapper::toDto, null, beaconMapper::toDtoList);
    }
    @Bean
    public Converter<HBeacon, Beacon> toBInner(final BeaconMapper beaconMapper) {
        return new Converter<>(HBeacon.class, Beacon.class, beaconMapper::toInner, null, beaconMapper::toInnerList);
    }
    @Bean
    public Converter<Beacon, HBeacon> toBEntity(final BeaconMapper beaconMapper) {
        return new Converter<>(Beacon.class, HBeacon.class, beaconMapper::toEntity, beaconMapper::mergeOnto, beaconMapper::toEntityList);
    }
    @Bean
    public Converter<RsBeacon, Beacon> toBInnerIn(final BeaconMapper beaconMapper) {
        return new Converter<>(RsBeacon.class, Beacon.class, beaconMapper::toInnerIn, null, beaconMapper::toInnerInList);
    }

    @Bean
    public Converter<Poi, RsPoi> toPDto(final PoiMapper poiMapper) {
        return new Converter<>(Poi.class, RsPoi.class, poiMapper::toDto, null, poiMapper::toDtoList);
    }
    @Bean
    public Converter<HPoi, Poi> toPInner(final PoiMapper poiMapper) {
        return new Converter<>(HPoi.class, Poi.class, poiMapper::toInner, null, poiMapper::toInnerList);
    }
    @Bean
    public Converter<Poi, HPoi> toPEntity(final PoiMapper poiMapper) {
        return new Converter<>(Poi.class, HPoi.class, poiMapper::toEntity, poiMapper::mergeOnto, poiMapper::toEntityList);
    }
    @Bean
    public Converter<RsPoi, Poi> toPInnerIn(final PoiMapper poiMapper) {
        return new Converter<>(RsPoi.class, Poi.class, poiMapper::toInnerIn, null, poiMapper::toInnerInList);
    }
    
    @Bean
    public ConverterRegistry converterRegistry(final List<Converter<?, ?>> converters) {
        return new ConverterRegistry(converters);
    }
}
