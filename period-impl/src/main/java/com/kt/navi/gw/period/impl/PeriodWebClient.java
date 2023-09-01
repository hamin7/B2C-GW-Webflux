package com.kt.navi.gw.period.impl;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PeriodWebClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> result = client.get().uri("/service/period/logs").accept(MediaType.TEXT_PLAIN).exchange();

    public String getResult() {
        return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
    }

    public void fetchEmergencyById() {
        int id = 101;
        client.get()
                .uri("/emergencies/" + id)
                .exchange()
                .flatMap(res -> res.bodyToMono(String.class))
                .subscribe(emergency -> System.out.println("GET: " + emergency));
    }

    public void createEmergency() {
        client.post()
                .uri("/create")
                .bodyValue(new Emergency("Crash"))
                .exchange()
                .flatMap(res -> res.bodyToMono(Emergency.class))
                .subscribe(emergency -> System.out.println("POST: " + emergency.getId()));
    }
}
