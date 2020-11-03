package com.flipkart.exception;

/**
 * The type Login not found exception. Exception happens when user authentication fails.
 */
public class LoginNotFoundException extends Exception {
    /**
     * Instantiates a new Login not found exception.
     */
    public LoginNotFoundException() {
        super("Failed to login. Please check your username and password.");
    }
}
