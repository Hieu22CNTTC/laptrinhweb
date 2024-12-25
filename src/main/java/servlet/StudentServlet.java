package servlet;

import dao.ClassDAO;
import dao.StudentDAO;
import model.Class;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Servlet quản lý CRUD cho Student
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;
    private ClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO(); // Khởi tạo DAO cho Student
        classDAO = new ClassDAO(); // Khởi tạo DAO cho Class
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "list";
            }

            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        } catch (Exception e) {
            handleError(e, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertStudent(request, response);
                    break;
                case "update":
                    updateStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        } catch (Exception e) {
            handleError(e, request, response);
        }
    }

    // Hiển thị danh sách sinh viên
    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> studentList = studentDAO.getAllStudents();
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("students/list.jsp").forward(request, response);
    }

    // Hiển thị form thêm sinh viên mới
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Class> classList = classDAO.getAllClasses(); // Lấy danh sách lớp
        request.setAttribute("classList", classList);
        request.getRequestDispatcher("students/add.jsp").forward(request, response);
    }

    // Hiển thị form chỉnh sửa sinh viên
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("id"));
            Student existingStudent = studentDAO.getStudentById(studentId);
            
            if (existingStudent == null) {
                throw new IllegalArgumentException("Không tìm thấy sinh viên với ID: " + studentId);
            }

            List<Class> classList = classDAO.getAllClasses(); // Lấy danh sách lớp
            request.setAttribute("student", existingStudent);
            request.setAttribute("classList", classList);
            request.getRequestDispatcher("students/edit.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID sinh viên không hợp lệ.");
        }
    }

    // Thêm sinh viên mới
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int classId = Integer.parseInt(request.getParameter("classId"));

            if (name.isEmpty() || email.isEmpty()) {
                request.setAttribute("message", "Tên và email không được để trống.");
                showNewForm(request, response);
                return;
            }

            boolean isAdded = studentDAO.addStudent(name, email, classId);
            if (isAdded) {
                response.sendRedirect("student?action=list");
            } else {
                request.setAttribute("message", "Thêm sinh viên thất bại.");
                showNewForm(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Dữ liệu nhập không hợp lệ.");
            showNewForm(request, response);
        }
    }

    // Cập nhật thông tin sinh viên
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int classId = Integer.parseInt(request.getParameter("classId"));

            if (name.isEmpty() || email.isEmpty()) {
                request.setAttribute("message", "Tên và email không được để trống.");
                showEditForm(request, response);
                return;
            }

            boolean isUpdated = studentDAO.updateStudent(studentId, name, email, classId);
            if (isUpdated) {
                response.sendRedirect("student?action=list");
            } else {
                request.setAttribute("message", "Cập nhật sinh viên thất bại.");
                showEditForm(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Dữ liệu nhập không hợp lệ.");
            showEditForm(request, response);
        }
    }

    // Xóa sinh viên
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = studentDAO.deleteStudentById(studentId);
            if (isDeleted) {
                response.sendRedirect("student?action=list");
            } else {
                request.setAttribute("message", "Xóa sinh viên thất bại.");
                listStudents(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ID sinh viên không hợp lệ.");
            listStudents(request, response);
        }
    }

    // Xử lý lỗi và gửi thông báo lỗi
    private void handleError(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("errorMessage", e.getMessage());
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
