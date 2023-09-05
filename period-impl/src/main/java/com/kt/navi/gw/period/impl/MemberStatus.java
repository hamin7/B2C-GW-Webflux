package com.kt.navi.gw.period.impl;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MemberStatus {
    public int memberId;
    public Map<String, JsonValue> status;
    public Long timestamp;

    public MemberStatus(int memberId, Map<String, JsonValue> status, Long timestamp) {
        this.memberId = memberId;
        this.status = status;
        this.timestamp = timestamp;
    }

    public MemberStatus changed(Map<String, JsonValue> status, long timestamp) {
        Map<String, JsonValue> newStatus = new HashMap<>(this.status);
        newStatus.putAll(status);
        return new MemberStatus(this.memberId, newStatus, timestamp);
    }
}
