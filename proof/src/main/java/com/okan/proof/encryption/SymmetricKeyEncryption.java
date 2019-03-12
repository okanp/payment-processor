package com.okan.proof.encryption;

import com.okan.proof.exception.EncryptionException;
import com.okan.proof.model.Encrypted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class SymmetricKeyEncryption {

    private static final Logger LOGGER = LoggerFactory.getLogger(SymmetricKeyEncryption.class);

    public static Encrypted encrypt(byte[] data) throws EncryptionException {
        try {
            Cipher c = Cipher.getInstance("AES");
            SecretKey key = KeyGenerator.getInstance("AES").generateKey();
            SecretKeySpec k = new SecretKeySpec(key.getEncoded(), "AES");
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] encryptedData = c.doFinal(data);
            Encrypted encrypted = new Encrypted();
            encrypted.setKey(key.getEncoded());
            encrypted.setToken(encryptedData);
            return encrypted;
        } catch (Exception e) {
            LOGGER.error("Exception occurred during encryption");
            e.printStackTrace();
            throw new EncryptionException();
        }
    }

    public static byte[] decrypt(byte[] key, byte[] encryptedData) throws EncryptionException {
        try {
            Cipher c = Cipher.getInstance("AES");
            SecretKeySpec k = new SecretKeySpec(key, "AES");
            c.init(Cipher.DECRYPT_MODE, k);
            return c.doFinal(encryptedData);
        } catch (Exception e) {
            LOGGER.error("Exception occurred during encryption");
            e.printStackTrace();
            throw new EncryptionException();
        }
    }
}
