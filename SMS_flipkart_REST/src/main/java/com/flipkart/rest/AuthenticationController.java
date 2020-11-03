package com.flipkart.rest;

import com.flipkart.bean.User;
import com.flipkart.exception.LoginNotApprovedException;
import com.flipkart.exception.LoginNotFoundException;
import com.flipkart.service.AuthenticationService;
import com.flipkart.service.ServiceManager;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The type Authentication controller. All the operations to login, logout and get the logged in user.
 */
@Path("/auth")
public class AuthenticationController {
    private static final Logger logger = Logger.getLogger(AuthenticationController.class);
    private final AuthenticationService service;

    /**
     * Instantiates a new Authentication controller.
     */
    public AuthenticationController() {
        ServiceManager.initialize();
        service = (AuthenticationService) ServiceManager.getService(ServiceManager.SERVICE_TYPE.AUTHENTICATION);
    }

    /**
     * Authenticate the user
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the http response containing message text and response code
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        int responseCode = 200;
        String message = "Successfully logged in";
        try {
            boolean result = service.login(username, password);
            if (!result) {
                message = "Error in logging in the user.";
                logger.error(message);
            }
        } catch (LoginNotApprovedException e) {
            responseCode = 401;
            message = "User is not approved";
            logger.error(message + " " + e.getMessage());
        } catch (LoginNotFoundException e) {
            responseCode = 400;
            message = "username or password is incorrect";
            logger.error(message + " " + e.getMessage());
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Logout the logged in user
     *
     * @return the http response containing message text and response code
     */
    @GET
    @Path("/logout")
    public Response logout() {
        logger.info("Logging out the user");
        service.logout();
        return Response.status(200).entity("Successfully logged out the user").build();
    }

    /**
     * Approve the user. This operation is done by Administrator
     *
     * @param userId the user id
     * @return the http response containing message text and response code
     */
    @GET
    @Path("/approve/{userId}")
    public Response approve(@PathParam("userId") int userId) {
        int responseCode;
        String message;
        boolean result = service.approveUser(userId);
        if (result) {
            message = "Successfully approved the user";
            logger.debug(message);
            responseCode = 200;
        } else {
            message = "Error in approving the user";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Gets the currently logged in user
     *
     * @return the logged in user
     */
    @GET
    @Path("/loggedin")
    @Produces(MediaType.APPLICATION_JSON)
    public User getLoggedInUser() {
        logger.debug("Getting the logged in user.");
        return service.getLoggedInUser();
    }
}