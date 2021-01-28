package com.blibli.belajar.design.patterns.dynamic_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class DynamicModuleApplication {

    @SpringBootApplication
    public static class Application {

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
