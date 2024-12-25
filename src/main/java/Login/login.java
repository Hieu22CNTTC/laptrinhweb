package Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login") // Định nghĩa URL servlet
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Lấy thông tin từ form đăng nhập
        String username = request.getParameter("name");
        String password = request.getParameter("password");

        // Cấu hình thông tin kết nối cơ sở dữ liệu
        String url = "jdbc:mysql://localhost:3306/student_fee_management?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
        String dbUser = "root";
        String dbPassword = "ued123";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Đăng ký driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối cơ sở dữ liệu
            connection = DriverManager.getConnection(url, dbUser, dbPassword);

            // Truy vấn kiểm tra thông tin đăng nhập
            String query = "SELECT username, role FROM Users WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Đăng nhập thành công
                String role = resultSet.getString("role");

                // Lưu thông tin vào session
                HttpSession session = request.getSession();
                session.setAttribute("username", username); // Lưu tên tài khoản
                session.setAttribute("role", role);         // Lưu vai trò (nếu cần)

                // Chuyển hướng đến trang chính
                response.sendRedirect("index.jsp");
            } else {
                // Sai tên đăng nhập hoặc mật khẩu
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (Exception e) {
            // Log lỗi để kiểm tra
            e.printStackTrace();
            // Chuyển hướng về trang đăng nhập với thông báo lỗi hệ thống
            response.sendRedirect("login.jsp?error=db");
        } finally {
            // Đóng các tài nguyên để tránh rò rỉ
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
