package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.List;

/**
 * The interface for course catalog
 */
public interface CourseManagementService {
    /**
     * Add course to the course catalog
     *
     * @param course the course
     * @return the boolean indicating the result of addition of course
     */
    boolean addCourse(Course course);

    /**
     * Delete course having given course id
     *
     * @param courseId the course id
     * @return the boolean indicating the result of course deletition
     */
    boolean deleteCourse(int courseId);

    /**
     * Update course information
     *
     * @param course the course to be updated
     * @return the boolean indicating the result of course update
     */
    boolean updateCourse(Course course);

    /**
     * Gets course from course id
     *
     * @param courseId the course id of the required course
     * @return the course
     */
    Course getCourse(int courseId);

    /**
     * Gets all the courses list
     *
     * @return the courses list
     */
    List<Course> getCourses();
}
