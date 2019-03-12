package com.okan.flow.encryption;

import com.google.gson.Gson;
import com.okan.flow.exception.EncryptionException;
import com.okan.flow.model.Card;
import com.okan.flow.model.Encrypted;

public class TransactionEncryption {

    public static Encrypted encrypt(Card card) throws EncryptionException {
        return SymmetricKeyEncryption.encrypt(new Gson().toJson(card).getBytes());
    }

    public static Card decrypt(byte[] key, byte[] encryptedData) throws EncryptionException {
        byte[] data = SymmetricKeyEncryption.decrypt(key, encryptedData);
        String result = new String(data);
        Card card = new Gson().fromJson(result, Card.class);
        return card;
    }

}
