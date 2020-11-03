package com.flipkart.app;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The type Professor console.
 */
public class ProfessorConsole implements Console {

    private static final Logger logger = Logger.getLogger(ProfessorConsole.class);
    private Scanner scanner = null;

    /**
     * Instantiates a new Professor console.
     *
     * @param scanner the scanner
     */
    public ProfessorConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(ConsoleContext context) {
        String text = "\n"
                + "Enter operation : \n"
                + "0. Exit\n"
                + "1. Logout\n"
                + "2. View courses\n"
                + "3. Log grade\n"
                + "4. View Student Grades\n"
                + "5. My account\n"
                + "6. Sign up for course\n"
                + "7. View my courses\n"
                + "8. View users\n";

        logger.info(text);

        UserOperations usrOps = new UserOperations(scanner);
        CourseOperations courseOps = new CourseOperations(scanner);
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
                enrOps.logGrade();
                break;
            case 4:
                enrOps.viewStudentCourses(false);
                break;
            case 5:
                usrOps.myAccount();
                break;
            case 6:
                enrOps.assignProfessor(true);
                break;
            case 7:
                enrOps.viewProfessorCourses();
                break;
            case 8:
                usrOps.listUsers();
                break;
            default:
                throw new IllegalArgumentException("Please enter proper value...");
        }

        logger.info("continue...");
        scanner.nextLine();
    }
}
