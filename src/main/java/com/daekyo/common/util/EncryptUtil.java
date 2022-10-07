package com.daekyo.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    private EncryptUtil() {}

    public static String hashSHA256(String plainStr) {
        byte[] hashByte = hashPassword(plainStr);
        return toHex(hashByte);
    }
    private static byte[] hashPassword(final String password) {
        final byte[] encodedPassword = password.getBytes(StandardCharsets.UTF_8);
        final MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        return algorithm.digest(encodedPassword);
    }

    private static String toHex(final byte[] data) {
        final StringBuilder sb = new StringBuilder(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%02X", data[i]));
        }
        return sb.toString();
    }
}