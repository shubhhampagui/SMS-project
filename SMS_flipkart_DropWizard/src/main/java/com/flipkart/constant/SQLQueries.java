package com.flipkart.constant;

/**
 * The type SQL queries.
 */
public class SQLQueries {
    /**
     * The query to insert the user
     */
    public static final String INSERT_USER_QUERY = "INSERT INTO users (id, name, role) VALUES (LAST_INSERT_ID(), ?, ?)";
    /**
     * The query to delete the user
     */
    public static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    /**
     * The query to update the user
     */
    public static final String UPDATE_USER_QUERY = "UPDATE users SET name = ?, role = ? WHERE id = ?";
    /**
     * The query to get the user
     */
    public static final String GET_USER_QUERY = "SELECT users.id AS id, users.name AS name, roles.name AS role FROM users LEFT JOIN roles ON roles.id = users.role where users.id = ?";
    /**
     * The query to list all the users
     */
    public static final String LIST_USERS_QUERY = "SELECT users.id AS id, users.name AS name, roles.name AS role FROM users LEFT JOIN roles ON roles.id = users.role";

    /**
     * The query to insert course
     */
    public static final String INSERT_COURSE_QUERY = "INSERT INTO courses (name, description, fees) VALUES (?, ?, ?)";
    /**
     * The query to delete the course
     */
    public static final String DELETE_COURSE_QUERY = "DELETE FROM courses WHERE id = ?";
    /**
     * The query to update the course
     */
    public static final String UPDATE_COURSE_QUERY = "UPDATE courses SET name = ?, description = ?, fees = ? WHERE id = ?";
    /**
     * The query to get the course
     */
    public static final String GET_COURSE_QUERY = "SELECT id, name, description, fees FROM courses WHERE id = ?";
    /**
     * The query to list all the courses
     */
    public static final String LIST_COURSES_QUERY = "SELECT id, name, description, fees FROM courses";

    /**
     * The query to authenticate user
     */
    public static final String INSERT_LOGIN_QUERY = "INSERT INTO logins (username, password, approved) VALUES (?, ?, ?)";
    /**
     * The query to delete login data
     */
    public static final String DELETE_LOGIN_QUERY = "DELETE FROM logins WHERE id = ?";
    /**
     * The query to update login data
     */
    public static final String UPDATE_LOGIN_QUERY = "UPDATE logins SET username = ?, password = ?, approved = ? WHERE id = ?";
    /**
     * The query to get login data
     */
    public static final String GET_LOGIN_QUERY = "SELECT id, username, password, approved FROM logins WHERE id = ?";
    /**
     * The query to validate login query
     */
    public static final String VALIDATE_LOGIN_QUERY = "SELECT id, username, password, approved FROM logins WHERE username = ? AND password = ?";
    /**
     * The query to list all the logins data
     */
    public static final String LIST_LOGIN_QUERY = "SELECT id, username, password, approved FROM logins";

    /**
     * The query to insert payment
     */
    public static final String INSERT_PAYMENT_QUERY = "INSERT INTO payments (userId, amount, time) VALUES (?, ?, NOW())";
    /**
     * The query to delete payment
     */
    public static final String DELETE_PAYMENT_QUERY = "DELETE FROM payments WHERE id = ?";
    /**
     * The query to update the payment
     */
    public static final String UPDATE_PAYMENT_QUERY = "UPDATE payments SET userId = ?, amount = ?, time = ? WHERE id = ?";
    /**
     * The query to get the payment
     */
    public static final String GET_PAYMENT_QUERY = "SELECT id, userId, amount, time FROM payments WHERE id = ?";
    /**
     * The query to list all the payments
     */
    public static final String LIST_PAYMENTS_QUERY = "SELECT id, userId, amount, time FROM payments WHERE userId = ?";

    /**
     * The query to insert notification
     */
    public static final String INSERT_NOTIFICATION_QUERY = "INSERT INTO notifications (userId, message, time) VALUES (?, ?, NOW())";
    /**
     * The query to delete the notification
     */
    public static final String DELETE_NOTIFICATION_QUERY = "DELETE FROM notifications WHERE id = ?";
    /**
     * The query to update the notification
     */
    public static final String UPDATE_NOTIFICATION_QUERY = "UPDATE notifications SET userId = ?, message = ?, time = ? WHERE id = ?";
    /**
     * The query to get the notification
     */
    public static final String GET_NOTIFICATION_QUERY = "SELECT id, userId, message, time FROM notifications WHERE id = ?";
    /**
     * The query to list all the notifications
     */
    public static final String LIST_NOTIFICATIONS_QUERY = "SELECT id, userId, message, time FROM notifications WHERE userId = ? ORDER BY time DESC";

    /**
     * The query to register a course for student
     */
    public static final String INSERT_STUDENT_COURSE_QUERY = "INSERT INTO student_course (studentId, courseId) VALUES (?, ?)";
    /**
     * The query to drop a course for student
     */
    public static final String DELETE_STUDENT_COURSE_QUERY = "DELETE FROM student_course WHERE studentId = ? AND courseId = ?";
    /**
     * The query to get all student related data
     */
    public static final String GET_STUDENT_COURSES_QUERY = "SELECT * FROM student_course WHERE studentId = ?";
    /**
     * The query to update grade of a student
     */
    public static final String UPDATE_STUDENT_GRADE_QUERY = "UPDATE student_course SET grade = ? WHERE courseId = ? AND studentId = ?";

    /**
     * The query to assign a course to professor
     */
    public static final String INSERT_PROFESSOR_COURSE_QUERY = "INSERT INTO professor_course (professorId, courseId) VALUES (?, ?)";
    /**
     * The query to delete course for a professor
     */
    public static final String DELETE_PROFESSOR_COURSE_QUERY = "DELETE FROM professor_course WHERE professorId = ? AND courseId = ?";
    /**
     * The query to get all the courses of a professor
     */
    public static final String GET_PROFESSOR_COURSES_QUERY = "SELECT * FROM professor_course WHERE professorId = ?";
}
