package com.flipkart.exception;

/**
 * The type Payment not found exception. Exception happens when payment is not found in the database.
 */
public class PaymentNotFoundException extends Exception {
    /**
     * Instantiates a new Payment not found exception.
     */
    public PaymentNotFoundException() {
        super("Failed to find the payment.");
    }
}
