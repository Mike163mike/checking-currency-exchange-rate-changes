package com.example.mike.checkingcurrencyexchangeratechanges;

import com.example.mike.checkingcurrencyexchangeratechanges.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class ApplicationConfig {

    @Bean
    public String testingCurrencyId() {
        return "EUR";
    }

    @Bean
    @Autowired
    public String today(ParserService parserService) {
        return parserService.getTodayCurrencyRate();
    }

    @Bean
    @Autowired
    public String yesterday(ParserService parserService) {
        return parserService.getYesterdayCurrencyRate();
    }
}
