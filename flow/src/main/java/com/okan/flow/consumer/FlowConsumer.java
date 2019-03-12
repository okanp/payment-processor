package com.okan.flow.consumer;

import com.google.gson.Gson;
import com.okan.flow.encryption.TransactionEncryption;
import com.okan.flow.exception.EncryptionException;
import com.okan.flow.model.Encrypted;
import com.okan.flow.model.TransactionToken;
import com.okan.flow.model.Transaction;
import com.okan.flow.model.TransactionSecretKey;
import com.okan.flow.repository.TransactionSecretKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class FlowConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowConsumer.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    TransactionSecretKeyRepository transactionSecretKeyRepository;

    @KafkaListener(topics = "input-topic")
    public void receive(Transaction transaction) {
        LOGGER.info("received transaction='{}'", transaction.toString());
        try {
            // 1. encrypt
            Encrypted encrypted = TransactionEncryption.encrypt(transaction.getCard());

            // 2. save <transactionId, key> to redis
            TransactionSecretKey secretKey = new TransactionSecretKey();
            secretKey.setKey(encrypted.getKey());
            secretKey.setTransactionId(transaction.getId().toString());
            transactionSecretKeyRepository.save(secretKey);

            // 3. Send <transactionId, token> to kafka
            TransactionToken transactionToken = new TransactionToken();
            transactionToken.setTransactionId(transaction.getId());
            transactionToken.setToken(encrypted.getToken());
            kafkaTemplate.send("output-topic", new Gson().toJson(transactionToken));

            LOGGER.info("sent transaction='{}'", transaction.getId());
        } catch (EncryptionException e) {
            e.printStackTrace();
        }
    }
}
