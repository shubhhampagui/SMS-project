package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.UserNotFoundException;

import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDAO {
    /**
     * Add a new user
     *
     * @param user the user
     * @throws DBOperationException the DB operation exception
     */
    void addUser(User user) throws DBOperationException;

    /**
     * Delete user from user id
     *
     * @param userId the user id
     * @throws UserNotFoundException the user not found exception
     */
    void deleteUser(int userId) throws UserNotFoundException;

    /**
     * Update user information
     *
     * @param user the updated user
     * @throws DBOperationException the DB operation exception
     */
    void updateUser(User user) throws DBOperationException;

    /**
     * Gets user from user id
     *
     * @param userId the user id
     * @return the user
     * @throws UserNotFoundException the user not found exception
     * @throws DBOperationException  the DB operation exception
     */
    User getUser(int userId) throws UserNotFoundException, DBOperationException;

    /**
     * Gets all the users
     *
     * @return the users list
     * @throws DBOperationException the DB operation exception
     */
    List<User> getUsers() throws DBOperationException;
}
