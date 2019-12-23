package hu.imsi.mir.service;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.mappers.MuseumMapper;
import hu.imsi.mir.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ManagementServiceHandler {

    @Autowired
    private MuseumRepository museumRepository;

    @Autowired
    private MuseumMapper museumMapper;


    public Museum createMuseum(Museum museum){
        if(!ServiceHelper.validateMuseum(museum)) return museum;
        final HMuseum entity = museumMapper.toEntity(museum);
        final HMuseum stored = museumRepository.saveAndFlush(entity);
        return museumMapper.toInner(stored);
    }

    public Optional<Museum> updateMusem(Integer id, Museum museum){
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        if(!hMuseum.isPresent()) return null;
        final HMuseum m = hMuseum.get();
        museumMapper.mergeOnto(museum, m);
        return Optional.of(museumMapper.toInner(museumRepository.saveAndFlush(m)));
    }

    public Optional<Museum> getMuseum(Integer id){
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        if(!hMuseum.isPresent()) return null;
        final HMuseum m = hMuseum.get();
        return Optional.of(museumMapper.toInner(m));
    }

    public boolean deleteMuseum(Integer id){
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        if(!hMuseum.isPresent()) return false;
        final HMuseum m = hMuseum.get();
        museumRepository.delete(m);
        return true;
    }

}
