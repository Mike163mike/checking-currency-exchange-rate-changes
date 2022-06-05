package com.example.mike.checkingcurrencyexchangeratechanges.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@NoArgsConstructor
public class MainService {

    public CurrencyTrend compareRates(BigDecimal today, BigDecimal yesterday) {
        if (today.compareTo(yesterday) > 0) {
            return CurrencyTrend.UP;
        } else if (today.compareTo(yesterday) < 0) {
            return CurrencyTrend.DOWN;
        }
        return CurrencyTrend.CONSTANT;
    }
}
