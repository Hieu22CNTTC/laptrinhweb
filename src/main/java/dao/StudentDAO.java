package dao;

import model.Student;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Lớp DAO để quản lý bảng students
public class StudentDAO {

    // Lấy danh sách tất cả sinh viên
	public List<Student> getAllStudents() {
	    List<Student> studentList = new ArrayList<>();
	    String query = "SELECT s.student_id, s.name, s.email, s.class_id, c.class_name FROM students s " +
	                   "LEFT JOIN classes c ON s.class_id = c.class_id"; // 

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {
	            Student student = new Student(
	                    resultSet.getInt("student_id"),
	                    resultSet.getString("name"),
	                    resultSet.getString("email"),
	                    resultSet.getInt("class_id"),
	                    resultSet.getString("class_name") // Lấy tên lớp
	            );
	            studentList.add(student);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return studentList;
	}


    // Thêm một sinh viên mới
    public boolean addStudent(String name, String email, int classId) {
        String query = "INSERT INTO students (name, email, class_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, classId);

            return preparedStatement.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một sinh viên theo ID
    public boolean deleteStudentById(int studentId) {
        String query = "DELETE FROM students WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);

            return preparedStatement.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin sinh viên
    public boolean updateStudent(int studentId, String name, String email, int classId) {
        String query = "UPDATE students SET name = ?, email = ?, class_id = ? WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, classId);
            preparedStatement.setInt(4, studentId);

            return preparedStatement.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }// Lấy danh sách sinh viên theo classId
    public List<Student> getStudentsByClassId(int classId) {
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT s.student_id, s.name, s.email, s.class_id, c.class_name " +
                       "FROM students s LEFT JOIN classes c ON s.class_id = c.class_id WHERE s.class_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, classId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("student_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("class_id"),
                            resultSet.getString("class_name")
                    );
                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }


    // Lấy thông tin sinh viên theo ID
    public Student getStudentById(int studentId) {
        String query = "SELECT s.student_id, s.name, s.email, s.class_id, c.class_name FROM students s " +
                       "LEFT JOIN classes c ON s.class_id = c.class_id WHERE s.student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(
                            resultSet.getInt("student_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("class_id"), // Lấy ID lớp
                            resultSet.getString("class_name") // Lấy tên lớp
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

}
