package com.flipkart.bean;

import com.flipkart.enums.USER_TYPE;

/**
 * The class contains information required for registration of a user
 */
public class RegisterInfo {
    private User user;
    private Login login;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(Login login) {
        this.login = login;
    }
}
