package hu.imsi.mir.dao;

import hu.imsi.mir.dao.entities.HLayout;

import java.util.List;

public interface CustomLayoutRepository {
    List<HLayout> findLayoutsCustom(final Integer roomId, final Integer museumId, final Integer beaconId, final Integer exhibitionId, final Integer poiId,
                                    final String poiName, final String poiType, final String poiShortDesc, final String poiDescription, final String category, final String style, final String poiAuthor, final String poiAge, final String poiCreationPlace, final String poiMaterial);
}
