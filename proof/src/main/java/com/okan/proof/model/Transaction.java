package com.okan.proof.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    UUID id = UUID.randomUUID();
    Card card;
    
    @Override
    public String toString() {
        return "Transaction [id=" + id.toString() + ", cardNumber=" + card.getCardNumber() + ", cardExpirationDate=" + card.getExpirationDate() + ", cardCvv=" + card.getCvv() + "]";
    }
}
