package servlet;

import model.Student;
import model.Payment;
import model.Class;
import model.Semester;
import model.Fee;
import dao.StudentDAO;
import dao.PaymentDAO;
import dao.ClassDAO;
import dao.SemesterDAO;
import dao.FeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PaymentDAO paymentDAO;
    private ClassDAO classDAO;
    private SemesterDAO semesterDAO;
    private StudentDAO studentDAO;
    private FeeDAO feeDAO;

    @Override
    public void init() throws ServletException {
        paymentDAO = new PaymentDAO();
        classDAO = new ClassDAO();
        semesterDAO = new SemesterDAO();
        studentDAO = new StudentDAO();
        feeDAO = new FeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deletePayment(request, response);
                    break;
                default:
                    listPayments(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertPayment(request, response);
                    break;
                case "update":
                    updatePayment(request, response);
                    break;
                default:
                    listPayments(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.");
        }
    }

    private void listPayments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classIdStr = request.getParameter("classId");
        String semesterIdStr = request.getParameter("semesterId");

        List<Payment> paymentList;
        if (classIdStr != null && semesterIdStr != null && !classIdStr.isEmpty() && !semesterIdStr.isEmpty()) {
            int classId = Integer.parseInt(classIdStr);
            int semesterId = Integer.parseInt(semesterIdStr);
            paymentList = paymentDAO.getPaymentsByClassAndSemester(classId, semesterId);
        } else {
            paymentList = paymentDAO.getAllPayments();
        }

        List<Class> classList = classDAO.getAllClasses();
        List<Semester> semesterList = semesterDAO.getAllSemesters();

        request.setAttribute("paymentList", paymentList);
        request.setAttribute("classList", classList);
        request.setAttribute("semesterList", semesterList);

        request.getRequestDispatcher("payments/list.jsp").forward(request, response);
    }

    protected void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Class> classList = classDAO.getAllClasses();
        List<Semester> semesterList = semesterDAO.getAllSemesters();
        List<Student> studentList = studentDAO.getAllStudents();
        List<Fee> feeList = feeDAO.getAllFees();

        // Log kiểm tra
        System.out.println("Classes: " + (classList == null ? "null" : classList.size()));
        System.out.println("Semesters: " + (semesterList == null ? "null" : semesterList.size()));
        System.out.println("Students: " + (studentList == null ? "null" : studentList.size()));
        System.out.println("Fees: " + (feeList == null ? "null" : feeList.size()));

        request.setAttribute("classList", classList);
        request.setAttribute("semesterList", semesterList);
        request.setAttribute("studentList", studentList);
        request.setAttribute("feeList", feeList);

        request.getRequestDispatcher("payments/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("id"));
            Payment existingPayment = paymentDAO.getPaymentById(paymentId);

            List<Class> classList = classDAO.getAllClasses();
            List<Semester> semesterList = semesterDAO.getAllSemesters();

            request.setAttribute("payment", existingPayment);
            request.setAttribute("classList", classList);
            request.setAttribute("semesterList", semesterList);

            request.getRequestDispatcher("payments/edit.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "ID thanh toán không hợp lệ.");
            response.sendRedirect(request.getContextPath() + "/payment?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi tải thông tin thanh toán.");
            response.sendRedirect(request.getContextPath() + "/payment?action=list");
        }
    }

    private void insertPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int feeId = Integer.parseInt(request.getParameter("feeId"));
            String status = request.getParameter("status");
            int classId = Integer.parseInt(request.getParameter("classId"));
            int semesterId = Integer.parseInt(request.getParameter("semesterId"));

            boolean isAdded = paymentDAO.addPayment(studentId, feeId, status, classId, semesterId);

            if (isAdded) {
                response.sendRedirect(request.getContextPath() + "/payment?action=list&success=true");
            } else {
                request.setAttribute("error", "Thêm thanh toán không thành công. Vui lòng thử lại.");
                request.setAttribute("classList", classDAO.getAllClasses());
                request.setAttribute("semesterList", semesterDAO.getAllSemesters());
                request.getRequestDispatcher("payments/add.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Dữ liệu nhập không hợp lệ. Vui lòng kiểm tra lại.");
            request.setAttribute("classList", classDAO.getAllClasses());
            request.setAttribute("semesterList", semesterDAO.getAllSemesters());
            request.getRequestDispatcher("payments/add.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi không mong muốn. Vui lòng thử lại.");
            request.setAttribute("classList", classDAO.getAllClasses());
            request.setAttribute("semesterList", semesterDAO.getAllSemesters());
            request.getRequestDispatcher("payments/add.jsp").forward(request, response);
        }
    }
    private void updatePayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("id"));
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int classId = Integer.parseInt(request.getParameter("classId"));
            int semesterId = Integer.parseInt(request.getParameter("semesterId"));
            String status = request.getParameter("status");

            boolean updated = paymentDAO.updatePayment(paymentId, studentId, status, classId, semesterId);

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/payment?action=list");
            } else {
                request.setAttribute("errorMessage", "Không thể cập nhật thanh toán. Vui lòng thử lại.");
                showEditForm(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Dữ liệu không hợp lệ hoặc có lỗi xảy ra!");
            showEditForm(request, response);
        }
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("id"));
            paymentDAO.deletePaymentById(paymentId);
            response.sendRedirect("payment?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("payments/list.jsp?error=true");
        }
    }
}
