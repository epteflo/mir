package hu.imsi.mir.service;

import hu.imsi.mir.common.Museum;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.mappers.MuseumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationServiceHandler {

    @Autowired
    private MuseumRepository museumRepository;

    @Autowired
    private MuseumMapper museumMapper;


    public Museum getMuseum(Integer id){
        return null;
    }

    public List<Museum> getMuseums(Museum museum){
        return null;
    }

}
