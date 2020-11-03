package com.flipkart.bean;

import java.util.List;

/**
 * The class contains information of all the courses assigned to a Student
 */
public class Student {
    private int userId;
    private List<CourseData> courses = null;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets courses.
     *
     * @return the courses
     */
    public List<CourseData> getCourses() {
        return courses;
    }

    /**
     * Sets courses.
     *
     * @param courses the courses
     */
    public void setCourses(List<CourseData> courses) {
        this.courses = courses;
    }
}
