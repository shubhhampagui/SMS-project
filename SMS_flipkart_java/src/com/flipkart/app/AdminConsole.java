package com.flipkart.app;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The type Admin console.
 */
public class AdminConsole implements Console {
    private static final Logger logger = Logger.getLogger(AdminConsole.class);
    private Scanner scanner = null;

    /**
     * Instantiates a new Admin console.
     *
     * @param scanner the scanner
     */
    public AdminConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(ConsoleContext context) {
        String text = "\n"
                + "Enter operation : \n"
                + "0. Exit\n"
                + "1. Logout\n"
                + "2. View courses\n"
                + "3. Add course\n"
                + "4. Remove course\n"
                + "5. View student courses\n"
                + "6. Register user\n"
                + "7. Delete user\n"
                + "8. Approve user\n"
                + "9. List users\n"
                + "10. Assign professor\n"
                + "11. My account";

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
                courseOps.addCourse();
                break;
            case 4:
                courseOps.deleteCourse();
                break;
            case 5:
                enrOps.viewStudentCourses(false);
                break;
            case 6:
                usrOps.registerUser(true);
                break;
            case 7:
                usrOps.deleteUser();
                break;
            case 8:
                usrOps.approveUser();
                break;
            case 9:
                usrOps.listUsers();
                break;
            case 10:
                enrOps.assignProfessor(false);
                break;
            case 12:
                usrOps.myAccount();
                break;
            default:
                throw new IllegalArgumentException("Please enter proper value...");
        }

        logger.info("continue...");
        scanner.nextLine();
    }
}
