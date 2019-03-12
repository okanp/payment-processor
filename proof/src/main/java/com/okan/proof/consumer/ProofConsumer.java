package com.okan.proof.consumer;

import com.google.gson.Gson;
import com.okan.proof.encryption.TransactionEncryption;
import com.okan.proof.model.Card;
import com.okan.proof.model.TransactionToken;
import com.okan.proof.model.Transaction;
import com.okan.proof.model.TransactionSecretKey;
import com.okan.proof.repository.TransactionSecretKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProofConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProofConsumer.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    TransactionSecretKeyRepository transactionSecretKeyRepository;

    @KafkaListener(topics = "output-topic")
    public void receive(TransactionToken transactionToken) {
        try {
            LOGGER.info("received token='{}'", transactionToken.toString());
            TransactionSecretKey secretKey = transactionSecretKeyRepository.findById(transactionToken.getTransactionId().toString())
                    .orElseThrow(Exception::new);

            Card card = TransactionEncryption.decrypt(secretKey.getKey(), transactionToken.getToken());
            Transaction transaction = new Transaction();
            transaction.setId(transactionToken.getTransactionId());
            transaction.setCard(card);
            LOGGER.info("{}", new Gson().toJson(transaction));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
