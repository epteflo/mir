package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HLayout;

import java.util.List;

public interface CustomLayoutRepository {
    List<HLayout> findLayoutsCustom(final Integer roomId, final String poiName);
}
