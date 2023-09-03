package com.kt.navi.gw.period.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class PeriodHandler {
    @Autowired
    private PeriodService periodService;

    public Mono<ServerResponse> unifiedLog(ServerRequest request) {
        return request.bodyToMono(LogMessage.class)
                .flatMap(logMessage -> Mono.just(periodService.unifiedLog(logMessage)))
                .flatMap(logMessage -> ServerResponse.created(URI.create("/unifiedLog/" + logMessage.getTimestamp()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(logMessage)));
    }

//    public Mono<ServerResponse> unifiedLog(ServerRequest request) {
//        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
//                .body(BodyInserters.fromValue("i will give you unifiedLog"));
//    }

    public Mono<ServerResponse> getEmergencyById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(periodService.getEmergencyById(id)));
    }

    public Mono<ServerResponse> createEmergency(ServerRequest request) {
        return request.bodyToMono(Emergency.class)
                .flatMap(emergency -> Mono.just(periodService.createEmergency(emergency)))
                .flatMap(emergency -> ServerResponse.created(URI.create("/emergencies/" + emergency.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(emergency)));
    }
}
