package com.kt.navi.gw.period.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class PeriodRouter {

    @Bean
    public RouterFunction<ServerResponse> root(PeriodHandler periodHandler) {
        return RouterFunctions.route()
                .POST("/service/period/logs", RequestPredicates.contentType(MediaType.APPLICATION_JSON), periodHandler::unifiedLog)
                .build();
    }
}
