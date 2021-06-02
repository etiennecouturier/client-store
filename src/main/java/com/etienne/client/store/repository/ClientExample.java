package com.etienne.client.store.repository;

import com.etienne.client.store.model.Client;
import org.springframework.data.domain.Example;

import static org.springframework.data.domain.Example.of;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.matching;

public class ClientExample {

    public static Example<Client> clientExample(Client client) {
        return of(client, matching()
                .withMatcher("name", contains().ignoreCase()));
    }

}
