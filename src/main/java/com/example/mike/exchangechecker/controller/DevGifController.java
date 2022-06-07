package com.example.mike.exchangechecker.controller;

import com.example.mike.exchangechecker.service.GifChooseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
//@Tag(name = "Dev-gif-controller", description = "Dev gif controller")
@RequestMapping("/dev")
public class DevGifController {

    private final GifChooseService gifChooseService;

    @RequestMapping(method = RequestMethod.GET, value = "/v1/check-exchange/{id}")
    @ApiOperation("Getting the url of appropriate GIF in accordance with actual currency trend.")
    public ResponseEntity<String> getAppropriateGif(@PathVariable("id") String currency) {
        return ResponseEntity.ok(gifChooseService.appropriateGif(currency));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/v1/info")
    @ApiOperation("Getting complete list with short view of currency type.")
    public ResponseEntity<Map<String, String>> info() {
        return ResponseEntity.ok(gifChooseService.currenciesInfo());
    }
}
