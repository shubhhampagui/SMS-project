/**
 *
 */
package com.flipkart.app;

import com.flipkart.service.ServiceManager;
import com.flipkart.util.DBUtils;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The type SMS application.
 *
 * @author shubham
 */
public class SMSApplication {
    private static final Logger logger = Logger.getLogger(SMSApplication.class);
    private Scanner scanner = null;
    private ConsoleContext context = null;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SMSApplication app = new SMSApplication();
        if (!app.initialize()) {
            logger.error("App initialization failed");
        }

        app.run();
    }

    private boolean initialize() {
        scanner = new Scanner(System.in);
        context = new ConsoleContext(new DashboardConsole(scanner));

        if (!DBUtils.openConnection()) {
            return false;
        }

        return ServiceManager.initialize();
    }

    private void cleanUp() {
        ServiceManager.clean();
        DBUtils.closeConnection();
    }

    private void run() {
        logger.info("Welcome to Student Management System!");

        while (true) {
            try {
                if (!context.execute()) {
                    break;
                }
            } catch (Exception ex) {
                logger.error("Error occurred: " + ex.getMessage());
            }
        }

        logger.info("Exited");
        cleanUp();
    }
}