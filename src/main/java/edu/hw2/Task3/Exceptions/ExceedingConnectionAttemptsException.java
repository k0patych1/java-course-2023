package edu.hw2.Task3.Exceptions;

public class ExceedingConnectionAttemptsException extends RuntimeException {
    public ExceedingConnectionAttemptsException(String message, Throwable cause) {
        super(message, cause);
    }
}
