package com.flipkart.service;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.*;
import com.flipkart.exception.DBOperationException;
import org.apache.log4j.Logger;

/**
 * The type Course enrollment service.
 */
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {
    private static final Logger logger = Logger.getLogger(CourseEnrollmentServiceImpl.class);
    private final StudentDAO studentDAO = new StudentDAOImpl();
    private final NotificationDAO notifyDao = new NotificationDAOImpl();
    private final ProfessorDAO professorDAO = new ProfessorDAOImpl();

    @Override
    public boolean registerCourse(int studentId, int courseId) {
        try {
            studentDAO.addCourse(studentId, courseId);

            Notification notification = new Notification();
            notification.setUserId(studentId);
            notification.setMessage("Pay your fees for course with courseId " + courseId);
            notifyDao.addNotification(notification);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteCourse(int studentId, int courseId) {
        try {
            studentDAO.removeCourse(studentId, courseId);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean assignGrade(int studentId, int courseId, String grade) {
        try {
            studentDAO.updateGrade(studentId, courseId, grade);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Student getStudent(int studentId) {
        Student student = null;
        try {
            student = studentDAO.getStudent(studentId);
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
        }

        return student;
    }

    @Override
    public boolean assignProfessor(int professorId, int courseId) {
        try {
            professorDAO.addCourse(professorId, courseId);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Professor getProfessor(int professorId) {
        Professor professor = null;
        try {
            professor = professorDAO.getProfessor(professorId);
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
        }

        return professor;
    }
}
