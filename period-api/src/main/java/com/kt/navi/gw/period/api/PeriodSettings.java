package com.kt.navi.gw.period.api;

import java.util.Optional;

public class PeriodSettings {
    public static final String logDistributors = System.getenv("NAVI_LOG_DISTRIBUTORS");
    public static final int logBufferCount = Optional.ofNullable(System.getenv("NAVI_LOG_BUFFER_COUNT"))
            .map(Integer::parseInt)
            .orElse(10000);
    public static final int logKafkaReconnectInterval = Optional.ofNullable(System.getenv("NAVI_LOG_KAFKA_RECONNECT_INTERVAL"))
            .map(Integer::parseInt)
            .orElse(60);
}
