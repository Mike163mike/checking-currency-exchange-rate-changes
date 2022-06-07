package com.example.mike.exchangechecker.service;

import com.example.mike.exchangechecker.enums.currencyTrend;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ParserUtilTest {

    @Test
    void compareRates_Down() {
        currencyTrend trend = ParserUtil.compareRates(new BigDecimal("1"), new BigDecimal("2"));
        assertEquals(currencyTrend.DOWN, trend);
    }

    @Test
    void compareRates_Constant() {
        currencyTrend trend = ParserUtil.compareRates(new BigDecimal("1"), new BigDecimal("1"));
        assertEquals(currencyTrend.CONSTANT, trend);
    }

    @Test
    void compareRates_Up() {
        currencyTrend trend = ParserUtil.compareRates(new BigDecimal("2"), new BigDecimal("1"));
        assertEquals(currencyTrend.UP, trend);
    }
}