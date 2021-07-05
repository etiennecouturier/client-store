package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.domain.ClientVisit;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {

    @Aggregation(pipeline = {
            "{ $unwind : '$visits'}",
            "{ $match : { 'visits._id' : ObjectId('60e2c089c11d542805fddf71')}}"
    })
    ClientVisit findClientWithVisit(String visitId);

}
