package com.flipkart.dao;

import com.flipkart.bean.Payment;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.PaymentNotFoundException;

import java.util.List;

/**
 * The interface Payment dao.
 */
public interface PaymentDAO {
    /**
     * Add new payment
     *
     * @param payment the payment
     * @throws DBOperationException the DB operation exception
     */
    void addPayment(Payment payment) throws DBOperationException;

    /**
     * Delete payment from payment id
     *
     * @param paymentId the payment id
     * @throws PaymentNotFoundException the payment not found exception
     */
    void deletePayment(int paymentId) throws PaymentNotFoundException;

    /**
     * Update payment information
     *
     * @param payment the updated payment
     * @throws DBOperationException the DB operation exception
     */
    void updatePayment(Payment payment) throws DBOperationException;

    /**
     * Gets payment from payment id
     *
     * @param paymentId the payment id
     * @return the payment
     * @throws PaymentNotFoundException the payment not found exception
     * @throws DBOperationException     the DB operation exception
     */
    Payment getPayment(int paymentId) throws PaymentNotFoundException, DBOperationException;

    /**
     * Gets payments for a particular user
     *
     * @param userId the user id
     * @return the payments list
     * @throws DBOperationException the DB operation exception
     */
    List<Payment> getPayments(int userId) throws DBOperationException;
}
