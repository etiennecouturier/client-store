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
                .orElse("N");
    }

    void enrichClientWithSex(Client client) {
        String sex = nameSexRepository
                        .findSexByName(client.getFirstName())
                        .map(NameSex::getSex)
                        .orElse("N");
        client.setSex(sex);
    }

}
