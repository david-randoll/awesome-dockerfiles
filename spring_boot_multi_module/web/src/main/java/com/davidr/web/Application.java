package com.davidr.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.davidr")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
