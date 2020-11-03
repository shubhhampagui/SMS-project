package com.flipkart.rest;

import com.flipkart.bean.RegisterInfo;
import com.flipkart.bean.User;
import com.flipkart.service.ServiceManager;
import com.flipkart.service.UserManagementService;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type User management controller. All the operations required to create, get, list, delete and update users.
 */
@Path("/user")
public class UserManagementController {
    private static final Logger logger = Logger.getLogger(UserManagementController.class);
    private final UserManagementService service;

    /**
     * Instantiates a new User management controller.
     */
    public UserManagementController() {
        ServiceManager.initialize();
        service = (UserManagementService) ServiceManager.getService(ServiceManager.SERVICE_TYPE.USER_MANAGEMENT);
    }

    /**
     * Add a new user
     *
     * @param registerInfo containing the registration information of the user
     * @return the http response containing message text and response code
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(RegisterInfo registerInfo) {
        int responseCode;
        String message;
        boolean result = service.registerUser(registerInfo.getLogin(), registerInfo.getUser());
        if (result) {
            message = "Successfully added the user";
            logger.debug(message);
            responseCode = 201;
        } else {
            message = "Error occurred to create the user";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Gets a user
     *
     * @param userId the user id of the required user
     * @return the user
     */
    @GET
    @Path("/get/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") int userId) {
        logger.debug("Getting the required user");
        return service.getUser(userId);
    }

    /**
     * Gets the list of all the users
     *
     * @return the user list. List of all the users
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        logger.debug("Getting the list of all the users");
        return service.getUsers();
    }

    /**
     * Delete user response
     *
     * @param userId the user id of the user to be deleted
     * @return the http response containing message text and response code
     */
    @DELETE
    @Path("/delete/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(@PathParam("userId") int userId) {
        int responseCode;
        String message;
        boolean result = service.deleteUser(userId);
        if (result) {
            message = "Successfully deleted the user";
            logger.debug(message);
            responseCode = 200;
        } else {
            message = "Error occurred to delete the user";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Updated information of the user
     *
     * @param user the user containing the updated information
     * @return the updated user
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        boolean result = service.updateUser(user);
        if (result) {
            logger.debug("Successfully updated the user");
        } else {
            logger.error("Error occurred to update the user");
        }

        return service.getUser(user.getId());
    }
}