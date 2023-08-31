package com.kt.navi.gw.period.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeriodImplApplication {

    public static void main(String[] args) {

        SpringApplication.run(PeriodImplApplication.class, args);

        PeriodWebClient pwc = new PeriodWebClient();
    }
}
