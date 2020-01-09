package hu.imsi.mir.beans;

import hu.imsi.mir.dao.BeaconRepository;
import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.ServiceLogRepository;
import hu.imsi.mir.utils.InitDBDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${mir.db.init:false}") boolean init;
    @Value("${mir.db.init_logs:false}") boolean init_logs;
    @Value("${mir.db.fill_data:false}") boolean fill_data;
    @Value("${mir.db.fill_data_file:null}") String fill_data_file;

    @Autowired
    MuseumRepository museumRepository;

    @Autowired
    ServiceLogRepository serviceLogRepository;

    @Autowired
    BeaconRepository beaconRepository;

    final static Logger logger = LogManager.getLogger(Initializer.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextStartedEvent) {
        logger.info(" MIR Init Params ");
        logger.info("-----------------");
        logger.info("init :" + init);
        if (init) {
            museumRepository.deleteAll();
            beaconRepository.deleteAll();
        }

        logger.info("init_logs :" + init_logs);
        if (init_logs) {
            serviceLogRepository.deleteAll();

        }

        logger.info("fill_data :" + fill_data);
        if (fill_data && !StringUtils.isEmpty(fill_data_file)) {
            InitDBDataHelper.readProperties(fill_data_file);
        } else if(fill_data) {
            logger.info("Data fill not possible, not a file :"+fill_data_file);
        }
    }
}
