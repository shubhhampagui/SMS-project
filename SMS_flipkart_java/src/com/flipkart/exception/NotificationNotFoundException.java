package com.flipkart.exception;

/**
 * The type Notification not found exception. Exception happens when notification is not found in the database.
 */
public class NotificationNotFoundException extends Exception {
    /**
     * Instantiates a new Notification not found exception.
     */
    public NotificationNotFoundException() {
        super("Failed to find the payment.");
    }
}
