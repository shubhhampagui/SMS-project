package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constant.SQLQueries;
import com.flipkart.enums.USER_TYPE;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User dao implementation
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.INSERT_USER_QUERY);
            fillUserStatement(ps, user);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void deleteUser(int userId) throws UserNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.DELETE_USER_QUERY);
            ps.setInt(1, userId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserNotFoundException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void updateUser(User user) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.UPDATE_USER_QUERY);
            fillUserStatement(ps, user);
            ps.setInt(3, user.getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public User getUser(int userId) throws UserNotFoundException, DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            ps = connection.prepareStatement(SQLQueries.GET_USER_QUERY);
            ps.setInt(1, userId);
            ps.execute();

            rs = ps.getResultSet();
            if (rs.next()) {
                user = createUser(rs);
            } else {
                throw new UserNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return user;
    }

    @Override
    public List<User> getUsers() throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = null;

        try {
            String sql = SQLQueries.LIST_USERS_QUERY;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            users = new ArrayList<User>();
            while (rs.next()) {
                users.add(createUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return users;
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setType(USER_TYPE.valueOf(rs.getString("role")));
        return user;
    }

    private void fillUserStatement(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getName());
        ps.setInt(2, user.getType().getValue());
    }
}
