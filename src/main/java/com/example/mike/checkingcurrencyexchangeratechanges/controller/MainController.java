package com.example.mike.checkingcurrencyexchangeratechanges.controller;

import com.example.mike.checkingcurrencyexchangeratechanges.service.CurrencyTrend;
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

    public ParserService parserService;
    public MainService mainService;

    @GetMapping("/{currencyId}")
    public String getCurrencyTrend(@PathVariable("currencyId") String currencyId,
                                   Model model) throws IOException {
        String currencyTrendNow = "UP";
        String url = "https://giphy.com/search/rich";
        model.addAttribute("currencyTrendNow", currencyTrendNow);
        model.addAttribute("requestCurrency", currencyId.toUpperCase(Locale.ROOT));
        model.addAttribute("url", url);
        return "index";
    }
}
