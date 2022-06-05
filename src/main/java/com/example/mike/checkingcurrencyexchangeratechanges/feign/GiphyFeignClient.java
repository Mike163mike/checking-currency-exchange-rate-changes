package com.example.mike.checkingcurrencyexchangeratechanges.feign;

import com.example.mike.checkingcurrencyexchangeratechanges.dto.GifDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.giphy-name}", url = "${feign.giphy-url}")
public interface GiphyFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/gifs/search")
    GifDto getGif(@RequestParam("api_key") String apiKey,
                  @RequestParam("q") String type,
                  @RequestParam("limit") Integer limit,
                  @RequestParam("offset") Integer offset,
                  @RequestParam("rating") String rating,
                  @RequestParam("lang") String lang);
}
