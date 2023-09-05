package com.kt.navi.gw.period.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Emergency {

    private final String carNoName;
    private final double carLatitude;
    private final double carLongitude;
    private final long inputDatetime;

    @JsonCreator
    public Emergency(
            @JsonProperty("carNoName") String carNoName,
            @JsonProperty("carLatitude") double carLatitude,
            @JsonProperty("carLongitude") double carLongitude,
            @JsonProperty("inputDatetime") long inputDatetime) {
        this.carNoName = carNoName;
        this.carLatitude = carLatitude;
        this.carLongitude = carLongitude;
        this.inputDatetime = inputDatetime;
    }
}
