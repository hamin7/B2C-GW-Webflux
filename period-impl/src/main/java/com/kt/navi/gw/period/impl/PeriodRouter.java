package com.kt.navi.gw.period.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class PeriodRouter {

    @Bean
    public RouterFunction<ServerResponse> root(PeriodHandler periodHandler) {
        return RouterFunctions.route()
//                .route(RequestPredicates.GET("/service/period/logs").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), periodHandler::unifiedLog)
                .POST("/service/period/logs", RequestPredicates.contentType(MediaType.APPLICATION_JSON), periodHandler::unifiedLog)
                .POST("/unifiedLog", RequestPredicates.contentType(MediaType.APPLICATION_JSON), periodHandler::authenticate)
                .GET("emergencies/{id}", RequestPredicates.accept(MediaType.TEXT_PLAIN), periodHandler::getEmergencyById)
                .POST("/create", RequestPredicates.contentType(MediaType.APPLICATION_JSON), periodHandler::createEmergency)
                .build();
    }
}
