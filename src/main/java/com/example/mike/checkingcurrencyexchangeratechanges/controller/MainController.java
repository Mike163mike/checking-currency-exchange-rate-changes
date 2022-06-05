package com.example.mike.checkingcurrencyexchangeratechanges.controller;

import com.example.mike.checkingcurrencyexchangeratechanges.service.CurrencyTrend;
import com.example.mike.checkingcurrencyexchangeratechanges.service.MainService;
import com.example.mike.checkingcurrencyexchangeratechanges.service.feign.ExchangeRateService;
import com.example.mike.checkingcurrencyexchangeratechanges.service.feign.GifService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Api("Main controller.")
public class MainController {

    private final MainService mainService;
    private final GifService gifService;
    private final ExchangeRateService exchangeRateService;

    @Value("${tag-broke}")
    private String broke;

    @Value("${tag-rich}")
    private String rich;

    @GetMapping("/{currencyId}")
    @ApiOperation("Getting the url of appropriate GIF in accordance with actual currency trend.")
    public ResponseEntity<String> getAppropriateGif(@PathVariable String currencyId) {
        BigDecimal today = exchangeRateService.getLatestRate(currencyId);
        BigDecimal yesterday = exchangeRateService.getRateOnDate(LocalDate.now().minusDays(1), currencyId);
        CurrencyTrend trend = mainService.compareRates(today, yesterday);
        String tag;
        if (trend.equals(CurrencyTrend.UP) || trend.equals(CurrencyTrend.CONSTANT)) {
            tag = rich;
        } else {
            tag = broke;
        }
        String gifUrl = gifService.getGif(tag);
        return ResponseEntity.ok(gifUrl);
    }
    
    @GetMapping("/")
    @ApiOperation("Getting complete list with short view of currency type.")
    public ResponseEntity<Map<String, String>> info() {
        Map<String, String> currencies = exchangeRateService.getCurrencyMap();
        if (currencies == null) {
            currencies = new HashMap<>();
            currencies.put("exception", "can't get currency list");
        }
        return ResponseEntity.ok(currencies);
    }
}
