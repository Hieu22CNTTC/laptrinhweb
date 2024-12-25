package dao;

import model.Fee;
import utils.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Lớp DAO để quản lý bảng fees
public class FeeDAO {

    // Lấy danh sách tất cả các khoản phí
    public List<Fee> getAllFees() {
        List<Fee> feeList = new ArrayList<>();
        String query = "SELECT fee_id, semester_id, amount FROM fees";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Fee fee = new Fee(
                        resultSet.getInt("fee_id"), // ID khoản phí
                        resultSet.getInt("semester_id"), // Học kỳ
                        resultSet.getBigDecimal("amount") // Số tiền (không chỉnh sửa)
                );
                feeList.add(fee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feeList;
    }

    // Thêm một khoản phí mới
    public boolean addFee(int semesterId, double amount) {
        String query = "INSERT INTO fees (semester_id, amount) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, semesterId);
            preparedStatement.setDouble(2, amount); // Lưu trực tiếp giá trị nhập từ giao diện

            return preparedStatement.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một khoản phí theo ID
    public boolean deleteFeeById(int feeId) {
        String query = "DELETE FROM fees WHERE fee_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, feeId);

            return preparedStatement.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin khoản phí
    public boolean updateFee(int feeId, int semesterId, double amount) {
        String query = "UPDATE fees SET semester_id = ?, amount = ? WHERE fee_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, semesterId);
            preparedStatement.setDouble(2, amount); // Lưu trực tiếp giá trị nhập từ giao diện
            preparedStatement.setInt(3, feeId);

            return preparedStatement.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thông tin khoản phí theo ID
    public Fee getFeeById(int feeId) {
        String query = "SELECT fee_id, semester_id, amount FROM fees WHERE fee_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, feeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Fee(
                            resultSet.getInt("fee_id"), // ID khoản phí
                            resultSet.getInt("semester_id"), // Học kỳ
                            resultSet.getBigDecimal("amount") // Số tiền (không chỉnh sửa)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }
}
