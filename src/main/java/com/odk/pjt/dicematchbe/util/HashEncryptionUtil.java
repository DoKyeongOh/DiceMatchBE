package com.odk.pjt.dicematchbe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEncryptionUtil {

    public static String encrypt(String algorithm, String message) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] digested = digest.digest("password".getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : digested) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
