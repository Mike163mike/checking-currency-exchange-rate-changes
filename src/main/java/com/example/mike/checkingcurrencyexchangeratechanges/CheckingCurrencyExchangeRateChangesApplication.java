package com.example.mike.checkingcurrencyexchangeratechanges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CheckingCurrencyExchangeRateChangesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckingCurrencyExchangeRateChangesApplication.class, args);
    }

}
