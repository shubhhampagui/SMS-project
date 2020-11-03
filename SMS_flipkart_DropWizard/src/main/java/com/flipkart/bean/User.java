/**
 *
 */
package com.flipkart.bean;

import com.flipkart.enums.USER_TYPE;

/**
 * The class contains information of a user
 *
 * @author shubham
 */
public class User {
    private int id;
    private String name;
    private USER_TYPE type;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public USER_TYPE getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(USER_TYPE type) {
        this.type = type;
    }
}