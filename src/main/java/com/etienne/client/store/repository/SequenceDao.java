package com.etienne.client.store.repository;

import com.etienne.client.store.model.exception.SequenceException;

public interface SequenceDao {
    long getNextSequenceId(String key) throws SequenceException;
}
