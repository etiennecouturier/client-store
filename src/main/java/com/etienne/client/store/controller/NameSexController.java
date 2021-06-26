package com.etienne.client.store.controller;

import com.etienne.client.store.service.NameSexService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/name-sex")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NameSexController {

    private final NameSexService nameSexService;

    @GetMapping(path = "/determine-sex/{name}")
    public String findSexByName(@PathVariable String name) {
        return nameSexService.findSexByName(name);
    }

}
