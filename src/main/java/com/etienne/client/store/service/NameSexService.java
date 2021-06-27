package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.domain.NameSex;
import com.etienne.client.store.repository.NameSexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NameSexService {

    private final NameSexRepository nameSexRepository;

    public String findSexByName(String name) {
        return nameSexRepository.findSexByName(name)
                .map(NameSex::getSex)
                .orElse(findMarriedName(name));
    }

    void enrichClientWithSex(Client client) {
        client.setSex(findSexByName(client.getFirstName()));
    }

    private String findMarriedName(String name) {
        return name.endsWith("né") ?
                nameSexRepository.findSexByName(name.substring(0, name.length() - 2))
                        .map(ns -> "F")
                        .orElse("N")
                : "N";
    }

}
