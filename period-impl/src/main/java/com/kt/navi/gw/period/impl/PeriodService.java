package com.kt.navi.gw.period.impl;

import org.springframework.stereotype.Service;

// TODO : 이 @Service는 무슨 어노테이션인가?
@Service
public class PeriodService {

    public LogMessage unifiedLog(LogMessage logMessage) {
        System.out.println("unifiedLog => timestamp : " + logMessage.timestamp + ", instanceId : " + logMessage.instanceId + ", Location : (" + logMessage.location.lat + ","+logMessage.location.lng + ")");
        return new LogMessage(logMessage.getTimestamp(), logMessage.getInstanceId(), logMessage.getLocation());
    }
}
