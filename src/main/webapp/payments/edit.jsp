<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa Thanh Toán</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body { font-family: Arial, sans-serif; }
        .container { padding-top: 20px; }
        .form-group { margin-bottom: 1rem; }
        .main-content {
            margin-left: 270px; /* Phù hợp sidebar */
            padding: 20px;
        }
        @media (max-width: 768px) {
            .main-content { margin-left: 0; padding: 10px; }
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <jsp:include page="../sidebar.jsp" />

    <!-- Main Content -->
    <div class="main-content">
        <h2 class="text-center my-4">Chỉnh sửa Thanh Toán</h2>

        <!-- Form chỉnh sửa thanh toán -->
        <form action="${pageContext.request.contextPath}/payment?action=update" method="post">
            <!-- Hidden ID -->
            <input type="hidden" name="id" value="${payment.paymentId}">

            <!-- Mã sinh viên -->
            <div class="form-group">
                <label for="studentId">Mã sinh viên:</label>
                <input type="text" id="studentId" name="studentId" class="form-control" value="${payment.studentId}" readonly>
            </div>

            <!-- Chọn lớp -->
            <div class="form-group">
                <label for="classId">Chọn lớp:</label>
                <select id="classId" name="classId" class="form-control">
                    <c:forEach var="classObj" items="${classList}">
                        <option value="${classObj.classId}" <c:if test="${payment.classId == classObj.classId}">selected</c:if>>
                            ${classObj.className}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Chọn học kỳ -->
            <div class="form-group">
                <label for="semesterId">Chọn học kỳ:</label>
                <select id="semesterId" name="semesterId" class="form-control">
                    <c:forEach var="semester" items="${semesterList}">
                        <option value="${semester.semesterId}" <c:if test="${payment.semesterId == semester.semesterId}">selected</c:if>>
                            ${semester.semesterName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Trạng thái -->
            <div class="form-group">
                <label for="status">Trạng thái:</label>
                <select id="status" name="status" class="form-control">
                    <option value="Pending" <c:if test="${payment.status == 'Pending'}">selected</c:if>>Pending</option>
                    <option value="Paid" <c:if test="${payment.status == 'Paid'}">selected</c:if>>Paid</option>
                </select>
            </div>

            <!-- Nút lưu và quay lại -->
            <div class="text-center">
                <button type="submit" class="btn btn-success">Lưu</button>
                <a href="${pageContext.request.contextPath}/payment?action=list" class="btn btn-secondary">Quay lại</a>
            </div>
        </form>
    </div>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>