package com.flipkart.test.mock;

import com.flipkart.bean.Payment;
import com.flipkart.dao.PaymentDAO;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.PaymentNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Mock payment dao.
 */
public class MockPaymentDAOImpl implements PaymentDAO {
    private final List<Payment> payments = new ArrayList<Payment>();

    @Override
    public void addPayment(Payment payment) throws DBOperationException {
        payments.add(payment);
    }

    @Override
    public void deletePayment(int paymentId) throws PaymentNotFoundException {
        int indexToDelete = -1;
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId() == paymentId) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            throw new PaymentNotFoundException();
        }

        payments.remove(indexToDelete);
    }

    @Override
    public void updatePayment(Payment payment) throws DBOperationException {
        boolean paymentFound = false;
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId() == payment.getId()) {
                payments.set(i, payment);
                break;
            }
        }
    }

    @Override
    public Payment getPayment(int paymentId) throws PaymentNotFoundException, DBOperationException {
        for (Payment payment : payments) {
            if (payment.getId() == paymentId) {
                return payment;
            }
        }

        throw new PaymentNotFoundException();
    }

    @Override
    public List<Payment> getPayments(int userId) throws DBOperationException {
        List<Payment> userPayments = new ArrayList<Payment>();
        for (Payment payment : payments) {
            if (userId == payment.getUserId()) {
                userPayments.add(payment);
            }
        }

        return userPayments;
    }

    /**
     * Reset the payments list
     */
    public void reset() {
        payments.clear();
    }
}
