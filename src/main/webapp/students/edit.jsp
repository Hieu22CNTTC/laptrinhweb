<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa Sinh Viên</title>
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
            <h1 class="text-center">Chỉnh Sửa Sinh Viên</h1>
            <form action="student?action=update" method="post" accept-charset="UTF-8">
                <!-- Hidden input để gửi studentId -->
                <input type="hidden" name="id" value="${student.id}">

                <!-- Tên Sinh Viên -->
                <div class="form-group">
                    <label for="name">Tên Sinh Viên:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${student.name}" required>
                </div>

                <!-- Email -->
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" value="${student.email}" required>
                </div>

                <!-- Lớp -->
                <div class="form-group">
    <label for="classId">Lớp:</label>
    <select id="classId" name="classId" class="form-control" required>
        <option value="">Chọn lớp</option>
        <c:forEach var="clazz" items="${classList}">
            <option value="${clazz.classId}" ${clazz.classId == student.classId ? 'selected' : ''}>
                ${clazz.className}
            </option>
        </c:forEach>
    </select>
</div>
                

                <!-- Nút hành động -->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Cập Nhật</button>
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
