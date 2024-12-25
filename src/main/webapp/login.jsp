<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <style>
        /* Tổng thể body */
        body {
            font-family: 'Times New Roman', Times, serif;
            background-color: linear-gradient(320deg, #eb92be, #ffef78, #63c9b4);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Container chính */
        .login-container {
            background-color: #fff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 100%;
            max-width: 400px;
        }

        /* Tiêu đề */
        .login-container h2 {
            font-size: 1.8rem;
            color: #333;
            margin-bottom: 20px;
            font-weight: bold;
        }

        /* Input fields */
        .login-container input {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            background-color: #f9f9f9;
            transition: border-color 0.3s;
            font-family: 'Times New Roman', Times, serif;
        }

        .login-container input:focus {
            border-color: #007bff;
            outline: none;
            background-color: #fff;
        }

       /* Button */
	.login-container button {
	    width: 100%; /* Đảm bảo nút chiếm toàn bộ chiều ngang container */
	    padding: 12px;
	    font-size: 1rem;
	    font-weight: bold;
	    color: #fff;
	    background-color: #007bff;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	    transition: background-color 0.3s;
	    font-family: 'Times New Roman', Times, serif;
	    display: block; /* Đảm bảo nút là một block element */
	    margin: 0 auto; /* Căn giữa nút */
	}

	.login-container button:hover {
	    background-color: #0056b3;
	}

/* Đảm bảo container của nút không bị lỗi */
	.login-container .button {
	    text-align: center; /* Căn giữa các phần tử bên trong */
	}

        /* Alert */
        .alert {
            font-size: 0.9rem;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            color: #fff;
        }

        .alert-danger {
            background-color: #dc3545;
        }
    </style>
</head>
<body>

<div class="login-container">
    <div class="login-box mx-auto text-center" style="max-width: 400px;">
        <h2 class="mb-4">Chào mừng đến Trang Quản Lý Tiền Đoàn Phí</h2>
        
        <!-- Hiển thị thông báo lỗi nếu có -->
        <%
            String errorMessage = request.getParameter("error");
            if ("1".equals(errorMessage)) {
        %>
            <div class="alert alert-danger text-center" role="alert">
                Sai tên đăng nhập hoặc mật khẩu! Vui lòng thử lại.
            </div>
        <%
            } else if ("2".equals(errorMessage)) {
        %>
            <div class="alert alert-danger text-center" role="alert">
                Lỗi hệ thống! Vui lòng thử lại sau.
            </div>
        <%
            }
        %>
        
        <!-- Form đăng nhập -->
        <form action="login" method="post">
            <div class="mb-3">
                <input type="text" class="form-control" name="name" placeholder="Tên đăng nhập" required>
            </div>
            <div class="mb-3">
                <input type="password" class="form-control" name="password" placeholder="Mật khẩu" required>
            </div>
            <div class="button">
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
