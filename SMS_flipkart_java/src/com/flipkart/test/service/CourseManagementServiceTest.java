package com.flipkart.test.service;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.service.CourseManagementService;
import com.flipkart.service.CourseManagementServiceImpl;
import com.flipkart.test.mock.MockCourseDAOImpl;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * The type Course management service test.
 */
public class CourseManagementServiceTest {
    private static final Course[] courses = new Course[3];
    private static CourseManagementService courseService = null;
    private static MockCourseDAOImpl courseDAO = null;

    /**
     * Sets up all.
     */
    @BeforeClass
    public static void setUpAll() {
        courseDAO = new MockCourseDAOImpl();
        courseService = new CourseManagementServiceImpl(courseDAO);

        Course course = new Course();
        course.setId(1);
        course.setName("TestCourse1");
        course.setDescription("Test course 1 description");
        course.setFees(100);
        courses[0] = course;

        course = new Course();
        course.setId(2);
        course.setName("TestCourse2");
        course.setDescription("Test course 2 description");
        course.setFees(200);
        courses[1] = course;

        course = new Course();
        course.setId(3);
        course.setName("TestCourse3");
        course.setDescription("Test course 3 description");
        course.setFees(300);
        courses[2] = course;
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        courseDAO.reset();
    }

    /**
     * Gets course pass test.
     */
    @Test
    public void getCoursePassTest() {
        Course course1 = courses[0];
        courseDAO.addCourse(courses[0]);

        Course course = courseService.getCourse(course1.getId());
        assertNotNull(course);

        compareCourse(course, course1);
    }

    /**
     * Gets course fail test.
     */
    @Test
    public void getCourseFailTest() {
        try {
            courseDAO.getCourse(courses[0].getId());
            assertTrue(false);
        } catch (CourseNotFoundException e) {
        }
    }

    /**
     * Add course test.
     */
    @Test
    public void addCourseTest() {
        Course localCourse = courses[0];
        assertTrue(courseService.addCourse(localCourse));
        try {
            Course course = courseDAO.getCourse(localCourse.getId());
            assertNotNull(course);
            compareCourse(course, localCourse);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * Delete course test.
     */
    @Test
    public void deleteCourseTest() {
        courseDAO.addCourse(courses[0]);
        assertTrue(courseService.deleteCourse(courses[0].getId()));
    }

    /**
     * Update course test.
     */
    @Test
    public void updateCourseTest() {
        Course originalCourse = courses[0];
        courseDAO.addCourse(originalCourse);

        Course updatedCourse = courses[2];
        updatedCourse.setId(originalCourse.getId());

        try {
            courseService.updateCourse(updatedCourse);
            Course course = courseDAO.getCourse(originalCourse.getId());
            compareCourse(course, updatedCourse);
        } catch (CourseNotFoundException e) {
            assertTrue(false);
        }
    }

    /**
     * List courses test.
     */
    @Test
    public void listCourses() {
        for (Course course : courses) {
            courseDAO.addCourse(course);
        }

        List<Course> courseList = courseService.getCourses();
        assertNotNull(courseList);
    }

    private void compareCourse(Course course1, Course course2) {
        assertEquals(course1.getId(), course2.getId());
        assertEquals(course1.getName(), course2.getName());
        assertEquals(course1.getDescription(), course2.getDescription());
        assertEquals(course1.getFees(), course2.getFees());
    }
}
