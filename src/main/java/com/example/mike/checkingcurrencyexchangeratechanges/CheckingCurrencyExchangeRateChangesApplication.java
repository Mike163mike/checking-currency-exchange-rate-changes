package com.example.mike.checkingcurrencyexchangeratechanges;

import com.example.mike.checkingcurrencyexchangeratechanges.service.CurrencyTrend;
import com.example.mike.checkingcurrencyexchangeratechanges.service.MainService;
import com.example.mike.checkingcurrencyexchangeratechanges.service.ParserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Locale;

@SpringBootApplication
public class CheckingCurrencyExchangeRateChangesApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CheckingCurrencyExchangeRateChangesApplication.class, args);
//
//        ParserService parserService = new ParserService();
//        String currencyId = "EUR";
//        String today = parserService.parseResponsePresentRate(currencyId.toUpperCase(Locale.ROOT));
//        String yesterday = parserService.parseResponseYesterdaysRate(currencyId.toUpperCase(Locale.ROOT));
//        MainService mainService = new MainService();
//        CurrencyTrend trend = mainService.compareRates(today, yesterday);
//        System.err.println(trend);
    }

}
