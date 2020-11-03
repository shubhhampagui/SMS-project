package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.exception.LoginNotApprovedException;
import com.flipkart.exception.LoginNotFoundException;

/**
 * The interface Authentication service.
 * This interface is used for all the functionality related to authentication
 *
 * @author shubham
 */
public interface AuthenticationService {
    /**
     * Login.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the boolean indicated if user is authenticated
     * @throws LoginNotFoundException    the exception when username or password is incorrect
     * @throws LoginNotApprovedException the exception when user is not approved by admin
     */
    boolean login(String username, String password) throws LoginNotFoundException, LoginNotApprovedException;

    /**
     * Logs out the user
     */
    void logout();

    /**
     * Gets the logged in user
     *
     * @return the logged in user
     */
    User getLoggedInUser();

    /**
     * Admin will approve the user
     *
     * @param userId the user id
     * @return the boolean
     */
    boolean approveUser(int userId);
}
