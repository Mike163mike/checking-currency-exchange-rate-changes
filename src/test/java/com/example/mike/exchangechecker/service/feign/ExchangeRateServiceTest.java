package com.example.mike.exchangechecker.service.feign;

import com.example.mike.exchangechecker.dto.ExchangeRateDto;
import com.example.mike.exchangechecker.feign.ExchangeRateFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ExchangeRateServiceTest {

    @MockBean
    private ExchangeRateFeignClient exchangeRateFeignClient;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Test
    void getLatestRate() {
        ExchangeRateDto dto = new ExchangeRateDto();
        dto.setRates(Map.of("RUB", new BigDecimal(3),
                "USD", new BigDecimal(1),
                "EUR", new BigDecimal(2)));
        Mockito.when(exchangeRateFeignClient.getLatestRates(any(), any()))
                .thenReturn(dto);

        BigDecimal result = exchangeRateService.getLatestRate("USD");
        assertEquals(new BigDecimal("1"), result);
    }

    @Test
    void getRateOnDate() {
    }

    @Test
    void getCurrencyMap() {
//        CurrencyListDto currencyListDto = exchangeRateFeignClient.getCurrencies();

    }
}