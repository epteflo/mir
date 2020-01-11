package hu.imsi.mir.service;

import hu.imsi.mir.common.*;
import hu.imsi.mir.dao.*;
import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.mappers.ConverterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceRegistry implements InitializingBean {

    @Autowired
    private MuseumRepository museumRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BeaconRepository beaconRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private ContentObjectRepository contentObjectRepository;
    @Autowired
    private DoorRepository doorRepository;
    @Autowired
    private ExhibitionRepository exhibitionRepository;
    @Autowired
    private ExhibitionTourRepository exhibitionTourRepository;
    @Autowired
    private ExhibitionTourLayoutRepository exhibitionTourLayoutRepository;
    @Autowired
    private LayoutRepository layoutRepository;
    @Autowired
    private PoiRepository poiRepository;



    @Autowired
    public ConverterRegistry converterRegistry;

    public final Map<Class<?>, Class<?>> MODEL_ENTITY_CLASS_MAP = new HashMap<>();
    {
        MODEL_ENTITY_CLASS_MAP.put(Museum.class, HMuseum.class);
        MODEL_ENTITY_CLASS_MAP.put(Room.class, HRoom.class);
        MODEL_ENTITY_CLASS_MAP.put(Door.class, HDoor.class);
        MODEL_ENTITY_CLASS_MAP.put(Beacon.class, HBeacon.class);
        MODEL_ENTITY_CLASS_MAP.put(Exhibition.class, HExhibition.class);
        MODEL_ENTITY_CLASS_MAP.put(ExhibitionTour.class, HExhibitionTour.class);
        MODEL_ENTITY_CLASS_MAP.put(ExhibitionTourLayout.class, HExhibitionTourLayout.class);
        MODEL_ENTITY_CLASS_MAP.put(Content.class, HContent.class);
        MODEL_ENTITY_CLASS_MAP.put(ContentObject.class, HContentObject.class);
        MODEL_ENTITY_CLASS_MAP.put(Poi.class, HPoi.class);
        MODEL_ENTITY_CLASS_MAP.put(Layout.class, HLayout.class);
    }

    @SuppressWarnings("rawtypes")
    public final Map<Class<?>, JpaRepository> REPOSITORY_MAP = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        REPOSITORY_MAP.clear();
        REPOSITORY_MAP.put(HMuseum.class, museumRepository);
        REPOSITORY_MAP.put(HRoom.class, roomRepository);
        REPOSITORY_MAP.put(HBeacon.class, beaconRepository);
        REPOSITORY_MAP.put(HContent.class, contentRepository);
        REPOSITORY_MAP.put(HContentObject.class, contentObjectRepository);
        REPOSITORY_MAP.put(HDoor.class, doorRepository);
        REPOSITORY_MAP.put(HExhibition.class, exhibitionRepository);
        REPOSITORY_MAP.put(HExhibitionTour.class, exhibitionTourRepository);
        REPOSITORY_MAP.put(HExhibitionTourLayout.class, exhibitionTourLayoutRepository);
        REPOSITORY_MAP.put(HLayout.class, layoutRepository);
        REPOSITORY_MAP.put(HPoi.class, poiRepository);
    }
}
