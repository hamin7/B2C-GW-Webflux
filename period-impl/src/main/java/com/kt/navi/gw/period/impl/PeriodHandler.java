package com.kt.navi.gw.period.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class PeriodHandler {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    public Mono<ServerResponse> authenticate(ServerRequest request) {

        return request.bodyToMono(LogMessage.class)
                .flatMap(logMessage -> {
                    Location location = logMessage.getLocation();
                    CommunicationRequestMessage communicationRequestMessage = logMessage.getCommunication();

                    Mono<List<Emergency>> emergenciesMono = getEmergencies(logMessage, location);
                    Mono<List<Sudden>> suddensMono = getSuddens(logMessage, location);
                    Mono<Optional<CommunicationResponseMessage>> communicationResponseMono = processCommunicationRequest(logMessage, communicationRequestMessage);

                    return Mono.zip(emergenciesMono, suddensMono, communicationResponseMono)
                            .flatMap(tuple -> {
                                List<Emergency> emergencies = tuple.getT1();
                                List<Sudden> suddens = tuple.getT2();
                                Optional<CommunicationResponseMessage> communicationResponseMessage = tuple.getT3();

                                HttpHeaders headers = new HttpHeaders();
                                headers.setContentType(MediaType.APPLICATION_JSON);
                                PeriodMessage periodMessage = new PeriodMessage(emergencies, suddens, communicationResponseMessage);

                                return ServerResponse.status(HttpStatus.OK)
                                        .headers(httpHeaders -> httpHeaders.putAll(headers))
                                        .bodyValue(periodMessage);
                            });
                });
    }

    public Mono<Optional<CommunicationResponseMessage>> processCommunicationRequest(LogMessage logMessage, CommunicationRequestMessage communicationRequestMessage) {
        if (communicationRequestMessage != null) {
            Location location = logMessage.getLocation();
            if (location != null) {
                communicationRequestMessage = communicationRequestMessage.locationAddedIfNotExists(location.getLat(), location.getLng());
            }
            communicationRequestMessage = communicationRequestMessage.added(logMessage.instanceId, logMessage.instanceId);      // added(context.instanceId, context.transactionId)

            // 외부 서비스를 호출하여 비동기적으로 communicationRequestMessage를 처리, 결과를 반환.
            return webClient.post()
                    .uri("/processCommunicationRequest")
                    .body(BodyInserters.fromValue(communicationRequestMessage))
                    .retrieve()
                    .bodyToMono(CommunicationResponseMessage.class)
                    .map(response -> {
                        if (response != null && response.getGroupMessage() != null) {
                            return Optional.of(response);
                        }
                        return Optional.empty();
                    });
        } else {
            return Mono.just(Optional.empty());
        }
    }

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
}
