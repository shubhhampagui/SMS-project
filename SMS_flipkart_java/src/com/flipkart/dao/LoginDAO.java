package com.flipkart.dao;

import com.flipkart.bean.Login;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.LoginNotFoundException;

import java.util.List;

/**
 * The interface Login dao.
 */
public interface LoginDAO {
    /**
     * Add a new login
     *
     * @param login the login
     * @throws DBOperationException the DB operation exception
     */
    void addLogin(Login login) throws DBOperationException;

    /**
     * Delete the login from login id
     *
     * @param loginId the login id
     * @throws LoginNotFoundException the login not found exception
     */
    void deleteLogin(int loginId) throws LoginNotFoundException;

    /**
     * Update the login information
     *
     * @param login the updated login information
     * @throws DBOperationException the DB operation exception
     */
    void updateLogin(Login login) throws DBOperationException;

    /**
     * Gets login from login id
     *
     * @param loginId the login id
     * @return the login
     * @throws LoginNotFoundException the login not found exception
     * @throws DBOperationException   the DB operation exception
     */
    Login getLogin(int loginId) throws LoginNotFoundException, DBOperationException;

    /**
     * Gets login from username and password
     *
     * @param username the username
     * @param password the password
     * @return the login
     * @throws LoginNotFoundException the login not found exception
     * @throws DBOperationException   the DB operation exception
     */
    Login getLogin(String username, String password) throws LoginNotFoundException, DBOperationException;

    /**
     * Gets all the logins
     *
     * @return the logins list
     * @throws DBOperationException the DB operation exception
     */
    List<Login> getLogins() throws DBOperationException;
}
