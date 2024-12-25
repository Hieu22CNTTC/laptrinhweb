package dao;

import model.Semester;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SemesterDAO {
    // Lấy danh sách tất cả học kỳ
	public List<Semester> getAllSemesters() {
	    List<Semester> semesterList = new ArrayList<>();
	    String query = "SELECT * FROM semesters";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {
	            Semester semester = new Semester(
	                    resultSet.getInt("semester_id"),
	                    resultSet.getString("semester_name"),
	                    resultSet.getString("year")
	            );
	            semesterList.add(semester);

	            // In log để kiểm tra dữ liệu
	            System.out.println("Fetched Semester: " + semester.getSemesterId() + " - " + semester.getSemesterName());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return semesterList;
	}

	public boolean addSemester(String semesterName, String year) {
	    String query = "INSERT INTO semesters (semester_name, year) VALUES (?, ?)";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        // Gán tham số cho câu lệnh SQL
	        preparedStatement.setString(1, semesterName);
	        preparedStatement.setString(2, year);

	        // Thực thi và trả về kết quả
	        int result = preparedStatement.executeUpdate();
	        System.out.println("Add Semester Result: " + result); // Log kiểm tra
	        return result > 0; // Trả về true nếu thêm thành công
	    } catch (SQLException e) {
	        System.err.println("Error in addSemester: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

    // Xóa học kỳ theo ID
    public boolean deleteSemesterById(int semesterId) {
        String query = "DELETE FROM semesters WHERE semester_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, semesterId);

            return preparedStatement.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSemester(int semesterId, String semesterName, String year) {
        String sql = "UPDATE semesters SET semester_name = ?, year = ? WHERE semester_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, semesterName);
            preparedStatement.setString(2, year);
            preparedStatement.setInt(3, semesterId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating semester: " + e.getMessage());
            return false;
        }
    }

    // Lấy thông tin học kỳ theo ID
    public Semester getSemesterById(int semesterId) {
        String query = "SELECT * FROM semesters WHERE semester_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, semesterId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Semester(
                            resultSet.getInt("semester_id"),
                            resultSet.getString("semester_name"),
                            resultSet.getString("year")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }
}
