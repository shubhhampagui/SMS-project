package com.flipkart.bean;

/**
 * The type Course data. This class contains student related information of a course.
 */
public class CourseData {
    /**
     * The Course id.
     */
    int courseId;
    /**
     * The Grade.
     */
    String grade;

    /**
     * Gets course id.
     *
     * @return the course id
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Sets course id.
     *
     * @param courseId the course id
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
}