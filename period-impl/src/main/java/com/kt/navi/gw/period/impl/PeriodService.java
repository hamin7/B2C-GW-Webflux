package com.kt.navi.gw.period.impl;

import org.springframework.stereotype.Service;

// TODO : 이 @Service는 무슨 어노테이션인가?
@Service
public class PeriodService {
    public Emergency getEmergencyById(int id) {
        return new Emergency(id, "Emergency!!!");
    }
    public Emergency createEmergency(Emergency emergency) {
        return new Emergency(102, emergency.getName());
    }
}
