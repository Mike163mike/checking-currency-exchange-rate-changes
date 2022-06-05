package com.example.mike.checkingcurrencyexchangeratechanges.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainServiceTest {

    @Test
    void compareRates1() {
        MainService mainService = new MainService();
        CurrencyTrend trend = mainService.compareRates(new BigDecimal("1"), new BigDecimal("2"));
        assertEquals(CurrencyTrend.DOWN, trend);
    }

    @Test
    void compareRates2() {
        MainService mainService = new MainService();
        CurrencyTrend trend = mainService.compareRates(new BigDecimal("1"), new BigDecimal("1"));
        assertEquals(CurrencyTrend.CONSTANT, trend);
    }

    @Test
    void compareRates3() {
        MainService mainService = new MainService();
        CurrencyTrend trend = mainService.compareRates(new BigDecimal("2"), new BigDecimal("1"));
        assertEquals(CurrencyTrend.UP, trend);
    }
}