package com.flipkart.app;

import com.flipkart.bean.Course;
import com.flipkart.service.CourseManagementService;
import com.flipkart.service.ServiceManager;
import com.flipkart.service.ServiceManager.SERVICE_TYPE;
import com.flipkart.service.UserManagementService;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 * The type Course operations.
 */
public class CourseOperations {
    private static final Logger logger = Logger.getLogger(CourseOperations.class);
    private final Scanner scanner;
    private final UserManagementService usrService;
    private final CourseManagementService courseService;

    /**
     * Instantiates a new Course operations.
     *
     * @param scanner the scanner
     */
    public CourseOperations(Scanner scanner) {
        this.scanner = scanner;
        usrService = (UserManagementService) ServiceManager.getService(SERVICE_TYPE.USER_MANAGEMENT);
        courseService = (CourseManagementService) ServiceManager.getService(SERVICE_TYPE.COURSE_MANAGEMENT);
    }

    /**
     * Add a new course.
     */
    public void addCourse() {
        logger.info("Add course");
        logger.info("Enter course name : ");
        String name = scanner.nextLine();
        logger.info("Enter course fees: ");
        int fees = Integer.parseInt(scanner.nextLine());
        logger.info("Enter course description : ");
        String description = scanner.nextLine();

        Course course = new Course();
        course.setName(name);
        course.setFees(fees);
        course.setDescription(description);

        boolean result = courseService.addCourse(course);
        if (!result) {
            logger.error("Course addition failed");
        }

        logger.info("Course " + name + " added");
    }

    /**
     * Delete a course.
     */
    public void deleteCourse() {
        logger.info("Delete course");
        logger.info("Enter course id: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        boolean result = courseService.deleteCourse(courseId);
        if (!result) {
            logger.error("Course deletion failed");
        }

        logger.info("Deleted course");
    }

    /**
     * List all the courses.
     */
    public void listCourses() {
        logger.info("List courses");

        List<Course> courses = courseService.getCourses();
        if (courses == null) {
            logger.error("Failed to get courses list");
            return;
        }

        String coursesOutFormat = "%1$-15s%2$-20s%3$-7s%4$-100s\n";
        Formatter formatter = new Formatter();
        formatter.format(coursesOutFormat, "course id", "course name", "fees", "description");
        formatter.format(coursesOutFormat, "", "", "", "");

        for (Course course : courses) {
            formatter.format(coursesOutFormat, course.getId(), course.getName(), course.getFees(), course.getDescription());
        }

        logger.info("\n" + formatter);
    }
}
