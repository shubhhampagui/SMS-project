/**
 *
 */
package com.flipkart.service;

import com.flipkart.bean.Login;
import com.flipkart.bean.User;

import java.util.List;

/**
 * The interface User management service.
 *
 * @author shubham
 */
public interface UserManagementService {
    /**
     * Register a new user
     *
     * @param login the login
     * @param user  the user
     * @return the boolean indicating the result of user registration
     */
    boolean registerUser(Login login, User user);

    /**
     * Delete a user from user id
     *
     * @param userId the user id
     * @return the boolean indicating the result of user deletion
     */
    boolean deleteUser(int userId);

    /**
     * Gets a user from user id
     *
     * @param userId the user id
     * @return the user
     */
    User getUser(int userId);

    /**
     * Update user information
     *
     * @param user the updated user
     * @return the boolean
     */
    boolean updateUser(User user);

    /**
     * Gets all the users list
     *
     * @return the users list
     */
    List<User> getUsers();
}
