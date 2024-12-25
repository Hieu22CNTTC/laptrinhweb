<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Học Kỳ</title>
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
            <h2 class="text-center">Chỉnh Sửa Học Kỳ</h2>

            <!-- Hiển thị thông báo nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-danger">
                    ${message}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/semester?action=update" method="post">
                <!-- ID Học Kỳ -->
                <input type="hidden" name="id" value="${semester.semesterId}" />

                <!-- Tên Học Kỳ -->
                <div class="form-group">
                    <label for="semesterName">Tên Học Kỳ:</label>
                    <input type="text" id="semesterName" name="semesterName" class="form-control" 
                           value="${semester.semesterName}" required />
                </div>

                <!-- Năm -->
                <div class="form-group">
                    <label for="year">Năm:</label>
                    <input type="text" id="year" name="year" class="form-control" 
                           value="${semester.year}" required />
                </div>

                <!-- Nút hành động -->
                <button type="submit" class="btn btn-primary">Cập Nhật</button>
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
