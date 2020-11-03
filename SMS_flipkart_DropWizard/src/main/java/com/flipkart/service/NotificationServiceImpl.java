package com.flipkart.service;

import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDAO;
import com.flipkart.exception.DBOperationException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Notification service.
 */
public class NotificationServiceImpl implements NotificationService {
    private static final Logger logger = Logger.getLogger(NotificationServiceImpl.class);
    /**
     * The Notification dao.
     */
    NotificationDAO notificationDAO;

    /**
     * Instantiates a new Notification service.
     *
     * @param notificationDAO the notification dao
     */
    public NotificationServiceImpl(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    public boolean addNotification(Notification notification) {
        try {
            notificationDAO.addNotification(notification);
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Notification> getNotifications(int userId) {
        List<Notification> notifications = null;
        try {
            notifications = notificationDAO.getNotifications(userId);
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
        }

        return notifications;
    }
}
