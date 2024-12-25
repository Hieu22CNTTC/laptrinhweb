<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thêm Thanh Toán</title>
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
            <h2 class="text-center">Thêm Thanh Toán</h2>

            <!-- Hiển thị lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/payment?action=add" method="post">

                <!-- Mã Sinh Viên -->
                <div class="form-group">
                    <label for="studentId">Mã Sinh Viên:</label>
                    <select id="studentId" name="studentId" class="form-control" required>
                        <option value="">-- Chọn sinh viên --</option>
                        <c:forEach var="student" items="${studentList}">
                            <option value="${student.studentId}">
                                ${student.studentId} - ${student.studentName}
                            </option>
                        </c:forEach>
                        <c:if test="${empty studentList}">
                            <option value="">Không có sinh viên nào</option>
                        </c:if>
                    </select>
                </div>

                <!-- Khoản Phí -->
                <div class="form-group">
                    <label for="feeId">Khoản Phí:</label>
                    <select id="feeId" name="feeId" class="form-control" required>
                        <option value="">-- Chọn khoản phí --</option>
                        <c:forEach var="fee" items="${feeList}">
                            <option value="${fee.feeId}">
                                ${fee.feeName} - ${fee.amount} VND
                            </option>
                        </c:forEach>
                        <c:if test="${empty feeList}">
                            <option value="">Không có khoản phí nào</option>
                        </c:if>
                    </select>
                </div>

                <!-- Trạng Thái -->
                <div class="form-group">
                    <label for="status">Trạng Thái:</label>
                    <select id="status" name="status" class="form-control" required>
                        <option value="Paid">Paid</option>
                        <option value="Pending">Pending</option>
                    </select>
                </div>

                <!-- Lớp Học -->
                <div class="form-group">
                    <label for="classId">Lớp Học:</label>
                    <select id="classId" name="classId" class="form-control" required>
                        <option value="">-- Chọn lớp học --</option>
                        <c:forEach var="classObj" items="${classList}">
                            <option value="${classObj.classId}">
                                ${classObj.className}
                            </option>
                        </c:forEach>
                        <c:if test="${empty classList}">
                            <option value="">Không có lớp học nào</option>
                        </c:if>
                    </select>
                </div>

                <!-- Học Kỳ -->
                <div class="form-group">
                    <label for="semesterId">Học Kỳ:</label>
                    <select id="semesterId" name="semesterId" class="form-control" required>
                        <option value="">-- Chọn học kỳ --</option>
                        <c:forEach var="semester" items="${semesterList}">
                            <option value="${semester.semesterId}">
                                ${semester.semesterName}
                            </option>
                        </c:forEach>
                        <c:if test="${empty semesterList}">
                            <option value="">Không có học kỳ nào</option>
                        </c:if>
                    </select>
                </div>

                <!-- Nút hành động -->
                <button type="submit" class="btn btn-primary">Thêm Mới</button>
                <a href="${pageContext.request.contextPath}/payment?action=list" class="btn btn-secondary">Hủy</a>
            </form>
        </div>
    </div>

    <!-- Bootstrap Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>