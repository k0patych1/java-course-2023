package edu.hw2.task3.exceptions;

public class ExceedingConnectionAttemptsException extends RuntimeException {
    public ExceedingConnectionAttemptsException(String message, Throwable cause) {
        super(message, cause);
    }
}
