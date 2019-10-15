package hu.imsi.mir.spring.hibernate.service;

import hu.imsi.mir.spring.hibernate.dao.MirDao;
import hu.imsi.mir.spring.hibernate.model.HMuseum;

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

}