package hu.imsi.mir.utils;

import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.dao.entities.HRoom;
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
}
