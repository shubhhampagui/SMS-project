package com.flipkart.service;

import com.flipkart.bean.Payment;

import java.util.List;

/**
 * The interface Payment service
 */
public interface PaymentService {
    /**
     * Adds new payment
     *
     * @param payment the payment
     * @return the boolean indicating the result of payment addition
     */
    boolean makePayment(Payment payment);

    /**
     * Gets payment from payment id
     *
     * @param paymentId the payment id
     * @return the payment
     */
    Payment getPayment(int paymentId);

    /**
     * Gets all the payments
     *
     * @param userId the user id
     * @return the payments list
     */
    List<Payment> getPayments(int userId);
}
