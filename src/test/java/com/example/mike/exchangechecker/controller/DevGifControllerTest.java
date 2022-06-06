package com.example.mike.exchangechecker.controller;

import com.example.mike.exchangechecker.service.feign.ExchangeRateService;
import com.example.mike.exchangechecker.service.feign.GifService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class DevGifControllerTest {

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