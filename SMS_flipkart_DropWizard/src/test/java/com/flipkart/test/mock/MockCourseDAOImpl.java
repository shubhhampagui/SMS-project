package com.flipkart.test.mock;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseDAO;
import com.flipkart.exception.CourseNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Mock course dao.
 */
public class MockCourseDAOImpl implements CourseDAO {
    private final List<Course> courses = new ArrayList<Course>();

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public void deleteCourse(int courseId) throws CourseNotFoundException {
        int indexToDelete = -1;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            throw new CourseNotFoundException();
        }

        courses.remove(indexToDelete);
    }

    @Override
    public void updateCourse(Course course) {
        boolean courseFound = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == course.getId()) {
                courses.set(i, course);
                break;
            }
        }
    }

    @Override
    public Course getCourse(int courseId) throws CourseNotFoundException {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }

        throw new CourseNotFoundException();
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Reset the courses list
     */
    public void reset() {
        courses.clear();
    }
}
