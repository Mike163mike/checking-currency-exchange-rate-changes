package com.example.mike.exchangechecker.feign;

import com.example.mike.exchangechecker.dto.ExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "${feign.exchange-rates-name}", url = "${feign.exchange-rates-url}")
public interface ExchangeRateFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "latest.json")
    ExchangeRateDto getLatestRates(@RequestParam("app_id") String appId,
                                   @RequestParam("symbols") String currency);

    @RequestMapping(method = RequestMethod.GET, value = "historical/{date}.json")
    ExchangeRateDto getRatesOnDate(@PathVariable("date") String date,
                                   @RequestParam("app_id") String appId,
                                   @RequestParam("symbols") String currency);

    @RequestMapping(method = RequestMethod.GET, value = "currencies.json")
    Map<String, String> getCurrencies();
}
