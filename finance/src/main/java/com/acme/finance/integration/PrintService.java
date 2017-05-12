package com.acme.finance.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hoeckxer on 05/03/2017.
 */
@Component
public class PrintService {
    @Value("${printservice.url}")
    private String printServiceUrl;

    private final RestTemplate restTemplate;

    public PrintService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void printLetter(String name, String address, String content) {
        RestTemplate restTemplate = new RestTemplate();

        PrintRequest printRequest = new PrintRequest();
        HttpEntity<PrintRequest> request = new HttpEntity<>(printRequest);
        restTemplate.postForLocation(printServiceUrl,request);
    }
}
