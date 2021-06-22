package com.etienne.client.store.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KeepAliveService {

    @Value("${client.store.address}")
    private String address;

    private final RestTemplate restTemplate;

    public KeepAliveService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Scheduled(cron = "${client.store.cron.keep.alive}")
    public void ping() {
        log.info("Keep-alive ping: calling " + address + "/keep-alive");
        restTemplate.getForObject(address + "/keep-alive", String.class);
    }

}
