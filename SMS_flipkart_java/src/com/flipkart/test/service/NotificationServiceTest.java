package com.flipkart.test.service;

import com.flipkart.bean.Notification;
import com.flipkart.service.NotificationService;
import com.flipkart.service.NotificationServiceImpl;
import com.flipkart.test.mock.MockNotificationDAOImpl;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The type Notification service test.
 */
public class NotificationServiceTest {
    private static final Notification[] notifications = new Notification[3];
    private static NotificationService notificationService = null;
    private static MockNotificationDAOImpl notificationDAO = null;

    /**
     * Sets up all.
     */
    @BeforeClass
    public static void setUpAll() {
        notificationDAO = new MockNotificationDAOImpl();
        notificationService = new NotificationServiceImpl(notificationDAO);

        Notification notification = new Notification();
        notification.setId(1);
        notification.setUserId(1);
        notification.setMessage("Notification 1 message");
        notification.setDate(new Date(System.currentTimeMillis()));
        notifications[0] = notification;

        notification = new Notification();
        notification.setId(2);
        notification.setUserId(1);
        notification.setMessage("Notification 2 message");
        notification.setDate(new Date(System.currentTimeMillis()));
        notifications[1] = notification;

        notification = new Notification();
        notification.setId(3);
        notification.setUserId(2);
        notification.setMessage("Notification 3 message");
        notification.setDate(new Date(System.currentTimeMillis()));
        notifications[2] = notification;
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        notificationDAO.reset();
    }

    /**
     * Add notification test.
     */
    @Test
    public void addNotificationTest() {
        Notification localNotification = notifications[0];
        assertTrue(notificationService.addNotification(localNotification));
        try {
            Notification notification = notificationDAO.getNotification(localNotification.getId());
            assertNotNull(notification);
            compareNotification(notification, localNotification);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * List notifications test
     */
    @Test
    public void listNotifications() {
        for (Notification notification : notifications) {
            notificationService.addNotification(notification);
        }

        List<Notification> notificationList = notificationService.getNotifications(1);
        assertNotNull(notificationList);
        assertEquals(2, notificationList.size());
    }

    private void compareNotification(Notification notification1, Notification notification2) {
        assertEquals(notification1.getId(), notification2.getId());
        assertEquals(notification1.getDate(), notification2.getDate());
        assertEquals(notification1.getMessage(), notification2.getMessage());
        assertEquals(notification1.getUserId(), notification2.getUserId());
    }
}
