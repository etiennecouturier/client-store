package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {}
