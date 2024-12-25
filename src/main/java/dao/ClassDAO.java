package dao;

import model.Class;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {

    public List<Class> getAllClasses() {
        List<Class> classList = new ArrayList<>();
        String query = "SELECT * FROM classes";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Class classObj = new Class();
                classObj.setClassId(resultSet.getInt("class_id"));
                classObj.setClassName(resultSet.getString("class_name"));
                classObj.setDescription(resultSet.getString("description"));
                classList.add(classObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classList;
    }

    // Lấy thông tin lớp học theo classId
    public Class getClassById(int classId) {
        String query = "SELECT * FROM classes WHERE class_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, classId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Class classObj = new Class();
                    classObj.setClassId(resultSet.getInt("class_id"));
                    classObj.setClassName(resultSet.getString("class_name"));
                    classObj.setDescription(resultSet.getString("description"));
                    return classObj; // Trả về đối tượng Class
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    public boolean addClass(String className, String description) {
        String query = "INSERT INTO classes (class_name, description) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, className);
            preparedStatement.setString(2, description);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public boolean updateClass(int classId, String className, String description) {
        String query = "UPDATE classes SET class_name = ?, description = ? WHERE class_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, className);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, classId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 
    public boolean deleteClassById(int classId) {
        String query = "DELETE FROM classes WHERE class_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, classId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
