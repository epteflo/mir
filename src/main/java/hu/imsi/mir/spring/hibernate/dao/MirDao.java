package hu.imsi.mir.spring.hibernate.dao;

import hu.imsi.mir.spring.hibernate.model.HMuseum;

import java.util.List;

public interface MirDao {

    public Integer saveMuseum(HMuseum hMuseum);

    public HMuseum getMuseum(int id);

    public List<HMuseum> getAllMuseum();

}
