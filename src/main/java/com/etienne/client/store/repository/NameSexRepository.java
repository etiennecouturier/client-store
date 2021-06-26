package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.NameSex;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NameSexRepository extends MongoRepository<NameSex, String> {

    Optional<NameSex> findSexByName(String name);

}
