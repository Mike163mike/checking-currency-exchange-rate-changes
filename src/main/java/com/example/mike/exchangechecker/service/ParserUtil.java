package com.example.mike.exchangechecker.service;

import com.example.mike.exchangechecker.enums.currencyTrend;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ParserUtil {

    public static currencyTrend compareRates(BigDecimal today, BigDecimal yesterday) {
        if (today.compareTo(yesterday) > 0) {
            return currencyTrend.UP;
        } else if (today.compareTo(yesterday) < 0) {
            return currencyTrend.DOWN;
        }
        return currencyTrend.CONSTANT;
    }
}
