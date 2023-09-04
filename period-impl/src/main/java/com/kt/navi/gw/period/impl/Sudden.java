package com.kt.navi.gw.period.impl;

import lombok.Data;

@Data
public class Sudden {

    private String id;
    private String code;
    private double lat;
    private double lng;
    private String[] links;

    public Sudden() {}
    public Sudden(String id, String code, double lat, double lng) {
        this.id = id;
        this.code = code;
        this.lat = lat;
        this.lng = lng;
    }
}
