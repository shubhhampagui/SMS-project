package com.flipkart.service;

import com.flipkart.bean.Login;
import com.flipkart.bean.User;
import com.flipkart.dao.LoginDAO;
import com.flipkart.dao.LoginDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.LoginNotApprovedException;
import com.flipkart.exception.LoginNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

/**
 * The type Authentication service.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
    private final UserDAO userDao = new UserDAOImpl();
    private final LoginDAO loginDAO = new LoginDAOImpl();
    private Login login = null;

    @Override
    public boolean login(String username, String password) throws LoginNotFoundException, LoginNotApprovedException {
        Login login = null;
        try {
            login = loginDAO.getLogin(username, password);
            if (login == null) {
                throw new LoginNotFoundException();
            }

            if (!login.isApproved()) {
                throw new LoginNotApprovedException();
            }
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
            return false;
        }

        this.login = login;
        return true;
    }

    @Override
    public void logout() {
        login = null;
    }

    @Override
    public User getLoggedInUser() {
        User user = null;
        try {
            if (login != null) {
                user = userDao.getUser(login.getId());
            }
        } catch (UserNotFoundException ex) {
            logger.error(ex.getMessage());
        } catch (DBOperationException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean approveUser(int userId) {
        Login login = null;
        try {
            login = loginDAO.getLogin(userId);
            if (login == null) {
                throw new LoginNotFoundException();
            }

            login.setApproved(true);
            loginDAO.updateLogin(login);
        } catch (DBOperationException | LoginNotFoundException ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }
}