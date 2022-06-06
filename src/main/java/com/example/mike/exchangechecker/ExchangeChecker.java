package com.example.mike.exchangechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeChecker {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeChecker.class, args);
    }

}
