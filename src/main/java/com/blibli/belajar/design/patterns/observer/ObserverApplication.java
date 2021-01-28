package com.blibli.belajar.design.patterns.observer;

import lombok.*;
import org.apache.logging.log4j.message.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;

public class ObserverApplication {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String id;
        private String name;
    }

    public static class ProductEvent extends ApplicationEvent {
        public ProductEvent(Product product) {
            super(product);
        }
    }

    public static class ProductRepository implements ApplicationEventPublisherAware {
        @Setter
        private ApplicationEventPublisher applicationEventPublisher;

        public void save(Product product) {
            System.out.println("Done save to DB");
            applicationEventPublisher.publishEvent(new ProductEvent(product));
        }
    }

    public static class MessageBrokerObserver implements ApplicationListener<ProductEvent> {
        @Override
        public void onApplicationEvent(ProductEvent event) {
            System.out.println("Kirim message ke Broker");
        }
    }

    public static class RedisObserver implements ApplicationListener<ProductEvent> {
        @Override
        public void onApplicationEvent(ProductEvent event) {
            System.out.println("Kirim message ke Redis");
        }
    }

    public static class LogObserver implements ApplicationListener<ProductEvent>{
        @Override
        public void onApplicationEvent(ProductEvent event) {
            System.out.println("Log message");
        }
    }

    @SpringBootApplication
    public static class Application {

        @Bean
        public ProductRepository productRepository(){
            return new ProductRepository();
        }

        @Bean
        public MessageBrokerObserver messageBrokerObserver(){
            return new MessageBrokerObserver();
        }

        @Bean
        public RedisObserver redisObserver(){
            return new RedisObserver();
        }

        @Bean
        public LogObserver logObserver() {
            return new LogObserver();
        }
    }

    public static void main (String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        productRepository.save(new Product("1", "Eko"));
    }
}
