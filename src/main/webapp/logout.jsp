<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Sử dụng tên biến khác nếu session đã được khai báo trước
    HttpSession currentSession = request.getSession(false); // Đổi tên thành currentSession
    if (currentSession != null) {
        // Hủy session
        currentSession.invalidate();
    }
    // Chuyển hướng về trang đăng nhập
    response.sendRedirect("index.jsp");
%>
