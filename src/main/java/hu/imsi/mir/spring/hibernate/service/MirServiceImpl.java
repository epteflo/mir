package hu.imsi.mir.spring.hibernate.service;

import hu.imsi.mir.spring.hibernate.dao.MirDao;
import hu.imsi.mir.spring.hibernate.model.HBeacon;
import hu.imsi.mir.spring.hibernate.model.HMuseum;
import hu.imsi.mir.spring.hibernate.query.MuseumQueryParams;

import java.util.List;

public class MirServiceImpl implements MirService {

    private MirDao mirDao;

    public void setMirDao(MirDao mirDao) {
        this.mirDao = mirDao;
    }

    @Override
    public Integer saveMuseum(HMuseum hMuseum) {
        return mirDao.saveMuseum(hMuseum);
    }

    @Override
    public HMuseum getMuseum(int id) {
        return mirDao.getMuseum(id);
    }

    @Override
    public List<HMuseum> getAllMuseum() {
        return mirDao.getAllMuseum();
    }

    @Override
    public List<HMuseum> findMuseums(MuseumQueryParams museumQueryParams) {
        return mirDao.findMuseums(museumQueryParams);
    }


    @Override
    public Integer saveBeacon(HBeacon hBeacon) {
        return mirDao.saveBeacon(hBeacon);
    }

    @Override
    public HBeacon getBeacon(int id) {
        return mirDao.getBeacon(id);
    }

    @Override
    public List<HBeacon> getAllBeacon() {
        return mirDao.getAllBeacon();
    }


}