package com.flipkart.dao;

import com.flipkart.bean.CourseData;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.DBOperationException;
import com.flipkart.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The type Student dao implementation
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public Student getStudent(int studentId) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;

        try {
            String sql = SQLQueries.GET_STUDENT_COURSES_QUERY;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.execute();

            rs = ps.getResultSet();
            ArrayList<CourseData> courseDataList = new ArrayList<CourseData>();
            while (rs.next()) {
                CourseData data = new CourseData();
                data.setCourseId(rs.getInt("courseId"));
                data.setGrade(rs.getString("grade"));
                courseDataList.add(data);
            }

            student = new Student();
            student.setUserId(studentId);
            student.setCourses(courseDataList);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return student;
    }

    @Override
    public void addCourse(int studentId, int courseId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLQueries.INSERT_STUDENT_COURSE_QUERY);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(statement, null);
        }
    }

    @Override
    public void removeCourse(int studentId, int courseId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLQueries.DELETE_STUDENT_COURSE_QUERY);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(statement, null);
        }
    }

    @Override
    public void updateGrade(int studentId, int courseId, String grade) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLQueries.UPDATE_STUDENT_GRADE_QUERY);
            statement.setString(1, grade);
            statement.setInt(2, courseId);
            statement.setInt(3, studentId);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(statement, null);
        }
    }
}
