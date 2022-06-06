package com.example.mike.exchangechecker.service;

import com.example.mike.exchangechecker.enums.CurrencyTrend;
import com.example.mike.exchangechecker.service.feign.ExchangeRateService;
import com.example.mike.exchangechecker.service.feign.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UtilService {

    private final GifService gifService;
    private final ExchangeRateService exchangeRateService;

    @Value("${tag-broke}")
    private String broke;

    @Value("${tag-rich}")
    private String rich;

    public CurrencyTrend compareRates(BigDecimal today, BigDecimal yesterday) {
        if (today.compareTo(yesterday) > 0) {
            return CurrencyTrend.UP;
        } else if (today.compareTo(yesterday) < 0) {
            return CurrencyTrend.DOWN;
        }
        return CurrencyTrend.CONSTANT;
    }

    public String appropriateGif(String currency) {
        currency = currency.toUpperCase(Locale.ROOT);
        BigDecimal today = exchangeRateService.getLatestRate(currency);
        BigDecimal yesterday = exchangeRateService.getRateOnDate(LocalDate.now().minusDays(1), currency);
        CurrencyTrend trend = compareRates(today, yesterday);
        if (trend.equals(CurrencyTrend.UP) || trend.equals(CurrencyTrend.CONSTANT)) {
            return gifService.getGif(rich);
        } else {
            return gifService.getGif(broke);
        }
    }

    public Map<String, String> currenciesInfo() {
        Map<String, String> currencies = exchangeRateService.getCurrencies();
        if (currencies == null) {
            currencies = new HashMap<>();
            currencies.put("exception", "can't get currency list");
        }
        return currencies;
    }
}
