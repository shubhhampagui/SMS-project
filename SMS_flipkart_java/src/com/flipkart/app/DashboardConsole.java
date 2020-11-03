package com.flipkart.app;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The type Dashboard console.
 */
public class DashboardConsole implements Console {
    private static final Logger logger = Logger.getLogger(DashboardConsole.class);
    private Scanner scanner = null;

    /**
     * Instantiates a new Dashboard console.
     *
     * @param scanner the scanner
     */
    public DashboardConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(ConsoleContext context) {
        String outText = "\n"
                + "Enter operation: \n"
                + "1. Login\n"
                + "2. Sign up\n"
                + "0. Exit";

        logger.info(outText);

        UserOperations usrOps = new UserOperations(scanner);

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                usrOps.login(context);
                break;

            case 2:
                usrOps.registerUser(false);
                break;

            case 0:
                context.setConsole(null);
                break;

            default:
                throw new IllegalArgumentException("Please enter proper value...");
        }
    }
}
