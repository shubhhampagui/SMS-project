package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Course dao.
 */
public class CourseDAOImpl implements CourseDAO {
    @Override
    public void addCourse(Course course) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.INSERT_COURSE_QUERY);
            fillCourseStatement(ps, course);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void deleteCourse(int courseId) throws CourseNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.DELETE_COURSE_QUERY);
            ps.setInt(1, courseId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CourseNotFoundException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void updateCourse(Course course) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.UPDATE_COURSE_QUERY);
            fillCourseStatement(ps, course);
            ps.setInt(4, course.getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public Course getCourse(int courseId) throws CourseNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course = null;

        try {
            ps = connection.prepareStatement(SQLQueries.GET_COURSE_QUERY);
            ps.setInt(1, courseId);
            ps.execute();

            rs = ps.getResultSet();
            if (rs.next()) {
                course = createCourse(rs);
            } else {
                throw new CourseNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return course;
    }

    @Override
    public List<Course> getCourses() {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courses = null;

        try {
            String sql = SQLQueries.LIST_COURSES_QUERY;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            courses = new ArrayList<Course>();
            while (rs.next()) {
                courses.add(createCourse(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return courses;
    }

    private Course createCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        course.setFees(rs.getInt("fees"));
        return course;
    }

    private void fillCourseStatement(PreparedStatement ps, Course course) throws SQLException {
        ps.setString(1, course.getName());
        ps.setString(2, course.getDescription());
        ps.setInt(3, course.getFees());
    }
}