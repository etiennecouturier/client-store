package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.stats.CountPerDate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

    @Aggregation(pipeline = {
                    "{ $unwind : '$visits'}",
                    "{ $group : {" +
                        "_id: '$visits.date'," +
                        "count: { $sum: 1 }" +
                    "}}",
                    "{ $sort: { _id : -1 } }",
                    "{ $limit: 10}"
    })
    List<CountPerDate> findVisitCountForLast10Days();

}
