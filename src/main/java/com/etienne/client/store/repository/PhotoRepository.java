package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.UserPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends MongoRepository<UserPhoto, String> {

    Optional<UserPhoto> findUserPhotoByName(String name);

}
