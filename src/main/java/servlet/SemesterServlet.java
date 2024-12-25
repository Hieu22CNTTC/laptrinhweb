package servlet;

import dao.SemesterDAO;
import model.Semester;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/semester")
public class SemesterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SemesterDAO semesterDAO;

    @Override
    public void init() throws ServletException {
        semesterDAO = new SemesterDAO(); // Khởi tạo DAO cho học kỳ
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
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
                    deleteSemester(request, response);
                    break;
                default:
                    listSemesters(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra trong xử lý yêu cầu.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertSemester(request, response);
                    break;
                case "update":
                    updateSemester(request, response);
                    break;
                default:
                    listSemesters(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra trong xử lý yêu cầu.");
        }
    }

    private void listSemesters(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Semester> semesterList = semesterDAO.getAllSemesters();
        if (semesterList == null || semesterList.isEmpty()) {
            request.setAttribute("message", "Không có học kỳ nào.");
        } else {
            request.setAttribute("semesterList", semesterList);
        }
        request.getRequestDispatcher("semesters/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("semesters/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int semesterId = Integer.parseInt(request.getParameter("id"));
            Semester existingSemester = semesterDAO.getSemesterById(semesterId);
            if (existingSemester == null) {
                request.setAttribute("message", "Học kỳ không tồn tại.");
                listSemesters(request, response);
            } else {
                request.setAttribute("semester", existingSemester);
                request.getRequestDispatcher("semesters/edit.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID học kỳ không hợp lệ.");
        }
    }

    private void insertSemester(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String semesterName = request.getParameter("semesterName");
        String year = request.getParameter("year");

        if (semesterName == null || semesterName.trim().isEmpty() || year == null || year.trim().isEmpty()) {
            request.setAttribute("message", "Tên học kỳ và năm không được để trống.");
            request.getRequestDispatcher("semesters/add.jsp").forward(request, response);
            return;
        }

        boolean isAdded = semesterDAO.addSemester(semesterName, year);

        if (isAdded) {
            response.sendRedirect("semester?action=list");
        } else {
            request.setAttribute("message", "Thêm học kỳ thất bại.");
            request.getRequestDispatcher("semesters/add.jsp").forward(request, response);
        }
    }

    private void updateSemester(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int semesterId = Integer.parseInt(request.getParameter("id"));
            String semesterName = request.getParameter("semesterName");
            String year = request.getParameter("year");

            if (semesterName == null || semesterName.trim().isEmpty() || year == null || year.trim().isEmpty()) {
                request.setAttribute("message", "Tên học kỳ và năm không được để trống.");
                showEditForm(request, response);
                return;
            }

            boolean isUpdated = semesterDAO.updateSemester(semesterId, semesterName, year);
            if (isUpdated) {
                response.sendRedirect("semester?action=list");
            } else {
                request.setAttribute("message", "Cập nhật học kỳ thất bại.");
                showEditForm(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID học kỳ không hợp lệ.");
        }
    }

    private void deleteSemester(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int semesterId = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = semesterDAO.deleteSemesterById(semesterId);
            if (isDeleted) {
                response.sendRedirect("semester?action=list");
            } else {
                request.setAttribute("message", "Xóa học kỳ thất bại.");
                listSemesters(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID học kỳ không hợp lệ.");
        }
    }
}
