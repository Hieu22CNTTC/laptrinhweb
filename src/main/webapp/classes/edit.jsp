<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa lớp học</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 20px;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Chỉnh Sửa Lớp Học</h1>
        <!-- Hiển thị thông báo lỗi nếu có -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">
                ${error}
            </div>
        </c:if>

        <!-- Form chỉnh sửa lớp học -->
        <form action="class?action=update" method="post" accept-charset="UTF-8">
            <!-- Truyền classId vào hidden input -->
            <input type="hidden" name="classId" value="${clazz.classId}">
            <div class="form-group">
                <label for="className">Tên Lớp:</label>
                <input type="text" id="className" name="className" class="form-control" value="${clazz.className}" required>
            </div>
            <div class="form-group">
                <label for="description">Mô Tả:</label>
                <textarea id="description" name="description" class="form-control" rows="4" required>${clazz.description}</textarea>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Cập Nhật</button>
                <a href="class?action=list" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
