package com.flipkart.service;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

/**
 * The interface for enrollment of courses by student and professor
 */
public interface CourseEnrollmentService {
    /**
     * Register course for student
     *
     * @param studentId the student id
     * @param courseId  the course id
     * @return the boolean indicating the result of course registration
     */
    boolean registerCourse(int studentId, int courseId);

    /**
     * Drop course for student
     *
     * @param studentId the student id
     * @param courseId  the course id
     * @return the boolean indicating the result of course drop
     */
    boolean deleteCourse(int studentId, int courseId);

    /**
     * Assign grade to student
     *
     * @param student  the student
     * @param courseId the course id
     * @param grade    the grade
     * @return the boolean indicating result of course assignment
     */
    boolean assignGrade(int student, int courseId, String grade);

    /**
     * Gets student information
     *
     * @param studentId the student id
     * @return the student
     */
    Student getStudent(int studentId);

    /**
     * Assign professor information
     *
     * @param professorId the professor id
     * @param courseId    the course id
     * @return the boolean indicates the result of course assigment
     */
    boolean assignProfessor(int professorId, int courseId);

    /**
     * Gets professor information
     *
     * @param professorId the professor id
     * @return the professor
     */
    Professor getProfessor(int professorId);
}
