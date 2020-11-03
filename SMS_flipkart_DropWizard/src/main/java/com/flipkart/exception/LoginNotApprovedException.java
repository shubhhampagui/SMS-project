package com.flipkart.exception;

/**
 * The type Login not approved exception. Exception happens when user is not approved.
 */
public class LoginNotApprovedException extends Exception {
    /**
     * Instantiates a new Login not approved exception.
     */
    public LoginNotApprovedException() {
        super("User is not approved. Please contact your administrator.");
    }
}
