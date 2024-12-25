package servlet;
import dao.StudentDAO;
import dao.ClassDAO;
import model.Class;
import model.Student;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/class")
public class ClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClassDAO classDAO;
    private StudentDAO studentDAO; // Khai báo studentDAO

    @Override
    public void init() throws ServletException {
        classDAO = new ClassDAO(); // Khởi tạo ClassDAO
        studentDAO = new StudentDAO(); // Khởi tạo StudentDAO
    }    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listClasses(request, response);
                    break;
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteClass(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý yêu cầu: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Thiết lập mã hóa UTF-8
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertClass(request, response);
                    break;
                case "update":
                    updateClass(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý yêu cầu: " + e.getMessage());
        }
    }

    private void listClasses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Class> classList = classDAO.getAllClasses();

            for (Class clazz : classList) {
                // Lấy danh sách sinh viên theo classId
                List<Student> studentsList = studentDAO.getStudentsByClassId(clazz.getClassId());
                clazz.setStudentsList(studentsList); // Gán danh sách sinh viên vào lớp
            }

            request.setAttribute("classList", classList);
            request.getRequestDispatcher("classes/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi lấy danh sách lớp học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("classes/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("ID lớp học không hợp lệ.");
            }

            int classId = Integer.parseInt(idParam);
            Class existingClass = classDAO.getClassById(classId);

            if (existingClass == null) {
                throw new IllegalArgumentException("Không tìm thấy lớp học với ID: " + classId);
            }

            request.setAttribute("clazz", existingClass);
            request.getRequestDispatcher("classes/edit.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi lấy thông tin lớp học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void insertClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String className = request.getParameter("className");
            String description = request.getParameter("description");

            if (className == null || className.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên lớp không được để trống.");
            }

            boolean isAdded = classDAO.addClass(className, description);
            if (isAdded) {
                response.sendRedirect("class?action=list");
            } else {
                request.setAttribute("message", "Thêm lớp học thất bại.");
                request.getRequestDispatcher("classes/add.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi thêm lớp học: " + e.getMessage());
            request.getRequestDispatcher("classes/add.jsp").forward(request, response);
        }
    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String classIdParam = request.getParameter("classId");
            if (classIdParam == null || classIdParam.isEmpty()) {
                throw new IllegalArgumentException("ID lớp học không hợp lệ.");
            }

            int classId = Integer.parseInt(classIdParam);
            String className = request.getParameter("className");
            String description = request.getParameter("description");

            if (className == null || className.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên lớp không được để trống.");
            }

            boolean isUpdated = classDAO.updateClass(classId, className, description);
            if (isUpdated) {
                response.sendRedirect("class?action=list");
            } else {
                request.setAttribute("message", "Cập nhật lớp học thất bại.");
                request.getRequestDispatcher("classes/edit.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi cập nhật lớp học: " + e.getMessage());
            request.getRequestDispatcher("classes/edit.jsp").forward(request, response);
        }
    }

    private void deleteClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("ID lớp học không hợp lệ.");
            }

            int classId = Integer.parseInt(idParam);

            boolean isDeleted = classDAO.deleteClassById(classId);
            if (isDeleted) {
                response.sendRedirect("class?action=list");
            } else {
                request.setAttribute("message", "Xóa lớp học thất bại.");
                request.getRequestDispatcher("classes/list.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi xóa lớp học: " + e.getMessage());
            request.getRequestDispatcher("classes/list.jsp").forward(request, response);
        }
    }
}
