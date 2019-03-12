package com.okan.flow.repository;

import com.okan.flow.model.TransactionSecretKey;
import org.springframework.data.repository.CrudRepository;

public interface TransactionSecretKeyRepository extends CrudRepository<TransactionSecretKey, String> {
}
