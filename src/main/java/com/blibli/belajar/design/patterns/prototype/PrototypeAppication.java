package com.blibli.belajar.design.patterns.prototype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class PrototypeAppication {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        private String id;
        private String name;
        private String category;
        private Integer discount;
    }

    @SpringBootApplication
    public static class Configuration {

        @Bean
        @Scope("prototype")
        public Customer standardCustomer(){
            return Customer.builder()
                    .category("STD")
                    .discount(10)
                    .build();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Configuration.class);
        Customer c1 = context.getBean(Customer.class);
        Customer c2 = context.getBean(Customer.class);
        Customer c3 = context.getBean(Customer.class);

        System.out.println(c1 == c2);
        System.out.println(c2 == c3);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
