<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title>Thêm Học Kỳ</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .main-content {
            margin-left: 270px; /* Khoảng cách phù hợp với sidebar */
            padding: 20px;
        }
        @media (max-width: 768px) {
            .main-content {
                margin-left: 0;
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <jsp:include page="../sidebar.jsp" />

    <!-- Main Content -->
    <div class="main-content">
        <div class="container">
            <h2 class="text-center">Thêm Học Kỳ</h2>

            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-danger">
                    ${message}
                </div>
            </c:if>

            <!-- Form thêm học kỳ -->
            <form action="${pageContext.request.contextPath}/semester?action=insert" method="post">
                <!-- Tên Học Kỳ -->
                <div class="form-group">
                    <label for="semesterName">Tên Học Kỳ:</label>
                    <input type="text" id="semesterName" name="semesterName" class="form-control" placeholder="Nhập tên học kỳ" required>
                </div>

                <!-- Năm -->
                <div class="form-group">
                    <label for="year">Năm:</label>
                    <input type="text" id="year" name="year" class="form-control" placeholder="Nhập năm" required>
                </div>

                <!-- Nút Lưu và Hủy -->
                <button type="submit" class="btn btn-primary">Lưu</button>
                <a href="${pageContext.request.contextPath}/semester?action=list" class="btn btn-secondary">Hủy</a>
            </form>
        </div>
    </div>

    <!-- Bootstrap Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
