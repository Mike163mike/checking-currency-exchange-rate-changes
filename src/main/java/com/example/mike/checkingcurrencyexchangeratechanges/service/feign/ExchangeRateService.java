package com.example.mike.checkingcurrencyexchangeratechanges.service.feign;

import com.example.mike.checkingcurrencyexchangeratechanges.dto.CurrencyListDto;
import com.example.mike.checkingcurrencyexchangeratechanges.dto.ExchangeRateDto;
import com.example.mike.checkingcurrencyexchangeratechanges.feign.ExchangeRateFeignClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateService {

    private ExchangeRateFeignClient exchangeRateFeignClient;

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

    public Map<String, String> getCurrencyMap() {
        CurrencyListDto listDto = exchangeRateFeignClient.getCurrencyMaps();
        return listDto.getCurrency();
    }
}
