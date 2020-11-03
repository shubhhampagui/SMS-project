package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseDAO;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Course management service.
 */
public class CourseManagementServiceImpl implements CourseManagementService {
    private static final Logger logger = Logger.getLogger(CourseManagementServiceImpl.class);
    private CourseDAO courseDAO = null;

    /**
     * Instantiates a new Course management service.
     *
     * @param courseDAO the course dao
     */
    public CourseManagementServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public boolean addCourse(Course course) {
        courseDAO.addCourse(course);
        return true;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        try {
            courseDAO.deleteCourse(courseId);
        } catch (CourseNotFoundException ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean updateCourse(Course course) {
        courseDAO.updateCourse(course);
        return true;
    }

    @Override
    public Course getCourse(int courseId) {
        Course course = null;
        try {
            course = courseDAO.getCourse(courseId);
        } catch (CourseNotFoundException ex) {
            logger.error(ex.getMessage());
        }

        return course;
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = courseDAO.getCourses();
        return courses;
    }
}
