/**
 *
 */
package com.flipkart.service;

import com.flipkart.bean.Notification;

import java.util.List;

/**
 * The interface Notification service
 *
 * @author shubham
 */
public interface NotificationService {
    /**
     * Add new notification
     *
     * @param notification the notification
     * @return the boolean indicates the result of addition of new notificatino
     */
    boolean addNotification(Notification notification);

    /**
     * Gets all the notifications of the given user
     *
     * @param userId the user id
     * @return the notifications list
     */
    List<Notification> getNotifications(int userId);
}
