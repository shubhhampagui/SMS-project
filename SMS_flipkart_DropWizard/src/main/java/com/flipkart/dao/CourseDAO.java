package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;

/**
 * The interface Course dao implementation
 */
public interface CourseDAO {
    /**
     * Add course to course catalog
     *
     * @param course the course
     */
    void addCourse(Course course);

    /**
     * Delete course from course catalog
     *
     * @param courseId the course id
     * @throws CourseNotFoundException the course not found exception
     */
    void deleteCourse(int courseId) throws CourseNotFoundException;

    /**
     * Update course into course catalog
     *
     * @param course the course
     */
    void updateCourse(Course course);

    /**
     * Gets course from a course Id
     *
     * @param courseId the course id
     * @return the course
     * @throws CourseNotFoundException the course not found exception
     */
    Course getCourse(int courseId) throws CourseNotFoundException;

    /**
     * Gets list of all the courses
     *
     * @return the courses list
     */
    List<Course> getCourses();
}
