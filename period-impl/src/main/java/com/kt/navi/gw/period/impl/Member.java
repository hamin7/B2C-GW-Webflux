package com.kt.navi.gw.period.impl;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.HashMap;

@Data
public class Member {
    public int memberId;
    public int seat;
    public HashMap<String, JsonValue> attributes;

    public Member(int memberId, int seat, HashMap<String, JsonValue> attributes) {
        this.memberId = memberId;
        this.seat = seat;
        this.attributes = new HashMap<>();
    }
}
