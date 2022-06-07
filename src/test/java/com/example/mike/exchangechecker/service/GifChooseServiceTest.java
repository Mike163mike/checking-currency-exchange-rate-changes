package com.example.mike.exchangechecker.service;

import com.example.mike.exchangechecker.dto.ExchangeRateDto;
import com.example.mike.exchangechecker.enums.currencyTrend;
import com.example.mike.exchangechecker.service.feign.ExchangeRateService;
import com.example.mike.exchangechecker.service.feign.GifService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class GifChooseServiceTest {

    @MockBean
    private GifService gifService;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Autowired
    private GifChooseService gifChooseService;

    @Test
    void appropriateGif() {
        //currencyTrend trend = currencyTrend.UP;
        Mockito.when(gifService.getGif("UP")).thenReturn("https://developers.giphy.com/");
        String result = gifChooseService.appropriateGif("EUR");
        String expected = "https://developers.giphy.com/";
        assertEquals(expected, result);
    }

    @Test
    void getActualCurrencyTrend() {
        Mockito.when(exchangeRateService.getLatestRate(any())).thenReturn(new BigDecimal(2));
        Mockito.when(exchangeRateService.getRateOnDate(any(), any())).thenReturn(new BigDecimal(1));
        currencyTrend result = gifChooseService.getActualCurrencyTrend("EUR");
        currencyTrend expected = currencyTrend.UP;
        assertEquals(expected, result);
    }

    @Test
    void currenciesInfo() {
        Mockito.when(exchangeRateService.getCurrencies()).thenReturn(
                (Map.of("RUB","3", "USD","1","EUR", "2")));
        Map<String, String> result = exchangeRateService.getCurrencies();
        Map<String, String> expected = (Map.of("RUB","3", "USD","1","EUR", "2"));
        assertEquals(expected, result);
    }
}