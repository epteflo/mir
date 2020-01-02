package hu.imsi.mir.utils;

import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.entities.HMuseum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperHelper implements InitializingBean {

    private static MuseumRepository museumRepository;

    @Autowired
    private MuseumRepository museumRepository0;

    @Override
    public void afterPropertiesSet() throws Exception {
        museumRepository = this.museumRepository0;
    }

    public static HMuseum getMuseum(Integer id){
        final Optional<HMuseum> hMuseum = museumRepository.findById(id);
        return hMuseum.get();
    }
}
