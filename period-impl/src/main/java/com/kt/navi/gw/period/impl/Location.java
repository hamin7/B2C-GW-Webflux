package com.kt.navi.gw.period.impl;

import lombok.Data;

@Data
public class Location {
    public double lat;
    public double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
