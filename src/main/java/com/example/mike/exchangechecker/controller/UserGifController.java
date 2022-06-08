package com.example.mike.exchangechecker.controller;

import com.example.mike.exchangechecker.enums.currencyTrend;
import com.example.mike.exchangechecker.service.GifChooseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserGifController {

    private final GifChooseService gifChooseService;

    @RequestMapping(method = RequestMethod.GET, value = "/currency/{id}")
    @ApiOperation("Getting the url of appropriate GIF in accordance with actual currency trend.")
    public String getAppropriateGif(@PathVariable("id") String currency,
                                    Model model) {
        currency = currency.toUpperCase(Locale.ROOT);
        currencyTrend trend = gifChooseService.getActualCurrencyTrend(currency);
        String gifUrl = gifChooseService.appropriateGif(currency);
        model.addAttribute("currency", currency);
        model.addAttribute("trend", trend);
        model.addAttribute("gifUrl", gifUrl);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/currency")
    @ApiOperation("Getting complete list with short view of currency type.")
    public String info(Model model) {
        Map<String, String> currencies = gifChooseService.currenciesInfo();
        model.addAttribute("currencies", currencies);
        return "info";
    }
}
