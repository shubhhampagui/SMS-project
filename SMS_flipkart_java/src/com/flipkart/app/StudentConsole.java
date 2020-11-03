package com.flipkart.app;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The type Student console.
 */
public class StudentConsole implements Console {

    private static final Logger logger = Logger.getLogger(StudentConsole.class);
    private Scanner scanner = null;

    /**
     * Instantiates a new Student console.
     *
     * @param scanner the scanner
     */
    public StudentConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(ConsoleContext context) {
        String text =
                "\nEnter operation : \n"
                        + "0. Exit\n"
                        + "1. Logout\n"
                        + "2. View courses\n"
                        + "3. Register course\n"
                        + "4. Drop course\n"
                        + "5. My Account\n"
                        + "6. View my courses\n"
                        + "7. Pay fees\n"
                        + "8. View notifications\n"
                        + "9. Calculate fee\n";

        logger.info(text);

        CourseOperations courseOps = new CourseOperations(scanner);
        UserOperations usrOps = new UserOperations(scanner);
        EnrollmentOperations enrOps = new EnrollmentOperations(scanner);

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 0:
                context.setConsole(null);
                return;
            case 1:
                usrOps.logout(context);
                return;
            case 2:
                courseOps.listCourses();
                break;
            case 3:
                enrOps.addRemoveCourse(true);
                break;
            case 4:
                enrOps.addRemoveCourse(false);
                break;
            case 5:
                usrOps.myAccount();
                break;
            case 6:
                enrOps.viewStudentCourses(true);
                break;
            case 7:
                enrOps.payFees();
                break;
            case 8:
                enrOps.viewNotifications();
                break;
            case 9:
                enrOps.calculateFees();
                break;
            default:
                throw new IllegalArgumentException("Please enter proper value...");
        }

        logger.info("continue...");
        scanner.nextLine();
    }
}
