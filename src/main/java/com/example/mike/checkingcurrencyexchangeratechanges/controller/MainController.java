package com.example.mike.checkingcurrencyexchangeratechanges.controller;

import com.example.mike.checkingcurrencyexchangeratechanges.service.CurrencyTrend;
import com.example.mike.checkingcurrencyexchangeratechanges.service.GifService;
import com.example.mike.checkingcurrencyexchangeratechanges.service.MainService;
import com.example.mike.checkingcurrencyexchangeratechanges.service.ParserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class MainController {

    private final ParserService parserService;
    private final MainService mainService;
    private final GifService gifService;

    public MainController(ParserService parserService, MainService mainService, GifService gifService) {
        this.parserService = parserService;
        this.mainService = mainService;
        this.gifService = gifService;
    }

    @GetMapping("/{currencyId}")
    public String getCurrencyTrend(@PathVariable("currencyId") String currencyId,
                                   Model model) throws IOException {
        String today = parserService.parseResponseTodayRate(currencyId.toUpperCase(Locale.ROOT));
        String yesterday = parserService.parseResponseYesterdaysRate(currencyId.toUpperCase(Locale.ROOT));
        CurrencyTrend trend = mainService.compareRates(today, yesterday);
        String request = gifService.getGifUrl(mainService);
        model.addAttribute("currencyTrendNow", trend.toString());
        model.addAttribute("requestCurrency", currencyId.toUpperCase(Locale.ROOT));
        model.addAttribute("url", request);
        return "index";
    }
}
