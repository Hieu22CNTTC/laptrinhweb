<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .sidebar {
            height: 100vh;
            background-color: #466af0;
            color: white;
            padding-top: 20px;
            position: fixed;
            width: 250px;
        }

        .sidebar a {
            color: white;
            display: block;
            padding: 10px 20px;
            text-decoration: none;
        }

        .sidebar a:hover {
            background-color: #00796b;
            color: white;
        }

        .sidebar h4 {
            color: white;
            font-size: 18px;
        }

        .sidebar hr {
            border-color: white;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h4 class="text-center">HỆ THỐNG QUẢN LÝ<br>ĐOÀN PHÍ</h4>
        <hr>
        <a href="index.jsp"><i class="fas fa-tachometer-alt"></i> Bảng Điều Khiển</a>
        <a href="class?action=list"><i class="fas fa-chalkboard-teacher"></i> Lớp Học</a>
        <a href="student?action=list"><i class="fas fa-user-graduate"></i> Sinh Viên</a>
        <a href="fee?action=list"><i class="fas fa-money-bill-wave"></i> Các Khoản Phí</a>
        <a href="semester?action=list"><i class="fas fa-calendar-alt"></i> Học Kỳ</a> <!-- Quản lý Học Kỳ -->
        <a href="payment?action=list"><i class="fas fa-credit-card"></i> Thanh Toán</a>
        <a href="logout.jsp"><i class="fas fa-sign-out-alt"></i> Đăng Xuất</a>
    </div>
</body>
</html>