package com.okan.source.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardAuthenticationResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    UUID transactionId;
    boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;
}
