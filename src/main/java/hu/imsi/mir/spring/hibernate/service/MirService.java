package hu.imsi.mir.spring.hibernate.service;


import hu.imsi.mir.spring.hibernate.model.HBeacon;
import hu.imsi.mir.spring.hibernate.model.HMuseum;
import hu.imsi.mir.spring.hibernate.query.MuseumQueryParams;

import java.util.List;

public interface MirService {

    public Integer saveMuseum(HMuseum hMuseum);

    public HMuseum getMuseum(int id);

    public List<HMuseum> getAllMuseum();

    public List<HMuseum> findMuseums(MuseumQueryParams museumQueryParams);



    public Integer saveBeacon(HBeacon hBeacon);

    public HBeacon getBeacon(int id);

    public List<HBeacon> getAllBeacon();

}
