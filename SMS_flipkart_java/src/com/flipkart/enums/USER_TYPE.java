package com.flipkart.enums;

/**
 * The enum User type.
 */
public enum USER_TYPE {
    /**
     * Admin user type.
     */
    ADMIN(0),
    /**
     * Professor user type.
     */
    PROFESSOR(1),
    /**
     * Student user type.
     */
    STUDENT(2);

    private final int value;

    USER_TYPE(int value) {
        this.value = value;
    }

    /**
     * Gets value of the enum.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
