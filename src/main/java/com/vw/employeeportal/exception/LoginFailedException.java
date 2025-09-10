package com.vw.employeeportal.exception;

/**
 * A custom exception thrown when user authentication fails for any reason
 * (e.g., bad password, locked account).
 */
public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message);
    }
}