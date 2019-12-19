package hu.imsi.mir.beans;

import hu.imsi.mir.dao.MuseumRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import sun.applet.AppletListener;

@Component
public class DBInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${mir.db.init:false}") boolean init;

    @Autowired
    MuseumRepository museumRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextStartedEvent) {
        System.out.println("init " + init);
        if (init) {
            museumRepository.deleteAll();
        }
    }
}
