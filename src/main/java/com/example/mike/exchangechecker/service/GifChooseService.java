package com.example.mike.exchangechecker.service;

import com.example.mike.exchangechecker.enums.currencyTrend;
import com.example.mike.exchangechecker.service.feign.ExchangeRateService;
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
public class GifChooseService {

    private final com.example.mike.exchangechecker.service.feign.GifService gifService;
    private final ExchangeRateService exchangeRateService;

    @Value("${tag-broke}")
    private String broke;

    @Value("${tag-rich}")
    private String rich;

    public String appropriateGif(String currency) {
        currencyTrend trend = getActualCurrencyTrend(currency);
        if (trend.equals(currencyTrend.UP) || trend.equals(currencyTrend.CONSTANT)) {
            return gifService.getGif(rich);
        } else {
            return gifService.getGif(broke);
        }
    }

    public currencyTrend getActualCurrencyTrend(String currency) {
        currency = currency.toUpperCase(Locale.ROOT);
        BigDecimal today = exchangeRateService.getLatestRate(currency);
        BigDecimal yesterday = exchangeRateService.getRateOnDate(LocalDate.now().minusDays(1), currency);
        return ParserUtil.compareRates(today, yesterday);
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
