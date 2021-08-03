package com.etienne.client.store.repository;

import com.etienne.client.store.model.domain.Sequence;
import com.etienne.client.store.model.exception.SequenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static java.util.Optional.ofNullable;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * https://mkyong.com/mongodb/spring-data-mongodb-auto-sequence-id-example/
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SequenceDaoImpl implements SequenceDao {

    private final MongoOperations mongoOperation;

    @Override
    public long getNextSequenceId(String key) {
        return ofNullable(mongoOperation.findAndModify(
                    new Query(where("_id").is(key)),
                    new Update().inc("seq", 1),
                    new FindAndModifyOptions().returnNew(true),
                    Sequence.class))
                .map(Sequence::getSeq)
                .orElseThrow(SequenceException::new);
    }

}
