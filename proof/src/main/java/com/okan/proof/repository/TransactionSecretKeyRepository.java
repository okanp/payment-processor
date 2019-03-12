package com.okan.proof.repository;

import com.okan.proof.model.TransactionSecretKey;
import org.springframework.data.repository.CrudRepository;

public interface TransactionSecretKeyRepository extends CrudRepository<TransactionSecretKey, String> {
}
