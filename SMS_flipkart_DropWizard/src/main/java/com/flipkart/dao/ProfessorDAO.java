package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.exception.DBOperationException;

/**
 * The interface Professor dao.
 */
public interface ProfessorDAO {
    /**
     * Gets professor information
     *
     * @param professorId the professor id
     * @return the professor
     * @throws DBOperationException the DB operation exception
     */
    Professor getProfessor(int professorId) throws DBOperationException;

    /**
     * Add course to a professor
     *
     * @param professor the professor
     * @param courseId  the course id
     */
    void addCourse(int professor, int courseId);

    /**
     * Remove course for a professor
     *
     * @param professorId the professor id
     * @param courseId    the course id
     */
    void removeCourse(int professorId, int courseId);
}
