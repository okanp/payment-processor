package com.okan.source.model;

import com.okan.source.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardAuthenticationRequest {
    @NotNull
    @Pattern(regexp = "[0-9]{13,16}", message = Messages.INVALID_CARD_NUMBER)
    String cardNumber;
    @NotNull
    @Pattern(regexp = "(0[1-9]|10|11|12)/[0-9]{2}$", message = Messages.INVALID_EXPIRATION_DATE)
    String expirationDate;
    @NotNull
    @Pattern(regexp = "[0-9]{3}", message = Messages.INVALID_CVV)
    String cvv;
}
