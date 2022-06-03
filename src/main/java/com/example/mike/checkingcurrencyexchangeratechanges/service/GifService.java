package com.example.mike.checkingcurrencyexchangeratechanges.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GifService {

    @Value("${rate-first-part}")
    private String rateFirstPart;

    @Value("${giphy-api-key}")
    private String giphyApiKey;

    @Value("${rate-third-part}")
    private String rateThirdPart;

    @Value("${rich}")
    private String rich;

    @Value("${broke}")
    private String broke;

    @Value("${rate-final}")
    private String rateFinal;

    private MainService mainService;
    private String gifRequest;

    public String getGifUrl(MainService mainService) throws IOException {
        String requestUrl;
        String todayRate = mainService.getTodayRate();
        String yesterdayRate = mainService.getYesterdayRate();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            System.err.println(rateFirstPart + giphyApiKey + rateThirdPart + rich + rateFinal
                    + "  ****testRequestUrlForGif**************");//****************************
            if (mainService.compareRates(todayRate, yesterdayRate).equals(CurrencyTrend.UP)
                    || mainService.compareRates(todayRate, yesterdayRate).equals(CurrencyTrend.CONSTANT)) {
                requestUrl = rateFirstPart + giphyApiKey + rateThirdPart + rich + rateFinal;
            } else {
                requestUrl = rateFirstPart + giphyApiKey + rateThirdPart + broke + rateFinal;
            }
            HttpGet request = new HttpGet(requestUrl);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result + " from getGifUrl()"); //***************************
                    gifRequest = parseGifJson(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gifRequest;
    }

    private String parseGifJson(String jsonGifString) {
        String requestStr = "";
        int index = jsonGifString.indexOf("images");
        String tempStr = jsonGifString.substring(index);
        String[] images = tempStr.split("\"url\":");
        for (String str : images) {
            String[] tempStrs = str.split("\"");
            requestStr = tempStrs[0];
        }
        System.err.println(requestStr + " from parseGifJson()");//*************************
        return requestStr;
    }
}
