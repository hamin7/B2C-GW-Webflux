package com.kt.navi.gw.period.impl;

import org.springframework.stereotype.Service;

// TODO : 이 @Service는 무슨 어노테이션인가?
@Service
public class PeriodService {
    int idCnt = 0;
    public Emergency getEmergencyById(int id) {
        return new Emergency(id, "Emergency!!!");
    }
    public Emergency createEmergency(Emergency emergency) {
        idCnt++;
        System.out.println("create request => idCnt : " + idCnt + ", name : " + emergency.getName());
        return new Emergency(idCnt, emergency.getName());
    }
    public LogMessage unifiedLog(LogMessage logMessage) {
        System.out.println("unifiedLog => timestamp : " + logMessage.timestamp + ", instanceId : " + logMessage.instanceId + ", Location : (" + logMessage.location.lat + ","+logMessage.location.lng + ")");
        return new LogMessage(logMessage.getTimestamp(), logMessage.getInstanceId(), logMessage.getLocation());
    }
}
