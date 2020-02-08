package hu.imsi.mir.utils;

import hu.imsi.mir.dao.entities.*;
import hu.imsi.mir.service.ServiceRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperHelper implements InitializingBean {

    private static ServiceRegistry serviceRegistry;

    @Override
    public void afterPropertiesSet() throws Exception {
        serviceRegistry = BeanHelper.getServiceRegistry();
    }

    public static HMuseum getMuseum(Integer id){
        final Optional<HMuseum> hMuseum = serviceRegistry.REPOSITORY_MAP.get(HMuseum.class).findById(id);
        return hMuseum.get();
    }

    public static HRoom getRoom(Integer id){
        final Optional<HRoom> hRoom = serviceRegistry.REPOSITORY_MAP.get(HRoom.class).findById(id);
        return hRoom.get();
    }

    public static HBeacon getBeacon(Integer id){
        final Optional<HBeacon> hBeacon = serviceRegistry.REPOSITORY_MAP.get(HBeacon.class).findById(id);
        return hBeacon.get();
    }

    public static HPoi getPoi(Integer id){
        final Optional<HPoi> hPoi = serviceRegistry.REPOSITORY_MAP.get(HPoi.class).findById(id);
        return hPoi.get();
    }

    public static HExhibition getExhibition(Integer id){
        final Optional<HExhibition> hExhibition = serviceRegistry.REPOSITORY_MAP.get(HExhibition.class).findById(id);
        return hExhibition.get();
    }
}
