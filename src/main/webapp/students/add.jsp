<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Sinh Viên Mới</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .main-content {
            margin-left: 270px;
            padding: 20px;
        }
        .container {
            margin-top: 20px;
        }
        label {
            font-weight: bold;
        }
        .btn {
            width: 100px;
            margin: 5px;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <jsp:include page="../sidebar.jsp" />

    <!-- Main Content -->
    <div class="main-content">
        <div class="container">
            <h1 class="text-center">Thêm Sinh Viên Mới</h1>
            <form action="student?action=insert" method="post" accept-charset="UTF-8">
                <!-- Tên Sinh Viên -->
                <div class="form-group">
                    <label for="name">Tên Sinh Viên:</label>
                    <input type="text" id="name" name="name" class="form-control" required>
                </div>

                <!-- Email -->
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>

                <!-- Lớp -->
                <div class="form-group">
                    <label for="classId">Lớp:</label>
                    <select id="classId" name="classId" class="form-control" required>
                        <option value="" disabled selected>Chọn lớp</option>
                        <c:if test="${not empty classList}">
                            <c:forEach var="clazz" items="${classList}">
                                <option value="${clazz.classId}">${clazz.className}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <!-- Nút hành động -->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Thêm</button>
                    <a href="student?action=list" class="btn btn-secondary">Hủy</a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
