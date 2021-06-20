package com.etienne.client.store.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

//    @Scheduled(fixedRate = 1000 * 60 * 20)
    public void ping() {
        restTemplate.getForObject("https://optics-client-store.herokuapp.com/ping", String.class);
    }

}
