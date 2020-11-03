package com.flipkart.dao;

import com.flipkart.bean.Payment;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.DBOperationException;
import com.flipkart.exception.PaymentNotFoundException;
import com.flipkart.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Payment dao implementation
 */
public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public void addPayment(Payment payment) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.INSERT_PAYMENT_QUERY);
            fillPaymentStatement(ps, payment);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void deletePayment(int paymentId) throws PaymentNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.DELETE_PAYMENT_QUERY);
            ps.setInt(1, paymentId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PaymentNotFoundException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public void updatePayment(Payment payment) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(SQLQueries.UPDATE_PAYMENT_QUERY);
            fillPaymentStatement(ps, payment);
            ps.setDate(3, payment.getDate());
            ps.setInt(4, payment.getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, null);
        }
    }

    @Override
    public Payment getPayment(int paymentId) throws PaymentNotFoundException, DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Payment payment = null;

        try {
            ps = connection.prepareStatement(SQLQueries.GET_PAYMENT_QUERY);
            ps.setInt(1, paymentId);
            ps.execute();

            rs = ps.getResultSet();
            if (rs.next()) {
                payment = createPayment(rs);
            } else {
                throw new PaymentNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return payment;
    }

    @Override
    public List<Payment> getPayments(int userId) throws DBOperationException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Payment> payments = null;

        try {
            String sql = SQLQueries.LIST_PAYMENTS_QUERY;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.execute();

            rs = ps.getResultSet();
            payments = new ArrayList<Payment>();
            while (rs.next()) {
                payments.add(createPayment(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBOperationException();
        } finally {
            Utils.closeQuery(ps, rs);
        }

        return payments;
    }

    private Payment createPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setUserId(rs.getInt("userId"));
        payment.setAmount(rs.getInt("amount"));
        payment.setDate(rs.getDate("time"));
        return payment;
    }

    private void fillPaymentStatement(PreparedStatement ps, Payment payment) throws SQLException {
        ps.setInt(1, payment.getUserId());
        ps.setInt(2, payment.getAmount());
    }
}
