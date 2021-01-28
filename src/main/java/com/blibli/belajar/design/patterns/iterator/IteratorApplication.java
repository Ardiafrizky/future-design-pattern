package com.blibli.belajar.design.patterns.iterator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.jar.Manifest;

public class IteratorApplication {

    public enum PaymentType {
        BCA, MANDIRI
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Payment {
        private PaymentType paymentType;
        private Long amount;
    }

    public static interface PaymentService {
        boolean isSupport(PaymentType paymentType);
        void pay(Payment payment);
    }

    public static class MandiriCardPaymentService implements PaymentService {

        @Override
        public boolean isSupport(PaymentType paymentType) {
            return paymentType.equals(PaymentType.MANDIRI);
        }

        @Override
        public void pay(Payment payment) {
            System.out.println("Bayar Mandiri");
        }
    }

    public static class BCACardPaymentService implements PaymentService {

        @Override
        public boolean isSupport(PaymentType paymentType) {
            return paymentType.equals(PaymentType.BCA);
        }

        @Override
        public void pay(Payment payment) {
            System.out.println("Bayar BCA");
        }
    }

    @SpringBootApplication
    public static class Application {

        @Bean
        public MandiriCardPaymentService mandiriCardPaymentService(){
            return new MandiriCardPaymentService();
        }

        @Bean
        public BCACardPaymentService bcaCardPaymentService(){
            return new BCACardPaymentService();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        Payment payment = Payment.builder().paymentType(PaymentType.BCA).amount(10000L).build();

        context.getBeansOfType(PaymentService.class).values()
                .stream()
                .filter(paymentService -> paymentService.isSupport(payment.getPaymentType()))
                .findFirst()
                .ifPresent(paymentService -> paymentService.pay(payment));
    }
}
