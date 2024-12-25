<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Khoản Phí</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .main-content {
            margin-left: 270px; /* Căn chỉnh nội dung theo sidebar */
            padding: 20px;
        }

        .form-container {
            max-width: 600px;
            margin: auto;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .text-center {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <jsp:include page="../sidebar.jsp" />

    <!-- Main Content -->
    <div class="main-content">
        <h1 class="text-center">Thêm Khoản Phí Mới</h1>
        <div class="form-container">
            <form action="fee?action=insert" method="post" accept-charset="UTF-8">
                <!-- Học Kỳ -->
                <div class="form-group">
                    <label for="semesterId">Chọn Học Kỳ:</label>
                    <select id="semesterId" name="semesterId" class="form-control" required>
                        <option value="" disabled selected>Chọn học kỳ</option>
                        <c:forEach var="semester" items="${semesterList}">
                            <option value="${semester.semesterId}">
                                ${semester.semesterName} - ${semester.year}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Số Tiền -->
                <div class="form-group">
                    <label for="amount">Số Tiền:</label>
                    <input 
                        type="number" 
                        id="amount" 
                        name="amount" 
                        class="form-control" 
                        step="1000" 
                        min="1000" 
                        required>
                </div>

                <!-- Các nút hành động -->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Thêm</button>
                    <a href="fee?action=list" class="btn btn-secondary">Hủy</a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
