package com.kt.navi.gw.period.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class PeriodMessage {
    private final Optional<List<Emergency>> emergencies;
    private final Optional<List<Sudden>> suddens;
    private final Optional<CommunicationResponseMessage> communication;

    @JsonCreator
    public PeriodMessage(
            @JsonProperty("emergencies") Optional<List<Emergency>> emergencies,
            @JsonProperty("suddens") Optional<List<Sudden>> suddens,
            @JsonProperty("commnunication") Optional<CommunicationResponseMessage> communication) {
        this.emergencies = emergencies;
        this.suddens = suddens;
        this.communication = communication;
    }
}
