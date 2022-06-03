package com.example.mike.checkingcurrencyexchangeratechanges.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MainService {

    private ParserService parserService;
    private String todayRate;
    private String yesterdayRate;

    public CurrencyTrend compareRates(String todayRate, String yesterdayRate) {
        BigDecimal today = new BigDecimal(todayRate);
        BigDecimal yesterday = new BigDecimal(yesterdayRate);
        if (today.compareTo(yesterday) > 0) {
            System.err.println(CurrencyTrend.UP + "  ****testMainService****");//************************
            return CurrencyTrend.UP;
        } else if (today.compareTo(yesterday) < 0) {
            System.err.println(CurrencyTrend.DOWN + "  ****testMainService****");//************************
            return CurrencyTrend.DOWN;
        }
        System.err.println(CurrencyTrend.CONSTANT + "  ****testMainService****");//************************
        return CurrencyTrend.CONSTANT;
    }
}
