package hu.imsi.mir.utils;

import hu.imsi.mir.dao.ServiceLogRepository;
import hu.imsi.mir.dao.UserRepository;
import hu.imsi.mir.dao.entities.HServiceLog;
import hu.imsi.mir.dao.entities.HUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoggerServiceHandler {

    @Autowired
    private ServiceLogRepository serviceLogRepository;

    @Autowired
    private UserRepository userRepository;

    public void logStart(String userName, String message, String sourceModule, String sourceMethod){
        HUser hUser = userRepository.findHUsersByName(userName);
        if(hUser==null){
            hUser = new HUser();
            hUser.setName(userName);
            hUser.setUuid(UUID.randomUUID().toString());
            userRepository.saveAndFlush(hUser);
        }
        HServiceLog serviceLog = new HServiceLog();
        serviceLog.setMessage(message);
        serviceLog.setSourceModul(sourceModule);
        serviceLog.setSourceMethod(sourceMethod);
        serviceLog.setUser(hUser);
        serviceLogRepository.saveAndFlush(serviceLog);
    }
}
