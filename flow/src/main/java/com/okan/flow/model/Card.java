package com.okan.flow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    String expirationDate;
    String cardNumber;
    String cvv;

    @Override
    public String toString() {
        return "Card [cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + ", cvv=" + cvv + "]";
    }
}
