package com.kt.navi.gw.period.impl;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PeriodWebClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> result = client.get().uri("/service/period/logs").accept(MediaType.TEXT_PLAIN).exchange();
}
