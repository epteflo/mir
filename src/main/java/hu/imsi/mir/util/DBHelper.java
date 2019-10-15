package hu.imsi.mir.util;

import hu.positech.positionlogger.spring.hibernate.service.LoggerService;

import java.util.Date;

public class DBHelper {

    private LoggerService loggerService;

    public HMark createMark(String name, String hwid, String coordX, String coordY){
        HMark mark = new HMark();
        mark.setHwId(hwid);
        mark.setName(name);
        mark.setCoordX(coordX);
        mark.setCoordY(coordY);
        loggerService.saveMark(mark);
        return mark;
    }

    public HBLNode createBlNode(String name, String hwid){
        HBLNode hblNode = new HBLNode();
        hblNode.setName(name);
        hblNode.setHwId(hwid);
        loggerService.saveBLNode(hblNode);
        return hblNode;
    }

    public HRFIDAntenna createHRFIDAntenna(String name, String hwid){
        HRFIDAntenna hrfidAntenna = new HRFIDAntenna();
        hrfidAntenna.setName(name);
        hrfidAntenna.setHwId(hwid);
        loggerService.saveRFIDAntenna(hrfidAntenna);
        return hrfidAntenna;
    }

    public HRFIDTag createHRFIDTag(String name, String tagId, String type){
        HRFIDTag hrfidTag = new HRFIDTag();
        hrfidTag.setName(name);
        hrfidTag.setTagId(tagId);
        hrfidTag.setType(type);
        loggerService.saveRFIDTag(hrfidTag);
        return hrfidTag;
    }

    public HWorker createWorker(String name, String status, String job, String company, HBLNode node, HRFIDTag tag){
        HWorker worker = new HWorker();
        worker.setName(name);
        worker.setCompany(company);
        worker.setJob(job);
        worker.setStatus(status);
        worker.setNode(node);
        worker.setRfidTag(tag);
        loggerService.saveWorker(worker);
        return worker;
    }

    public HPermission createPermission(String permission, HRFIDAntenna hrfidAntenna, HRFIDTag hrfidTag){
        return createPermission(permission, hrfidAntenna, hrfidTag, null, null, null);
    }

    public HPermission createPermission(String permission, HRFIDAntenna hrfidAntenna, HRFIDTag hrfidTag, HWorker worker){
        return createPermission(permission, hrfidAntenna, hrfidTag, worker, null, null);
    }

    public HPermission createPermission(String permission, HRFIDAntenna hrfidAntenna, HRFIDTag hrfidTag, HWorker worker, Integer limit, String corrId){
        HPermission hPermission = new HPermission();
        hPermission.setPermission(permission);
        hPermission.setRfidAntenna(hrfidAntenna);
        hPermission.setRfidTag(hrfidTag);
        if(worker!=null) hPermission.setWorker(worker);
        if(limit!=null) hPermission.setLimitInS(limit);
        if(corrId!=null) hPermission.setCorrId(corrId);
        loggerService.savePermission(hPermission);
        return hPermission;
    }

    public HBLMarkLog createBlMarkLog(Date createdAt, HMark mark, HBLNode hblNode, String desc){
        HBLMarkLog hblMarkLog = new HBLMarkLog();
        hblMarkLog.setCreatedAt(createdAt);
        hblMarkLog.setBlNode(hblNode);
        hblMarkLog.setMark(mark);
        hblMarkLog.setDesc(desc);
        loggerService.saveBLMarkLog(hblMarkLog);
        return hblMarkLog;
    }

    public HRFIDLog createHRFIDlog(Date createdAt, HRFIDAntenna hrfidAntenna, HRFIDTag hrfidTag, HWorker worker, String desc){
        HRFIDLog hrfidLog = new HRFIDLog();
        hrfidLog.setCreatedAt(createdAt);
        hrfidLog.setRfidAntenna(hrfidAntenna);
        hrfidLog.setRfidTag(hrfidTag);
        hrfidLog.setWorker(worker);
        hrfidLog.setDesc(desc);
        loggerService.saveRFIDLog(hrfidLog);
        return hrfidLog;
    }

    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }
}
