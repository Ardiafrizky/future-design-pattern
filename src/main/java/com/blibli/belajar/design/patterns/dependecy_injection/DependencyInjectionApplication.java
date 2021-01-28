package com.blibli.belajar.design.patterns.dependecy_injection;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class DependencyInjectionApplication {

    @SpringBootApplication
    public static class Application {

//        @Bean
//        public Foo foo() {
//            return new Foo;
//        }

        // @Component ngereplace kode yg diatas,
        // tapi cuma bisa bikin satu foo
        @Component
        public static class Foo {

        }

        @Component
        @Data
        public static class Bar {

            @Autowired
            private Foo foo;
        }

    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);

        Application.Foo foo = context.getBean(Application.Foo.class);
        Application.Bar bar =  context.getBean(Application.Bar.class);

        System.out.println(foo == bar.getFoo());
    }
}
