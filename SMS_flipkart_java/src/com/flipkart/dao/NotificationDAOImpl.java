package com.flipkart.dao;

import com.flipkart.bean.Notification;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.NotificationNotFoundException;
import com.flipkart.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Notification dao implementation
 */
public class NotificationDAOImpl implements NotificationDAO {
    @Override
    public void addNotification(Notification notification) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.INSERT_NOTIFICATION_QUERY);
            fillNotificationStatement(ps, notification);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void deleteNotification(int notificationId) throws NotificationNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.DELETE_NOTIFICATION_QUERY);
            ps.setInt(1, notificationId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new NotificationNotFoundException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void updateNotification(Notification notification) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.UPDATE_NOTIFICATION_QUERY);
            fillNotificationStatement(ps, notification);
            ps.setDate(3, notification.getDate());
            ps.setInt(4, notification.getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public Notification getNotification(int notificationId) throws NotificationNotFoundException, DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Notification notification = null;

        try {
            ps = connection.prepareStatement(SQLQueries.GET_NOTIFICATION_QUERY);
            ps.setInt(1, notificationId);
            ps.execute();

            rs = ps.getResultSet();
            if (rs.next()) {
                notification = createNotification(rs);
            } else {
                throw new NotificationNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return notification;
    }

    @Override
    public List<Notification> getNotifications(int userId) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Notification> notifications = null;

        try {
            ps = connection.prepareStatement(SQLQueries.LIST_NOTIFICATIONS_QUERY);
            ps.setInt(1, userId);
            ps.execute();

            rs = ps.getResultSet();
            notifications = new ArrayList<Notification>();
            while (rs.next()) {
                notifications.add(createNotification(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return notifications;
    }

    private Notification createNotification(ResultSet rs) throws SQLException {
        Notification notification = new Notification();
        notification.setId(rs.getInt("id"));
        notification.setUserId(rs.getInt("userId"));
        notification.setMessage(rs.getString("message"));
        notification.setDate(rs.getDate("time"));
        return notification;
    }

    private void fillNotificationStatement(PreparedStatement ps, Notification notification) throws SQLException {
        ps.setInt(1, notification.getUserId());
        ps.setString(2, notification.getMessage());
    }
}
