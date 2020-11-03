package com.flipkart.rest;

import com.flipkart.bean.Payment;
import com.flipkart.service.PaymentService;
import com.flipkart.service.ServiceManager;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The Payment controller. All the operations to create, get and list payments.
 */
@Path("/payment")
public class PaymentController {
    private static final Logger logger = Logger.getLogger(PaymentController.class);
    private final PaymentService service;

    /**
     * Instantiates a new Payment controller.
     */
    public PaymentController() {
        ServiceManager.initialize();
        service = (PaymentService) ServiceManager.getService(ServiceManager.SERVICE_TYPE.PAYMENT);
    }

    /**
     * Add a new payment
     *
     * @param payment the payment to be added
     * @return the http response containing message text and response code
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createPayment(Payment payment) {
        int responseCode;
        String message;
        boolean result = service.makePayment(payment);
        if (result) {
            message = "Successfully added the payment";
            logger.debug(message);
            responseCode = 201;
        } else {
            message = "Error occurred to add the payment";
            logger.error(message);
            responseCode = 400;
        }

        return Response.status(responseCode).entity(message).build();
    }

    /**
     * Gets a payment from a payment id
     *
     * @param paymentId the payment id of the required payment
     * @return the payment
     */
    @GET
    @Path("/get/{paymentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPayment(@PathParam("paymentId") int paymentId) {
        logger.debug("Getting the payment");
        return service.getPayment(paymentId);
    }

    /**
     * Gets all the payments of a particular user
     *
     * @param userId the user id of the user whose payments are required
     * @return the payments list
     */
    @GET
    @Path("/list/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> getPayments(@PathParam("userId") int userId) {
        logger.debug("Getting the list of all the payments");
        return service.getPayments(userId);
    }
}