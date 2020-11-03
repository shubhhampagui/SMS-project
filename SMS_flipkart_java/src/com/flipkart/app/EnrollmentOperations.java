package com.flipkart.app;

import com.flipkart.bean.*;
import com.flipkart.service.*;
import com.flipkart.service.ServiceManager.SERVICE_TYPE;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 * The type Enrollment operations.
 */
public class EnrollmentOperations {
    private static final Logger logger = Logger.getLogger(EnrollmentOperations.class);
    private final Scanner scanner;
    private final UserManagementService usrService;
    private final AuthenticationService authService;
    private final CourseEnrollmentService enrService;
    private final PaymentService paymentService;
    private final NotificationService notifyService;
    private final CourseManagementService courseService;

    /**
     * Instantiates a new Enrollment operations.
     *
     * @param scanner the scanner
     */
    public EnrollmentOperations(Scanner scanner) {
        this.scanner = scanner;
        usrService = (UserManagementService) ServiceManager.getService(SERVICE_TYPE.USER_MANAGEMENT);
        authService = (AuthenticationService) ServiceManager.getService(SERVICE_TYPE.AUTHENTICATION);
        enrService = (CourseEnrollmentService) ServiceManager.getService(SERVICE_TYPE.COURSE_ENROLMENT);
        paymentService = (PaymentService) ServiceManager.getService(SERVICE_TYPE.PAYMENT);
        notifyService = (NotificationService) ServiceManager.getService(SERVICE_TYPE.NOTIFICATION);
        courseService = (CourseManagementService) ServiceManager.getService(SERVICE_TYPE.COURSE_MANAGEMENT);
    }

    /**
     * Pay fees of the student
     */
    public void payFees() {
        logger.info("Pay fees");
        logger.info("Enter amount: ");
        int amount = Integer.parseInt(scanner.nextLine());

        User user = authService.getLoggedInUser();

        Payment payment = new Payment();
        payment.setUserId(user.getId());
        payment.setAmount(amount);

        boolean result = paymentService.makePayment(payment);
        if (!result) {
            logger.error("Payment failed");
        }

        logger.info("Payment succeed");
    }

    /**
     * Add remove course.
     *
     * @param add whether add or remove course
     */
    public void addRemoveCourse(boolean add) {
        logger.info("Course registration");
        logger.info("Enter course id: ");
        int courseId = Integer.parseInt(scanner.nextLine());
        User user = authService.getLoggedInUser();

        if (add) {
            boolean result = enrService.registerCourse(user.getId(), courseId);
            if (!result) {
                logger.error("Course registration failed");
            }

            logger.info("Registered the course");
        } else {
            boolean result = enrService.deleteCourse(user.getId(), courseId);
            if (!result) {
                logger.error("Failed to drop the course");
            }

            logger.info("Dropped the course");
        }
    }

    /**
     * Assign professor to course
     *
     * @param isProfessor  whether caller is professor
     */
    public void assignProfessor(boolean isProfessor) {
        logger.info("Add professor");
        logger.info("Enter course id: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        int professorId;
        if (!isProfessor) {
            logger.info("Enter professor id: ");
            professorId = Integer.parseInt(scanner.nextLine());
        } else {
            User user = authService.getLoggedInUser();
            professorId = user.getId();
        }

        boolean result = enrService.assignProfessor(professorId, courseId);
        if (!result) {
            logger.error("Professor assignment failed");
        }

        logger.info("Assigned professor to course with course id " + courseId);
    }

    /**
     * View notifications of the user
     */
    public void viewNotifications() {
        logger.info("View notifications");

        User user = authService.getLoggedInUser();
        List<Notification> notifications = notifyService.getNotifications(user.getId());

        String notificationsOutFormat = "%1$-20s%2$-200s\n";
        Formatter formatter = new Formatter();
        formatter.format(notificationsOutFormat, "date", "message");
        formatter.format(notificationsOutFormat, "", "");
        for (Notification notification : notifications) {

            formatter.format(notificationsOutFormat, notification.getDate(), notification.getMessage());
        }

        logger.info("\n" + formatter);
    }

    /**
     * View student courses.
     *
     * @param isStudent whether caller is student.
     */
    public void viewStudentCourses(boolean isStudent) {
        logger.info("View Student Courses");

        int studentId;
        if (!isStudent) {
            logger.info("Enter student id: ");
            studentId = Integer.parseInt(scanner.nextLine());
        } else {
            User user = authService.getLoggedInUser();
            studentId = user.getId();
        }

        Student student = enrService.getStudent(studentId);
        List<CourseData> courseDataList = student.getCourses();

        String coursesOutFormat = "%1$-15s%2$-20s%3$-5s\n";
        Formatter formatter = new Formatter();
        formatter.format(coursesOutFormat, "course id", "course name", "grade");
        formatter.format(coursesOutFormat, "", "", "");
        for (CourseData data : courseDataList) {
            Course course = courseService.getCourse(data.getCourseId());
            formatter.format(coursesOutFormat, course.getId(), course.getName(), data.getGrade());
        }

        logger.info("\n" + formatter);
    }

    /**
     * Calculate fees of the student courses
     */
    public void calculateFees() {
        logger.info("Fees for my courses");

        User user = authService.getLoggedInUser();
        Student student = enrService.getStudent(user.getId());
        List<CourseData> courseDataList = student.getCourses();

        int totalFee = 0;
        String coursesOutFormat = "%1$-15s%2$-20s%3$-10s\n";
        Formatter formatter = new Formatter();
        formatter.format(coursesOutFormat, "course id", "course name", "fees");
        formatter.format(coursesOutFormat, "", "", "");
        for (CourseData data : courseDataList) {
            Course course = courseService.getCourse(data.getCourseId());
            formatter.format(coursesOutFormat, course.getId(), course.getName(), course.getFees());
            totalFee += course.getFees();
        }

        logger.info("\n" + formatter + "Total fees: " + totalFee);
    }

    /**
     * Log grade of student course
     */
    public void logGrade() {
        logger.info("Logging grade");
        logger.info("Enter course id: ");
        int courseId = Integer.parseInt(scanner.nextLine());
        logger.info("Enter student id: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        logger.info("Enter grade: ");
        String grade = scanner.nextLine();

        boolean result = enrService.assignGrade(studentId, courseId, grade);
        if (!result) {
            logger.error("Failed to assign grade to student");
        }

        logger.info("Assigned grade to student");
    }

    /**
     * View courses assigned to professor.
     */
    public void viewProfessorCourses() {
        logger.info("View Professor Courses");

        User user = authService.getLoggedInUser();
        Professor professor = enrService.getProfessor(user.getId());
        List<Integer> courses = professor.getCourses();

        String coursesOutFormat = "%1$-6s%2$-30s\n";
        Formatter formatter = new Formatter();
        formatter.format(coursesOutFormat, "id", "name");
        formatter.format(coursesOutFormat, "", "");
        for (Integer courseId : courses) {
            Course course = courseService.getCourse(courseId);
            formatter.format(coursesOutFormat, course.getId(), course.getName());
        }

        logger.info("\n" + formatter);
    }
}
