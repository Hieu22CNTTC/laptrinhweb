package dao;

import model.Payment;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

	public List<Payment> getAllPayments() {
	    List<Payment> paymentList = new ArrayList<>();
	    String sql = "SELECT p.payment_id, p.student_id, s.name AS student_name, "
	               + "p.fee_id, f.amount AS amount, p.status, "
	               + "c.class_id, c.class_name, sem.semester_id, sem.semester_name "
	               + "FROM payments p "
	               + "JOIN students s ON p.student_id = s.student_id "
	               + "JOIN classes c ON p.class_id = c.class_id "
	               + "JOIN semesters sem ON p.semester_id = sem.semester_id "
	               + "JOIN fees f ON p.fee_id = f.fee_id";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql);
	         ResultSet rs = preparedStatement.executeQuery()) {

	        while (rs.next()) {
	            Payment payment = new Payment(
	                    rs.getInt("payment_id"),
	                    rs.getInt("student_id"),
	                    rs.getString("student_name"),
	                    rs.getInt("fee_id"),
	                    rs.getDouble("amount"),
	                    rs.getString("status"),
	                    rs.getInt("class_id"),
	                    rs.getString("class_name"),
	                    rs.getInt("semester_id"),
	                    rs.getString("semester_name")
	            );
	            paymentList.add(payment);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error in getAllPayments: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return paymentList;
	}

    // Thêm một khoản thanh toán mới
    public boolean addPayment(int studentId, int feeId, String status, int classId, int semesterId) {
        String query = "INSERT INTO payments (student_id, fee_id, status, class_id, semester_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, feeId);
            preparedStatement.setString(3, status);
            preparedStatement.setInt(4, classId);
            preparedStatement.setInt(5, semesterId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in addPayment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin khoản thanh toán
    public boolean updatePayment(int paymentId, int studentId, String status, int classId, int semesterId) {
        String query = "UPDATE payments SET student_id = ?, status = ?, class_id = ?, semester_id = ? WHERE payment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, classId);
            preparedStatement.setInt(4, semesterId);
            preparedStatement.setInt(5, paymentId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in updatePayment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Xóa khoản thanh toán
    public boolean deletePaymentById(int paymentId) {
        String query = "DELETE FROM payments WHERE payment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, paymentId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error in deletePaymentById: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thông tin khoản thanh toán theo ID
    public Payment getPaymentById(int paymentId) {
        String query = "SELECT p.payment_id, p.student_id, s.name AS student_name, "
                     + "p.fee_id, f.amount AS amount, p.status, "
                     + "c.class_id, c.class_name, sem.semester_id, sem.semester_name "
                     + "FROM payments p "
                     + "JOIN students s ON p.student_id = s.student_id "
                     + "JOIN classes c ON p.class_id = c.class_id "
                     + "JOIN semesters sem ON p.semester_id = sem.semester_id "
                     + "JOIN fees f ON p.fee_id = f.fee_id "
                     + "WHERE p.payment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, paymentId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new Payment(
                            rs.getInt("payment_id"),
                            rs.getInt("student_id"),
                            rs.getString("student_name"),
                            rs.getInt("fee_id"),
                            rs.getDouble("amount"),
                            rs.getString("status"),
                            rs.getInt("class_id"),
                            rs.getString("class_name"),
                            rs.getInt("semester_id"),
                            rs.getString("semester_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getPaymentById: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách khoản thanh toán theo lớp và học kỳ
    public List<Payment> getPaymentsByClassAndSemester(int classId, int semesterId) {
        List<Payment> paymentList = new ArrayList<>();
        String query = "SELECT p.payment_id, p.student_id, s.name AS student_name, "
                     + "p.fee_id, f.amount AS amount, p.status, "
                     + "c.class_id, c.class_name, sem.semester_id, sem.semester_name "
                     + "FROM payments p "
                     + "JOIN students s ON p.student_id = s.student_id "
                     + "JOIN classes c ON p.class_id = c.class_id "
                     + "JOIN semesters sem ON p.semester_id = sem.semester_id "
                     + "JOIN fees f ON p.fee_id = f.fee_id "
                     + "WHERE p.class_id = ? AND p.semester_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, semesterId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Payment payment = new Payment(
                            rs.getInt("payment_id"),
                            rs.getInt("student_id"),
                            rs.getString("student_name"),
                            rs.getInt("fee_id"),
                            rs.getDouble("amount"),
                            rs.getString("status"),
                            rs.getInt("class_id"),
                            rs.getString("class_name"),
                            rs.getInt("semester_id"),
                            rs.getString("semester_name")
                    );
                    paymentList.add(payment);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in getPaymentsByClassAndSemester: " + e.getMessage());
            e.printStackTrace();
        }

        return paymentList;
    }
}
 