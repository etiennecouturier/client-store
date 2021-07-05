package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.domain.ClientVisit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {

    @Aggregation(pipeline = {
            "{ $unwind : '$visits'}",
            "{ $match : { 'visits._id' : ?0}}"
    })
    ClientVisit findClientWithVisit(ObjectId visitId);

}
