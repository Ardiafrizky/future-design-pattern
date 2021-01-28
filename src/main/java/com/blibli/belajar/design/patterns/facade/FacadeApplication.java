package com.blibli.belajar.design.patterns.facade;

import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class FacadeApplication {

    public static interface AddressService {

        void save(String customerId, String address, String city);
        void remove(String customerId);
        void update(String customerId, String address, String city);
    }

    public static class AddressController {
        @Setter
        private AddressService addressService;

        public void saveAddress(String customerId, String address, String city) {
            System.out.println("Controller");
            addressService.save(customerId, address, city);
        }
    }

    public static class AddressServiceImplPostgre implements AddressService {

        @Override
        public void save(String customerId, String address, String city) {
            System.out.println("Implementation postgre save");
        }

        @Override
        public void update(String customerId, String address, String city) {
            System.out.println("Implementation postgre update");
        }

        @Override
        public void remove(String customerId) {
            System.out.println("Implementation postgre remove");
        }
    }

    public static class AddressServiceImplMongo implements AddressService {

        @Override
        public void save(String customerId, String address, String city) {
            System.out.println("Implementation mongo save");
        }

        @Override
        public void update(String customerId, String address, String city) {
            System.out.println("Implementation mongo update");
        }

        @Override
        public void remove(String customerId) {
            System.out.println("Implementation mongo remove");
        }
    }

    @SpringBootApplication
    public static class Application {

//        @Bean
        public AddressService addressServicePostgre() {
            return new AddressServiceImplPostgre();
        }

        @Bean
        public AddressService addressServiceMongo() {
            return new AddressServiceImplMongo();
        }

        @Bean
        public AddressController addressController(AddressService addressService) {
            AddressController controller = new AddressController();
            controller.setAddressService(addressService);
            return controller;
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        AddressController controller = context.getBean(AddressController.class);

        controller.saveAddress("","","");
    }
}
