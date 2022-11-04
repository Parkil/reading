package com.daekyo.exception;

public class JWTException extends Exception{
    public JWTException(String message, Throwable cause) {
        super(message, cause);
    }
}