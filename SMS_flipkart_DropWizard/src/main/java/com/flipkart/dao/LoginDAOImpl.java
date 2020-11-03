package com.flipkart.dao;

import com.flipkart.bean.Login;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.LoginNotFoundException;
import com.flipkart.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Login dao implementation
 */
public class LoginDAOImpl implements LoginDAO {
    @Override
    public void addLogin(Login login) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.INSERT_LOGIN_QUERY);
            fillLoginStatement(ps, login);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void deleteLogin(int loginId) throws LoginNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.DELETE_LOGIN_QUERY);
            ps.setInt(1, loginId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new LoginNotFoundException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void updateLogin(Login login) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.UPDATE_LOGIN_QUERY);
            fillLoginStatement(ps, login);
            ps.setInt(4, login.getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public Login getLogin(int loginId) throws LoginNotFoundException, DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Login login = null;

        try {
            ps = connection.prepareStatement(SQLQueries.GET_LOGIN_QUERY);
            ps.setInt(1, loginId);
            ps.execute();

            rs = ps.getResultSet();
            if (rs.next()) {
                login = createLogin(rs);
            } else {
                throw new LoginNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return login;
    }

    @Override
    public Login getLogin(String username, String password) throws LoginNotFoundException, DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Login login = null;

        try {
            ps = connection.prepareStatement(SQLQueries.VALIDATE_LOGIN_QUERY);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.execute();

            rs = ps.getResultSet();
            if (rs.next()) {
                login = createLogin(rs);
            } else {
                throw new LoginNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return login;
    }

    @Override
    public List<Login> getLogins() throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Login> logins = null;

        try {
            String sql = SQLQueries.LIST_LOGIN_QUERY;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            logins = new ArrayList<Login>();
            while (rs.next()) {
                logins.add(createLogin(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return logins;
    }

    private Login createLogin(ResultSet rs) throws SQLException {
        Login login = new Login();
        login.setId(rs.getInt("id"));
        login.setUsername(rs.getString("username"));
        login.setApproved(rs.getBoolean("approved"));
        login.setPassword(rs.getString("password"));
        return login;
    }

    private void fillLoginStatement(PreparedStatement ps, Login login) throws SQLException {
        ps.setString(1, login.getUsername());
        ps.setString(2, login.getPassword());
        ps.setBoolean(3, login.isApproved());
    }
}
