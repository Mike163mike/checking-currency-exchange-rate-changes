package com.example.mike.checkingcurrencyexchangeratechanges.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    public CurrencyTrend compareRates(@Qualifier("today") String todayRate,
                                      @Qualifier("yesterday") String yesterdayRate) {
        BigDecimal today = new BigDecimal(todayRate);
        BigDecimal yesterday = new BigDecimal(yesterdayRate);
        if (today.compareTo(yesterday) > 0) {
            System.err.println(CurrencyTrend.UP + "  ***test****");//************************
            return CurrencyTrend.UP;
        } else if (today.compareTo(yesterday) < 0) {
            System.err.println(CurrencyTrend.DOWN + "  ***test****");//************************
            return CurrencyTrend.DOWN;
        }
        System.err.println(CurrencyTrend.CONSTANT + "  ***test****");//************************
        return CurrencyTrend.CONSTANT;
    }
}
