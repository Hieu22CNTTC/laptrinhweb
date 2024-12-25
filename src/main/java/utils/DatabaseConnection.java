package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver"); // Thêm dòng này
            // Thông tin kết nối MySQL với UTF-8
            String url = "jdbc:mysql://localhost:3306/student_fee_management?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
            String user = "root"; // Tên tài khoản MySQL
            String password = "ued123"; // Mật khẩu MySQL
            
            // Tạo kết nối
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {

            // Kiểm tra kết nối
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
            } else {
                System.out.println("Kết nối cơ sở dữ liệu thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
