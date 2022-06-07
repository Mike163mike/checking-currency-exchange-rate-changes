package com.example.mike.exchangechecker.service.feign;

import com.example.mike.exchangechecker.dto.ExchangeRateDto;
import com.example.mike.exchangechecker.feign.ExchangeRateFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        dto.setRates(Map.of("EUR", new BigDecimal(2)));
        Mockito.when(exchangeRateFeignClient.getLatestRates(any(), any()))
                .thenReturn(dto);
        BigDecimal result = exchangeRateService.getLatestRate("EUR");
        assertEquals(new BigDecimal(2), result);
    }

    @Test
    void getRateOnDate() {
        ExchangeRateDto dto = new ExchangeRateDto();
        dto.setRates((Map.of("EUR", new BigDecimal(2))));
        Mockito.when(exchangeRateFeignClient.getRatesOnDate(any(), any(), any()))
                .thenReturn(dto);
        BigDecimal result = exchangeRateService.getRateOnDate(LocalDate.now().minusDays(1), "EUR");
        assertEquals(new BigDecimal(2), result);
    }

    @Test
    void getCurrencyMap() {
        ExchangeRateDto dto = new ExchangeRateDto();
        dto.setRates((Map.of("RUB", new BigDecimal(3),
                "USD", new BigDecimal(1),
                "EUR", new BigDecimal(2))));
        Mockito.when(exchangeRateFeignClient.getCurrencies()).thenReturn(
                (Map.of("RUB","3", "USD","1","EUR", "2")));
        Map<String, String> result = exchangeRateService.getCurrencies();
        Map<String, String> expected = (Map.of("RUB","3", "USD","1","EUR", "2"));
        assertEquals(expected, result);
    }
}