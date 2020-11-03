package com.flipkart.rest;

import com.flipkart.bean.Notification;
import com.flipkart.service.NotificationService;
import com.flipkart.service.ServiceManager;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The Notification controller. All the operations to create and list notifications for a particular user
 */
@Path("/notification")
public class NotificationController {
    private static final Logger logger = Logger.getLogger(NotificationController.class);
    private final NotificationService service;

    /**
     * Instantiates a new Notification controller.
     */
    public NotificationController() {
        ServiceManager.initialize();
        service = (NotificationService) ServiceManager.getService(ServiceManager.SERVICE_TYPE.NOTIFICATION);
    }

    /**
     * Add a new notification to be sent to given user
     *
     * @param notification the notification to be added
     * @return the http response containing message text and response code
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createNotification(Notification notification) {
        int responseCode;
        String message;
        boolean result = service.addNotification(notification);
        if (result) {
            message = "Successfully added the notification";
            logger.debug(message);
            responseCode = 201;
        } else {
            message = "Error to add the notification";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Gets all the notifications of a particular user
     *
     * @param userId the user id of the user whose notifications are required
     * @return the notifications list
     */
    @GET
    @Path("/list/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notification> getNotification(@PathParam("userId") int userId) {
        logger.debug("Getting the list of all the notifications");
        return service.getNotifications(userId);
    }
}