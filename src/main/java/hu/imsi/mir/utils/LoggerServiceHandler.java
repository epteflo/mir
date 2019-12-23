package hu.imsi.mir.utils;

import hu.imsi.mir.dao.ServiceLogRepository;
import hu.imsi.mir.dao.UserRepository;
import hu.imsi.mir.dao.entities.HServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Component;

@Component
public class LoggerServiceHandler {

    @Autowired
    private ServiceLogRepository serviceLogRepository;

    @Autowired
    private UserRepository userRepository;

    public void logStart(String userName, String message, String sourceModule, String sourceMethod){
        userRepository.findByName()
        HServiceLog serviceLog = new HServiceLog();
        serviceLog.setMessage(message);
        serviceLog.setSourceModul(sourceModule);
        serviceLog.setSourceMethod(sourceMethod);

    }
}
