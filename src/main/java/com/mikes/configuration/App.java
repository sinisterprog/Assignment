package com.mikes.configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "com.mikes")

public class App {

        public static void main(String[] args) {
            SpringApplication.run(App.class, args);

    }
}
