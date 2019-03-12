package com.okan.source.controller;

import com.google.gson.Gson;
import com.okan.source.model.Card;
import com.okan.source.model.CardAuthenticationRequest;
import com.okan.source.model.CardAuthenticationResponse;
import com.okan.source.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "/auth", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity auth(@Valid @RequestBody CardAuthenticationRequest request, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(new CardAuthenticationResponse(null, false, errorMessage), HttpStatus.OK);
        }

        // queue transaction
        Card card = new Card(request.getCardNumber(), request.getExpirationDate(), request.getCvv());
        Transaction transaction = new Transaction(card);
        kafkaTemplate.send("input-topic", new Gson().toJson(transaction));
        LOGGER.info("Sent: {}", transaction.toString());

        // return response with transaction id
        CardAuthenticationResponse response = new CardAuthenticationResponse(transaction.getId(), true, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
