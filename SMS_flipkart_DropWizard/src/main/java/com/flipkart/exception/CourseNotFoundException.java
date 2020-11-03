package com.flipkart.exception;

/**
 * The type Course not found exception. Exception indicated the course is not available in the course catalog.
 */
public class CourseNotFoundException extends Exception {
    /**
     * Instantiates a new Course not found exception.
     */
    public CourseNotFoundException() {
        super("Course not found.");
    }
}
