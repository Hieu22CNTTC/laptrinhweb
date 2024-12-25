package servlet;

import dao.FeeDAO;
import dao.SemesterDAO;
import model.Fee;
import model.Semester;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/fee")
public class FeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FeeDAO feeDAO;
    private SemesterDAO semesterDAO;

    @Override
    public void init() throws ServletException {
        feeDAO = new FeeDAO();
        semesterDAO = new SemesterDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                    deleteFee(request, response);
                    break;
                default:
                    listFees(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertFee(request, response);
                    break;
                case "update":
                    updateFee(request, response);
                    break;
                default:
                    listFees(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(request, response, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private void listFees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Fee> feeList = feeDAO.getAllFees();
        request.setAttribute("feeList", feeList);
        request.getRequestDispatcher("fees/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Semester> semesterList = semesterDAO.getAllSemesters();
            request.setAttribute("semesterList", semesterList);
            request.getRequestDispatcher("fees/add.jsp").forward(request, response);
        } catch (Exception e) {
            handleError(request, response, "Không thể tải danh sách học kỳ: " + e.getMessage());
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int feeId = Integer.parseInt(request.getParameter("id"));
            Fee existingFee = feeDAO.getFeeById(feeId);
            if (existingFee == null) {
                handleError(request, response, "Không tìm thấy khoản phí với ID: " + feeId);
                return;
            }

            List<Semester> semesterList = semesterDAO.getAllSemesters();
            request.setAttribute("fee", existingFee);
            request.setAttribute("semesterList", semesterList);
            request.getRequestDispatcher("fees/edit.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            handleError(request, response, "ID không hợp lệ.");
        }
    }

    private void insertFee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int semesterId = Integer.parseInt(request.getParameter("semesterId"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));

            boolean isAdded = feeDAO.addFee(semesterId, amount.doubleValue());
            if (isAdded) {
                response.sendRedirect("fee?action=list");
            } else {
                handleError(request, response, "Thêm khoản phí thất bại.");
            }
        } catch (NumberFormatException e) {
            handleError(request, response, "Dữ liệu nhập không hợp lệ.");
        }
    }

    private void updateFee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int feeId = Integer.parseInt(request.getParameter("id"));
            int semesterId = Integer.parseInt(request.getParameter("semesterId"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));

            boolean isUpdated = feeDAO.updateFee(feeId, semesterId, amount.doubleValue());
            if (isUpdated) {
                response.sendRedirect("fee?action=list");
            } else {
                handleError(request, response, "Cập nhật khoản phí thất bại.");
            }
        } catch (NumberFormatException e) {
            handleError(request, response, "Dữ liệu nhập không hợp lệ.");
        }
    }

    private void deleteFee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int feeId = Integer.parseInt(request.getParameter("id"));

            boolean isDeleted = feeDAO.deleteFeeById(feeId);
            if (isDeleted) {
                response.sendRedirect("fee?action=list");
            } else {
                handleError(request, response, "Xóa khoản phí thất bại.");
            }
        } catch (NumberFormatException e) {
            handleError(request, response, "ID không hợp lệ.");
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
