package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.stats.CountPerAge;
import com.etienne.client.store.model.stats.CountPerDate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

    @Aggregation(pipeline = {
                    "{ $unwind : '$visits'}",
                    "{ $group : {_id: '$visits.date', count: { $sum: 1 }}}",
                    "{ $sort: { _id : -1 } }",
                    "{ $limit: 10}",
                    "{ $sort: { _id : 1 } }",
                    "{ $project: { _id: 0, date: $_id, count: 1 }}"
    })
    List<CountPerDate> findVisitCountForLast10Days();

//    https://gist.github.com/Haleluak/82a21e3fe6c4ccda1d9cc0633d60da25
    @Aggregation(pipeline = {
            "{" +
            "  $project: {    " +
            "    'range': {" +
            "       $concat: [" +
            "          { $cond: [{$lte: ['$age',0]}, 'nem ismert', '']}, " +
            "          { $cond: [{$and:[ {$gt:['$age', 0 ]}, {$lt: ['$age', 11]}]}, '0 - 10', ''] }," +
            "          { $cond: [{$and:[ {$gte:['$age',11]}, {$lt:['$age', 21]}]}, '11 - 20', '']}," +
            "          { $cond: [{$and:[ {$gte:['$age',21]}, {$lt:['$age', 31]}]}, '21 - 30', '']}," +
            "          { $cond: [{$and:[ {$gte:['$age',31]}, {$lt:['$age', 41]}]}, '31 - 40', '']}," +
            "          { $cond: [{$and:[ {$gte:['$age',41]}, {$lt:['$age', 51]}]}, '41 - 50', '']}," +
            "          { $cond: [{$and:[ {$gte:['$age',51]}, {$lt:['$age', 61]}]}, '51 - 60', '']}," +
            "          { $cond: [{$and:[ {$gte:['$age',61]}, {$lt:['$age', 71]}]}, '61 - 70', '']}," +
            "          { $cond: [{$and:[ {$gte:['$age',71]}, {$lt:['$age', 81]}]}, '71 - 80', '']}," +
            "          { $cond: [{$gte:['$age',81]}, '81 -', '']}" +
            "       ]" +
            "    }  " +
            "  }    " +
            "}",
            "{" +
            "  $group: { " +
            "    '_id' : '$range', " +
            "    count: { " +
            "      $sum: 1" +
            "    } " +
            "  }" +
            "}",
            "{ $sort: { _id : 1 } }",
            "{ $project: { _id: 0, range: $_id, count: 1 }}",
    })
    List<CountPerAge> findVisitorCountPerAge();

}
