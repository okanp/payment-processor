package com.okan.flow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value="TransactionSecretKey")
public class TransactionSecretKey {
    @Id
    private String transactionId;
    private byte[] key;
}
