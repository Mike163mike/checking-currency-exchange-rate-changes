package com.example.mike.checkingcurrencyexchangeratechanges.controller;

import com.example.mike.checkingcurrencyexchangeratechanges.service.MainService;
import com.example.mike.checkingcurrencyexchangeratechanges.service.feign.ExchangeRateService;
import com.example.mike.checkingcurrencyexchangeratechanges.service.feign.GifService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class MainControllerTest {

    @MockBean
    private  MainService mainService;

    @MockBean
    private  GifService gifService;

    @MockBean
    private  ExchangeRateService exchangeRateService;

    @Test
    void info() {
    }

    @Test
    void getCurrencyTrend() {
    }
}