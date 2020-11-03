package com.flipkart.exception;

/**
 * The type DB operation exception. Exception indicates error is occurred in database query.
 */
public class DBOperationException extends Exception {
    /**
     * Instantiates a new DB operation exception.
     */
    public DBOperationException() {
        super("User operation failed");
    }
}
