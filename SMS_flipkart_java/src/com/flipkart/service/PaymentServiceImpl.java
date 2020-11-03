package com.flipkart.service;

import com.flipkart.bean.Payment;
import com.flipkart.dao.PaymentDAO;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.PaymentNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Payment service.
 */
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);
    /**
     * The Payment dao.
     */
    PaymentDAO paymentDAO;

    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public Payment getPayment(int paymentId) {
        Payment payment = null;
        try {
            payment = paymentDAO.getPayment(paymentId);
        } catch (DBOperationException | PaymentNotFoundException ex) {
            logger.error(ex.getMessage());
        }

        return payment;
    }

    @Override
    public List<Payment> getPayments(int userId) {
        List<Payment> payments = null;
        try {
            payments = paymentDAO.getPayments(userId);
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
        }

        return payments;
    }

    @Override
    public boolean makePayment(Payment payment) {
        try {
            paymentDAO.addPayment(payment);
        } catch (DBOperationException ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }
}
