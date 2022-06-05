package com.example.mike.checkingcurrencyexchangeratechanges.service.feign;

import com.example.mike.checkingcurrencyexchangeratechanges.dto.ExchangeRateDto;
import com.example.mike.checkingcurrencyexchangeratechanges.feign.ExchangeRateFeignClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class ExchangeRateServiceTest {

//    @Mock
//    private ExchangeRateFeignClient exchangeRateFeignClient;

    @Test
    void getLatestRate() {
        Map<String, BigDecimal> mockMap = new HashMap<>();
        mockMap.put("EUR", new BigDecimal("1.1"));
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setRates(mockMap);
        ExchangeRateService exchangeRateService = new ExchangeRateService();
        BigDecimal result = exchangeRateDto.getRates().get("EUR");
        Assertions.assertEquals(new BigDecimal("1.1"), result);
    }

    @Test
    void getRateOnDate() {
    }

    @Test
    void getCurrencyMap() {
    }
}