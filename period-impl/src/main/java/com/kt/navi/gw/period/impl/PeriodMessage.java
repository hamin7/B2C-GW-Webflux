package com.kt.navi.gw.period.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class PeriodMessage {
    private final List<Emergency> emergencies;
    private final List<Sudden> suddens;
    private final Optional<CommunicationResponseMessage> communication;

    @JsonCreator
    public PeriodMessage(
            @JsonProperty("emergencies") List<Emergency> emergencies,
            @JsonProperty("suddens") List<Sudden> suddens,
            @JsonProperty("commnunication") Optional<CommunicationResponseMessage> communication) {
        this.emergencies = emergencies;
        this.suddens = suddens;
        this.communication = communication;
    }
}
