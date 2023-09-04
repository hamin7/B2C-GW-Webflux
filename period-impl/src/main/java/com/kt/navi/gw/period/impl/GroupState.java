package com.kt.navi.gw.period.impl;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class GroupState {
    public String groupType;
    public int maxSeats;
    public Long expired;
    public int masterId;
    public HashMap<String, JsonValue> attributes;
    public List<Member> members;
    // ranking...
    public GroupState(String groupType, int maxSeats, Long expired, int masterId, HashMap<String, JsonValue> attributes, List<Member> members) {
        this.groupType = groupType;
        this.maxSeats = maxSeats;
        this.expired = expired;
        this.masterId = masterId;
        this.attributes = new HashMap<>();
        this.members = new ArrayList<>();
    }
}
