package hu.imsi.mir.service;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.mappers.MuseumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OperationServiceHandler {

    @Autowired
    private MuseumRepository museumRepository;

    @Autowired
    private MuseumMapper museumMapper;


    public Museum createMuseum(Museum museum){

        final HMuseum entity = museumMapper.toEntity(museum);
        final HMuseum stored = museumRepository.saveAndFlush(entity);
        return ResponseEntity.ok(museumMapper.toDto(stored));
    }

}
