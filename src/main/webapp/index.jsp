<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="sidebar.jsp" %> <!-- Sidebar linked -->
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession currentSession = request.getSession(false); 
    String username = null;

    if (currentSession != null) {
        username = (String) currentSession.getAttribute("username"); 
    }
%>


<!DOCTYPE html>
<html>
<head>
    <title>Hệ thống quản lý đoàn phí</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="styless.css">
    <style>
        body {
            font-family: 'Times New Roman', Times, serif, sans-serif;
           
        }

        .main-content {
            margin-left: 270px; /* Ensure proper space for sidebar */
            padding: 20px;
        }

        .card {
            cursor: pointer;
        }

        .card i {
            font-size: 50px;
        }

        .card:hover {
            transform: scale(1.05);
            transition: 0.3s;
        }

        /* Center the text in card title */
        .card-title {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <!-- Sidebar already included above -->

    <!-- Main Content -->
    <div class="d-flex justify-content-end mb-3">
        <% if (username != null) { %>
            <!-- Hiển thị tên tài khoản nếu đã đăng nhập -->
            <p class="me-3">Xin chào, <b><%= username %></b></p>
            <a href="logout.jsp" class="btn btn-danger">
                <i class="fas fa-sign-out-alt"></i> Đăng Xuất
            </a>
        <% } else { %>
            <!-- Nút Đăng Nhập nếu chưa đăng nhập -->
            <a href="login.jsp" class="btn btn-primary">
                <i class="fas fa-sign-in-alt"></i> Đăng Nhập
            </a>
        <% } %>
    </div>

    <div class="main-content">
        <div class="d-flex align-items-center justify-content-center mb-3">
            <img src="https://ued.udn.vn/uploads/photos/images/2021/04/logo-nha-truong/logo-2021.jpg" alt="Logo" class="logo">
            <h1 class="text-center">BẢNG ĐIỀU KHIỂN</h1>
        </div>
        <p class="text-center">Chào mừng bạn đến với <b>Hệ thống quản lý đoàn phí</b>. Vui lòng chọn chức năng bên dưới.</p>

        <div class="row mt-4">
            <!-- Quản lý sinh viên -->
            <div class="col-md-4 mb-4">
                <div class="card text-center bg-danger text-white" onclick="location.href='student?action=list';">
                    <div class="card-body">
                        <i class="fas fa-user-graduate"></i>
                        <h5 class="card-title">Quản Lý Sinh Viên</h5>
                    </div>
                </div>
            </div>
            <!-- Quản lý các khoản phí -->
            <div class="col-md-4 mb-4">
                <div class="card text-center bg-success text-white" onclick="location.href='fee?action=list';">
                    <div class="card-body">
                        <i class="fas fa-money-bill-wave"></i>
                        <h5 class="card-title">Quản Lý Các Khoản Phí</h5>
                    </div>
                </div>
            </div>
            <!-- Quản lý thanh toán -->
            <div class="col-md-4 mb-4">
                <div class="card text-center bg-warning text-white" onclick="location.href='payment?action=list';">
                    <div class="card-body">
                        <i class="fas fa-credit-card"></i>
                        <h5 class="card-title">Quản Lý Thanh Toán</h5>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <!-- Quản lý lớp học -->
            <div class="col-md-4 mb-4">
                <div class="card text-center bg-primary text-white" onclick="location.href='class?action=list';">
                    <div class="card-body">
                        <i class="fas fa-chalkboard-teacher"></i>
                        <h5 class="card-title">Quản Lý Lớp Học</h5>
                    </div>
                </div>
            </div>
            <!-- Quản lý học kỳ -->
            <div class="col-md-4 mb-4">
                <div class="card text-center bg-info text-white" onclick="location.href='semester?action=list';">
                    <div class="card-body">
                        <i class="fas fa-calendar-alt"></i>
                        <h5 class="card-title">Quản Lý Học Kỳ</h5>
                    </div>
                </div>
            </div>
            <!-- Đăng xuất -->
            <div class="col-md-4 mb-4">
                <div class="card text-center bg-secondary text-white" onclick="location.href='logout.jsp';">
                    <div class="card-body">
                        <i class="fas fa-sign-out-alt"></i>
                        <h5 class="card-title">Đăng Xuất</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="text-center mt-5">
    	<p>&copy; 2024 Thanh toán đoàn phí - Đại học Sư phạm Đà Nẵng</p>
	</footer>
    

    <!-- Include necessary scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
