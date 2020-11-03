package com.flipkart.bean;

/**
 * This class contains all the information of a course from course catalog
 */
public class Course {
    private int id;
    private String name;
    private String description;
    private int fees;

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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets fees.
     *
     * @return the fees
     */
    public int getFees() {
        return fees;
    }

    /**
     * Sets fees.
     *
     * @param fees the fees
     */
    public void setFees(int fees) {
        this.fees = fees;
    }
}