package com.example.mike.exchangechecker.service.feign;

import com.example.mike.exchangechecker.dto.ExchangeRateDto;
import com.example.mike.exchangechecker.feign.ExchangeRateFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateFeignClient exchangeRateFeignClient;

    @Value("${openexchangerates-api-key}")
    private String token;

    public BigDecimal getLatestRate(String currency) {
        if (currency == null) {
            currency = "USD";
        }
        ExchangeRateDto rateDto = exchangeRateFeignClient.getLatestRates(
                token,
                currency);
        return rateDto.getRates().get(currency);
    }

    public BigDecimal getRateOnDate(LocalDate date, String currency) {
        if (currency == null) {
            currency = "USD";
        }
        ExchangeRateDto rateDto = exchangeRateFeignClient.getRatesOnDate(
                date.toString(),
                token,
                currency);
        return rateDto.getRates().get(currency);
    }

    public Map<String, String> getCurrencies() {
        return exchangeRateFeignClient.getCurrencies();
    }
}
