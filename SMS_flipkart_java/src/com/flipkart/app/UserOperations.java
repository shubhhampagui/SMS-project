package com.flipkart.app;

import com.flipkart.bean.Login;
import com.flipkart.bean.User;
import com.flipkart.enums.USER_TYPE;
import com.flipkart.exception.LoginNotApprovedException;
import com.flipkart.exception.LoginNotFoundException;
import com.flipkart.service.AuthenticationService;
import com.flipkart.service.ServiceManager;
import com.flipkart.service.ServiceManager.SERVICE_TYPE;
import com.flipkart.service.UserManagementService;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 * The type User operations. Perform all the user related operations.
 */
public class UserOperations {
    private static final Logger logger = Logger.getLogger(UserOperations.class);
    private final Scanner scanner;
    private final UserManagementService usrService;
    private final AuthenticationService authService;

    /**
     * Instantiates a new User operations.
     *
     * @param scanner the scanner
     */
    public UserOperations(Scanner scanner) {
        this.scanner = scanner;
        usrService = (UserManagementService) ServiceManager.getService(SERVICE_TYPE.USER_MANAGEMENT);
        authService = (AuthenticationService) ServiceManager.getService(SERVICE_TYPE.AUTHENTICATION);
    }

    /**
     * Register a new user.
     *
     * @param approved the approved
     */
    public void registerUser(boolean approved) {
        logger.info("User registration");
        logger.info("Enter user type  1. Student   2. Professor  3. Admin");

        USER_TYPE userType;
        int type = Integer.parseInt(scanner.nextLine());
        switch (type) {
            case 1:
                userType = USER_TYPE.STUDENT;
                break;

            case 2:
                userType = USER_TYPE.PROFESSOR;
                break;

            case 3:
                userType = USER_TYPE.ADMIN;
                break;

            default:
                throw new IllegalArgumentException("Please enter proper value...");
        }

        logger.info("Enter username : ");
        String username = scanner.nextLine();
        logger.info("Enter password: ");
        String password = scanner.nextLine();
        logger.info("Enter name : ");
        String name = scanner.nextLine();

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);
        login.setApproved(approved);

        User user = new User();
        user.setName(name);
        user.setType(userType);

        boolean result = usrService.registerUser(login, user);
        if (!result) {
            logger.error("User registration failed");
        }

        logger.info("User " + name + " is registered");
    }

    /**
     * Delete a user.
     */
    public void deleteUser() {
        logger.info("Delete user");
        logger.info("Enter user id: ");
        int userId = Integer.parseInt(scanner.nextLine());


        boolean result = usrService.deleteUser(userId);
        if (!result) {
            logger.error("User deletion failed");
        }

        logger.info("Deleted user");
    }

    /**
     * List all the users.
     */
    public void listUsers() {
        logger.info("List users");

        List<User> users = usrService.getUsers();
        if (users == null) {
            logger.error("Failed to get users list");
            return;
        }

        String usersOutFormat = "%1$-10s%2$-20s%3$-15s\n";
        Formatter formatter = new Formatter();
        formatter.format(usersOutFormat, "id", "name", "role");
        formatter.format(usersOutFormat, "", "", "");
        for (User user : users) {

            formatter.format(usersOutFormat, user.getId(), user.getName(), user.getType());
        }

        logger.info("\n" + formatter);
    }

    /**
     * Approve a user.
     */
    public void approveUser() {
        logger.info("Approve user");
        logger.info("Enter user id: ");
        int userId = Integer.parseInt(scanner.nextLine());

        boolean result = authService.approveUser(userId);
        if (!result) {
            logger.error("User approval failed");
            return;
        }

        logger.info("Approved user");
    }

    /**
     * View my account
     */
    public void myAccount() {
        logger.info("My account");

        User user = authService.getLoggedInUser();

        String outText = "\nid: " + user.getId()
                + "\nname: " + user.getName()
                + "\nrole: " + user.getType();
        logger.info(outText);
    }

    /**
     * Login.
     *
     * @param context the context
     */
    public void login(ConsoleContext context) {
        logger.info("Login");
        logger.info("Enter username: ");
        String name = scanner.nextLine();
        logger.info("Enter password: ");
        String password = scanner.nextLine();

        try {
            boolean result = authService.login(name, password);
            if (!result) {
                logger.error("Log in failed");
                return;
            }
        } catch (LoginNotApprovedException | LoginNotFoundException ex) {
            logger.error(ex.getMessage());
        }

        User user = authService.getLoggedInUser();
        logger.info("Welcome " + user.getName());

        switch (user.getType()) {
            case ADMIN:
                context.setConsole(new AdminConsole(scanner));
                break;
            case STUDENT:
                context.setConsole(new StudentConsole(scanner));
                break;
            case PROFESSOR:
                context.setConsole(new ProfessorConsole(scanner));
                break;
        }
    }

    /**
     * Logout.
     *
     * @param context the context
     */
    public void logout(ConsoleContext context) {
        logger.info("logged out");
        authService.logout();
        context.setConsole(new DashboardConsole(scanner));
    }
}
