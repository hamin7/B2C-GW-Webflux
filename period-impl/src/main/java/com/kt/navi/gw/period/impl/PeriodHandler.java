package com.kt.navi.gw.period.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
public class PeriodHandler {
    @Autowired
    private PeriodService periodService;

    // TODO : logMessage의 쓸모는?
    public Mono<List<Emergency>> getEmergencies(LogMessage logMessage, Location location) {
        if (location != null) {
            return Mono.fromCallable(() -> {
                List<Emergency> list = fetchEmergencyData(location);
                return list;
            });
        } else {
            return Mono.empty();
        }
    }

    public List<Emergency> fetchEmergencyData(Location location) {
        // 비동기 작업을 수행하여 List<Emergency> 데이터를 가져옴
        return List.of(new Emergency(0, "crash"), new Emergency(1, "Fire"));
    }

    public Mono<List<Sudden>> getSuddens(LogMessage logMessage, Location location) {
        if (location != null) {
            return Mono.fromCallable(() -> {
                List<Sudden> list = fetchSuddenData(location);
                return list;
            });
        } else {
            return Mono.empty();
        }
    }

    public List<Sudden> fetchSuddenData(Location location) {
        return List.of(new Sudden("0", "seoul", 37.47135, 127.02937));
    }

    public Mono<ServerResponse> authenticate(ServerRequest request) {
        String requestHeader = request.headers().firstHeader("requestHeader");

        return request.bodyToMono(LogMessage.class)
                .flatMap(logMessage -> {
                    Location location = logMessage.getLocation();
                    CommunicationRequestMessage communicationRequestMessage = logMessage.getCommunication();

                    Mono<List<Emergency>> emergenciesMono = getEmergencies(logMessage, location);
                    Mono<List<Sudden>> suddenMono = getSuddens(logMessage, location);
                    Mono<CommunicationResponseMessage> communicationResponseMessageMono = processCommunicationRequest(logMessage, communicationRequestMessage);
                });
    }

    public Mono<CommunicationResponseMessage> processCommunicationRequest(LogMessage logMessage, CommunicationRequestMessage communicationRequestMessage) {
        if (communicationRequestMessage != null) {
            Location location = logMessage.getLocation();
            if (location != null) {

            } else {

            }
        }
    }

    public Mono<ServerResponse> unifiedLog(ServerRequest request) {
        return request.bodyToMono(LogMessage.class)
                .flatMap(logMessage -> Mono.just(periodService.unifiedLog(logMessage)))
                .flatMap(logMessage -> ServerResponse.created(URI.create("/unifiedLog/" + logMessage.getTimestamp()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(logMessage)));
    }

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
