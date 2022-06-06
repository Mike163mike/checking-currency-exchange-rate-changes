package com.example.mike.exchangechecker.service.feign;

import com.example.mike.exchangechecker.dto.GifDto;
import com.example.mike.exchangechecker.feign.GiphyFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GifService {

    private final GiphyFeignClient giphyFeignClient;

    @Value("${giphy-api-key}")
    private String token;

    @Value("${tag-broke}")
    private String broke;

    @Value("${tag-rich}")
    private String rich;

    public String getGif(String type) {
        if (!type.equals(broke) && !type.equals(rich)) {
            type = rich;
        }
        GifDto gifDto = giphyFeignClient.getGif(token,
                type, 1, 1, "g", "en");
        return gifDto.getData().get(0).getImages().get("original").getUrl();
    }
}
