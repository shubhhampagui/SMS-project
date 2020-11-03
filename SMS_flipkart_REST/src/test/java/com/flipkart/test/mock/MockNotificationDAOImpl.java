package com.flipkart.test.mock;

import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDAO;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.NotificationNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Mock notification dao.
 */
public class MockNotificationDAOImpl implements NotificationDAO {
    private final List<Notification> notifications = new ArrayList<Notification>();

    @Override
    public void addNotification(Notification notification) throws DBOperationException {
        notifications.add(notification);
    }

    @Override
    public void deleteNotification(int notificationId) throws NotificationNotFoundException {
        int indexToDelete = -1;
        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).getId() == notificationId) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            throw new NotificationNotFoundException();
        }

        notifications.remove(indexToDelete);
    }

    @Override
    public void updateNotification(Notification notification) throws DBOperationException {
        boolean notificationFound = false;
        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).getId() == notification.getId()) {
                notifications.set(i, notification);
                break;
            }
        }
    }

    @Override
    public Notification getNotification(int notificationId) throws NotificationNotFoundException, DBOperationException {
        for (Notification notification : notifications) {
            if (notification.getId() == notificationId) {
                return notification;
            }
        }

        throw new NotificationNotFoundException();
    }

    @Override
    public List<Notification> getNotifications(int userId) throws DBOperationException {
        List<Notification> userNotifications = new ArrayList<Notification>();
        for (Notification notification : notifications) {
            if (userId == notification.getUserId()) {
                userNotifications.add(notification);
            }
        }

        return userNotifications;
    }

    /**
     * Reset the notifications list
     */
    public void reset() {
        notifications.clear();
    }
}
