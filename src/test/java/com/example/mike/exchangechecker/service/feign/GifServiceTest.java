package com.example.mike.exchangechecker.service.feign;

import com.example.mike.exchangechecker.dto.DataDto;
import com.example.mike.exchangechecker.dto.GifDto;
import com.example.mike.exchangechecker.dto.InfoDto;
import com.example.mike.exchangechecker.feign.GiphyFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;
import java.util.Map;

@SpringBootTest
class GifServiceTest {

    @MockBean
    private GiphyFeignClient giphyFeignClient;

    @Autowired
    private GifService gifService;

    @Test
    void getGif() {
        GifDto gifDto = new GifDto();
        InfoDto infoDto = new InfoDto("https://developers.giphy.com/");
        DataDto dataDto = new DataDto(Map.of("any", infoDto));
        gifDto.setData(List.of(dataDto));
        Mockito.when(giphyFeignClient.getGif(any(), any(), any(), any(), any(), any()))
                .thenReturn(gifDto);
        String result = gifService.getGif("broke");
        String expectedUrl = "https://developers.giphy.com/";
        assertEquals(expectedUrl, result);
    }
}