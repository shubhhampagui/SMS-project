package com.flipkart.service;

import com.flipkart.dao.*;
import com.flipkart.util.DBUtils;
import org.apache.log4j.Logger;

/**
 * The type Service manager.
 */
public class ServiceManager {
    private static final Logger logger = Logger.getLogger(ServiceManager.class);
    private static boolean initialized = false;
    private static UserManagementService userManagementService = null;
    private static CourseManagementService courseManagementService = null;
    private static AuthenticationService authenticationService = null;
    private static CourseEnrollmentService courseEnrolmentService = null;
    private static PaymentService paymentService = null;
    private static NotificationService notificationService = null;

    /**
     * Initialize boolean.
     *
     * @return the boolean
     */
    public static boolean initialize() {
        if (initialized)
            return true;

        boolean result = DBUtils.openConnection();
        if (!result)
            return false;

        try {
            CourseDAO courseDAO = new CourseDAOImpl();
            NotificationDAO notificationDAO = new NotificationDAOImpl();
            PaymentDAO paymentDAO = new PaymentDAOImpl();

            userManagementService = new UserManagementServiceImpl();
            courseManagementService = new CourseManagementServiceImpl(courseDAO);
            authenticationService = new AuthenticationServiceImpl();
            courseEnrolmentService = new CourseEnrollmentServiceImpl();
            paymentService = new PaymentServiceImpl(paymentDAO);
            notificationService = new NotificationServiceImpl(notificationDAO);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

        initialized = true;
        return true;
    }

    /**
     * Gets service.
     *
     * @param type the type
     * @return the service
     */
    public static Object getService(SERVICE_TYPE type) {
        switch (type) {
            case USER_MANAGEMENT:
                return userManagementService;
            case COURSE_MANAGEMENT:
                return courseManagementService;
            case AUTHENTICATION:
                return authenticationService;
            case COURSE_ENROLMENT:
                return courseEnrolmentService;
            case PAYMENT:
                return paymentService;
            case NOTIFICATION:
                return notificationService;
            default:
                return null;
        }
    }

    /**
     * Clean.
     */
    public static void clean() {
        DBUtils.closeConnection();
        initialized = false;
    }

    /**
     * The enum Service type.
     */
    public enum SERVICE_TYPE {
        /**
         * User management service type.
         */
        USER_MANAGEMENT,
        /**
         * Course management service type.
         */
        COURSE_MANAGEMENT,
        /**
         * Course enrolment service type.
         */
        COURSE_ENROLMENT,
        /**
         * Authentication service type.
         */
        AUTHENTICATION,
        /**
         * Payment service type.
         */
        PAYMENT,
        /**
         * Notification service type.
         */
        NOTIFICATION
    }
}
