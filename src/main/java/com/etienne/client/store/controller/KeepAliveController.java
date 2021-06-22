package com.etienne.client.store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepAliveController {

    @GetMapping(path = "/keep-alive")
    public String keepAlive() {
        return "successful ping";
    }

}
