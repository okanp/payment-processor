package com.okan.source.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    String cardNumber;
    String expirationDate;
    String cvv;

    @Override
    public String toString() {
        return "Card [cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + ", cvv=" + cvv + "]";
    }
}
