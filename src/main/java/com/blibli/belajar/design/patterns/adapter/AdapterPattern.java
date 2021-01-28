package com.blibli.belajar.design.patterns.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

public class AdapterPattern {

    public static class MyGoodHealthIndicator implements HealthIndicator {

        @Override
        public Health health() {
            return Health.up().build();
        }
    }

    public static class MyPoorHealthIndicator implements HealthIndicator {

        @Override
        public Health health() {
            return Health.down().build();
        }
    }

    @SpringBootApplication
    public static class Application {

        @Bean
        public MyGoodHealthIndicator myGoodHealthIndicator() {
            return new MyGoodHealthIndicator();
        }

        @Bean
        public MyPoorHealthIndicator myPoorHealthIndicator() {
            return new MyPoorHealthIndicator();
        }
    }

    // Access with ../actuator/health...
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
