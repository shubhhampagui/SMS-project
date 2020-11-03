package com.flipkart.exception;

/**
 * The type User not found exception. Exception happens when user is not found in the database.
 */
public class UserNotFoundException extends Exception {
    /**
     * Instantiates a new User not found exception.
     */
    public UserNotFoundException() {
        super("User not found.");
    }
}
