package com.etienne.client.store.repository;

import com.etienne.client.store.model.auth.OpticsUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<OpticsUser, String> {

    Optional<OpticsUser> findByUsername(String username);

}
