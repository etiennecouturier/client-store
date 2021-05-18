package com.okta.springbootmongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<Client, Long> { }
