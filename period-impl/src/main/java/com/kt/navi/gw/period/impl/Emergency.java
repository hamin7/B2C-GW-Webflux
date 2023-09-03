package com.kt.navi.gw.period.impl;

import lombok.Data;

@Data
public class Emergency {

    private int id;
    private String name;

    public Emergency() {}
    public Emergency(String name) {
        this.name = name;
    }
    public Emergency(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
