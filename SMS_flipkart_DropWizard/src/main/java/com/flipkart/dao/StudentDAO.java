package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.exception.DBOperationException;

/**
 * The interface Student dao.
 */
public interface StudentDAO {
    /**
     * Gets student information
     *
     * @param studentId the student id
     * @return the student
     * @throws DBOperationException the db operation exception
     */
    Student getStudent(int studentId) throws DBOperationException;

    /**
     * Add course for a student
     *
     * @param studentId the student id
     * @param courseId  the course id
     */
    void addCourse(int studentId, int courseId);

    /**
     * Remove course for a student
     *
     * @param studentId the student id
     * @param courseId  the course id
     */
    void removeCourse(int studentId, int courseId);

    /**
     * Assign grade to a student
     *
     * @param studentId the student id
     * @param courseId  the course id
     * @param grade     the grade
     */
    void updateGrade(int studentId, int courseId, String grade);
}
