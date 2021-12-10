package com.bridgelabz.atmsystem.exception;

/**
 * Purpose : Create a custom exception that extends RuntimeExtension
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
public class AtmCustomException extends RuntimeException {
    public AtmCustomException(String message) {
        super(message);
    }
}
