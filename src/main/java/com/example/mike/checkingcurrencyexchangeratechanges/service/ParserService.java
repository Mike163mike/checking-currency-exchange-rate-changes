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
import java.time.LocalDate;
import java.util.Locale;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParserService {

    @Value("${exchange-url-now}")
    private String exchangeUrlNow;

    @Value("${openexchangerates-api-key}")
    private String openexchangeratesApiKey;

    @Value("${one-unit}")
    private String oneUnit;

    @Value("${exchange-url-yesterday}")
    private String exchangeUrlYesterday;

    @Value("${historical-part}")
    private String historicalPart;

    private String yesterday = LocalDate.now().minusDays(1).toString();
    String todayCurrencyRate;
    String yesterdayCurrencyRate;

    public String parseResponseTodayRate(String currencyId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.err.println(exchangeUrlNow + openexchangeratesApiKey + oneUnit
                + currencyId.toUpperCase(Locale.ROOT) + "  ****testRequestUrl**************");//***********************
        try {
            HttpGet request = new HttpGet(exchangeUrlNow + openexchangeratesApiKey + oneUnit
                    + currencyId.toUpperCase(Locale.ROOT));

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.err.println(result + " from parseResponseTodayRate()"); //***************************
                    todayCurrencyRate = parseCurrencyJson(currencyId, result);
                }
            }
        } finally {
            httpClient.close();
        }
        return todayCurrencyRate;
    }

    public String parseResponseYesterdaysRate(String currencyId) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            System.err.println(exchangeUrlYesterday + yesterday + historicalPart + openexchangeratesApiKey
                    + oneUnit + currencyId.toUpperCase(Locale.ROOT) + "  ****testRequestUrl**************");//********
            HttpGet request = new HttpGet(exchangeUrlYesterday + yesterday + historicalPart
                    + openexchangeratesApiKey + oneUnit + currencyId.toUpperCase(Locale.ROOT));
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.err.println(result + " from parseResponseYesterdaysRate()"); //*************************
                    yesterdayCurrencyRate = parseCurrencyJson(currencyId, result);
                }
            }
        }
        return yesterdayCurrencyRate;
    }

    private String parseCurrencyJson(String threeLetter, String jsonString) {
        int index = jsonString.indexOf(threeLetter);
        String[] currencyRates = jsonString.substring(index + 6).split(" ");
        String currencyRate = currencyRates[0].trim();
        System.err.println(currencyRate + " from parseCurrencyJson()");//*************************
        return currencyRate;
    }
}

