package com.daekyo.spring_security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainPasswordEncoder implements PasswordEncoder {
    private static final Logger logger = LoggerFactory.getLogger(PlainPasswordEncoder.class);

    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String encodeRawPassword = encode(rawPassword.toString());
        logger.info("matches : encodeRawPassword : {}, encodedPassword : {}", encodeRawPassword, encodedPassword);
        return encodedPassword.equals(encodeRawPassword);
    }
}