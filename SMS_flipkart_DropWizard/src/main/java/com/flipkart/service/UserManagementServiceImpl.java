package com.flipkart.service;

import com.flipkart.bean.Login;
import com.flipkart.bean.User;
import com.flipkart.dao.LoginDAO;
import com.flipkart.dao.LoginDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.LoginNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type User management service.
 */
public class UserManagementServiceImpl implements UserManagementService {
    private static final Logger logger = Logger.getLogger(UserManagementServiceImpl.class);
    /**
     * The User dao.
     */
    UserDAO userDao = new UserDAOImpl();
    /**
     * The Login dao.
     */
    LoginDAO loginDAO = new LoginDAOImpl();

    public boolean registerUser(Login login, User user) {
        try {
            loginDAO.addLogin(login);
            userDao.addUser(user);
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteUser(int userId) {
        try {
            loginDAO.deleteLogin(userId);
        } catch (LoginNotFoundException ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    public User getUser(int userId) {
        User user = null;
        try {
            user = userDao.getUser(userId);
        } catch (UserNotFoundException | DBOperationException ex) {
            logger.error(ex.getMessage());
        }

        return user;
    }

    public boolean updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (DBOperationException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<User> getUsers() {
        List<User> users = null;
        try {
            users = userDao.getUsers();
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
        }

        return users;
    }
}
