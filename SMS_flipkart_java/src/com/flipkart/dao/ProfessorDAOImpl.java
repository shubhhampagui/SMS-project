package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.DBOperationException;
import com.flipkart.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The type Professor dao implementation
 */
public class ProfessorDAOImpl implements ProfessorDAO {
    @Override
    public Professor getProfessor(int professorId) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Professor professor = null;

        try {
            String sql = SQLQueries.GET_PROFESSOR_COURSES_QUERY;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, professorId);
            ps.execute();

            rs = ps.getResultSet();
            ArrayList<Integer> coursesList = new ArrayList<Integer>();
            while (rs.next()) {
                coursesList.add(rs.getInt("courseId"));
            }

            professor = new Professor();
            professor.setUserId(professorId);
            professor.setCourses(coursesList);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return professor;
    }

    @Override
    public void addCourse(int professorId, int courseId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLQueries.INSERT_PROFESSOR_COURSE_QUERY);
            statement.setInt(1, professorId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(statement, null);
        }
    }

    @Override
    public void removeCourse(int professorId, int courseId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLQueries.DELETE_PROFESSOR_COURSE_QUERY);
            statement.setInt(1, professorId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(statement, null);
        }
    }
}
