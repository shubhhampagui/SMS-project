package com.flipkart.dao;

import com.flipkart.bean.Notification;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.NotificationNotFoundException;

import java.util.List;

/**
 * The interface Notification dao.
 */
public interface NotificationDAO {
    /**
     * Add new notification
     *
     * @param notification the notification
     * @throws DBOperationException the DB operation exception
     */
    void addNotification(Notification notification) throws DBOperationException;

    /**
     * Delete notification from notification id
     *
     * @param notificationId the notification id
     * @throws NotificationNotFoundException the notification not found exception
     */
    void deleteNotification(int notificationId) throws NotificationNotFoundException;

    /**
     * Update notification information
     *
     * @param notification the updated notification
     * @throws DBOperationException the DB operation exception
     */
    void updateNotification(Notification notification) throws DBOperationException;

    /**
     * Gets notification from notification id
     *
     * @param notificationId the notification id
     * @return the notification
     * @throws NotificationNotFoundException the notification not found exception
     * @throws DBOperationException          the DB operation exception
     */
    Notification getNotification(int notificationId) throws NotificationNotFoundException, DBOperationException;

    /**
     * Gets all the notifications of a given user
     *
     * @param userId the user id
     * @return the notifications list
     * @throws DBOperationException the DB operation exception
     */
    List<Notification> getNotifications(int userId) throws DBOperationException;
}
