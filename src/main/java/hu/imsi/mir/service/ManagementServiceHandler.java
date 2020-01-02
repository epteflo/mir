package hu.imsi.mir.service;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.common.Response;
import hu.imsi.mir.common.Room;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.RoomRepository;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HRoom;
import hu.imsi.mir.mappers.BaseMapper;
import hu.imsi.mir.mappers.MuseumMapper;
import hu.imsi.mir.mappers.RoomMapper;
import hu.imsi.mir.utils.ServiceHelper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ManagementServiceHandler {

    @Autowired
    private MuseumRepository museumRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MuseumMapper museumMapper;
    @Autowired
    private RoomMapper roomMapper;


    private final Map<String, JpaRepository> REPOSITORY_MAP = new HashMap<>();
    {
        REPOSITORY_MAP.put(Museum.class.getName(), museumRepository);
        REPOSITORY_MAP.put(Room.class.getName(), roomRepository);
    }

    private final Map<String, BaseMapper> MAPPER_MAP = new HashMap<>();
    {
        MAPPER_MAP.put(Museum.class.getName(), museumMapper);
        MAPPER_MAP.put(Room.class.getName(), roomMapper);
    }



    public Museum createMuseum(Museum museum){
        if(!ServiceHelper.validateMuseum(museum)) return museum;
        final HMuseum entity = museumMapper.toEntity(museum);
        final HMuseum stored = museumRepository.saveAndFlush(entity);
        return museumMapper.toInner(stored);
    }

    public Optional<Museum> updateMuseum(Integer id, Museum museum){
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        if(!hMuseum.isPresent()) return Optional.empty();;
        final HMuseum m = hMuseum.get();
        museumMapper.mergeOnto(museum, m);
        return Optional.of(museumMapper.toInner(museumRepository.saveAndFlush(m)));
    }

    public Optional<Museum> getMuseum(Integer id){
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        if(!hMuseum.isPresent()) return Optional.empty();;
        final HMuseum m = hMuseum.get();
        return Optional.of(museumMapper.toInner(m));
    }

    public Optional<Museum> deleteMuseum(Integer id){
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        if(!hMuseum.isPresent()) return Optional.empty();
        final HMuseum m = hMuseum.get();
        museumRepository.delete(m);
        return Optional.of(museumMapper.toInner(m));
    }


   /* public <T extends Response> T createObject(T source){
        if(!ServiceHelper.validateObject(source)) return source;
        BaseMapper mapper = MAPPER_MAP.get(source.getClass().getName());
        JpaRepository jpaRepository = REPOSITORY_MAP.get(source.getClass().getName());
        return mapper.toInner(jpaRepository.saveAndFlush(mapper.toEntity(source)));
    }*/

    public Room createRoom(Room room){
        final HRoom entity = roomMapper.toEntity(room);
        final HRoom stored = roomRepository.saveAndFlush(entity);
        return roomMapper.toInner(stored);
    }


}
