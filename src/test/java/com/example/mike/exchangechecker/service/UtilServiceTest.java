package com.example.mike.exchangechecker.service;

import com.example.mike.exchangechecker.enums.CurrencyTrend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UtilServiceTest {

    @Autowired
    private UtilService utilService;

    @Test
    void compareRates_Down() {
        CurrencyTrend trend = utilService.compareRates(new BigDecimal("1"), new BigDecimal("2"));
        assertEquals(CurrencyTrend.DOWN, trend);
    }

    @Test
    void compareRates_Constant() {
        CurrencyTrend trend = utilService.compareRates(new BigDecimal("1"), new BigDecimal("1"));
        assertEquals(CurrencyTrend.CONSTANT, trend);
    }

    @Test
    void compareRates_Up() {
        CurrencyTrend trend = utilService.compareRates(new BigDecimal("2"), new BigDecimal("1"));
        assertEquals(CurrencyTrend.UP, trend);
    }
}