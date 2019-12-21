package hu.imsi.mir.beans;

import hu.imsi.mir.dao.MuseumRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${mir.db.init:false}") boolean init;

    @Autowired
    MuseumRepository museumRepository;

    final static Logger logger = LogManager.getLogger(Initializer.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextStartedEvent) {
        logger.info("init " + init);
        if (init) {
            museumRepository.deleteAll();
        }
    }
}
