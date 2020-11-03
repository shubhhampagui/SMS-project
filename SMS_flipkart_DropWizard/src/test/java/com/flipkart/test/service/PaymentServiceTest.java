package com.flipkart.test.service;

import com.flipkart.bean.Payment;
import com.flipkart.bean.Payment;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.PaymentNotFoundException;
import com.flipkart.service.PaymentService;
import com.flipkart.service.PaymentServiceImpl;
import com.flipkart.test.mock.MockPaymentDAOImpl;
import org.junit.*;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The type Payment service test.
 */
public class PaymentServiceTest {
    private static final Payment[] payments = new Payment[3];
    private static PaymentService paymentService = null;
    private static MockPaymentDAOImpl paymentDAO = null;

    /**
     * Sets up all.
     */
    @BeforeClass
    public static void setUpAll() {
        paymentDAO = new MockPaymentDAOImpl();
        paymentService = new PaymentServiceImpl(paymentDAO);

        Payment payment = new Payment();
        payment.setId(1);
        payment.setUserId(1);
        payment.setAmount(100);
        payment.setDate(new Date(System.currentTimeMillis()));
        payments[0] = payment;

        payment = new Payment();
        payment.setId(2);
        payment.setUserId(1);
        payment.setAmount(200);
        payment.setDate(new Date(System.currentTimeMillis()));
        payments[1] = payment;

        payment = new Payment();
        payment.setId(3);
        payment.setUserId(2);
        payment.setAmount(300);
        payment.setDate(new Date(System.currentTimeMillis()));
        payments[2] = payment;
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        paymentDAO.reset();
    }

    /**
     * Add payment test.
     */
    @Test
    public void addPaymentTest() {
        Payment localPayment = payments[0];
        assertTrue(paymentService.makePayment(localPayment));
        try {
            Payment payment = paymentDAO.getPayment(localPayment.getId());
            assertNotNull(payment);
            comparePayment(payment, localPayment);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * Gets payment pass test.
     */
    @Test
    public void getPaymentPassTest() {
        Payment payment1 = payments[0];
        try {
            paymentDAO.addPayment(payments[0]);
        } catch (DBOperationException e) {
            assertTrue(false);
        }

        Payment payment = paymentService.getPayment(payment1.getId());
        assertNotNull(payment);

        comparePayment(payment, payment1);
    }

    /**
     * Gets payment fail test.
     */
    @Test
    public void getPaymentFailTest() {
        try {
            paymentDAO.getPayment(payments[0].getId());
            assertTrue(false);
        } catch (PaymentNotFoundException e) {
        } catch (DBOperationException e) {
            assert(false);
        }
    }

    /**
     * List payments test
     */
    @Test
    public void listPayments() {
        for (Payment payment : payments) {
            paymentService.makePayment(payment);
        }

        List<Payment> paymentList = paymentService.getPayments(1);
        assertNotNull(paymentList);
        assertEquals(2, paymentList.size());
    }

    private void comparePayment(Payment payment1, Payment payment2) {
        assertEquals(payment1.getId(), payment2.getId());
        assertEquals(payment1.getDate(), payment2.getDate());
        assertEquals(payment1.getAmount(), payment2.getAmount());
        assertEquals(payment1.getUserId(), payment2.getUserId());
    }
}
