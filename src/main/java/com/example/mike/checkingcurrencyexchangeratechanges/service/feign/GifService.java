package com.example.mike.checkingcurrencyexchangeratechanges.service.feign;

import com.example.mike.checkingcurrencyexchangeratechanges.dto.GifDto;
import com.example.mike.checkingcurrencyexchangeratechanges.feign.GiphyFeignClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class GifService {

    private GiphyFeignClient giphyFeignClient;

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
