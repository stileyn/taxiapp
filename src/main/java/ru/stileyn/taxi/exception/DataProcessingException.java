package ru.stileyn.taxi.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
