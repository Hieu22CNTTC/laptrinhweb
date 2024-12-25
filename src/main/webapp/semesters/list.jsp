<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách học kỳ</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .sidebar {
            width: 250px;
            position: fixed;
            height: 100%;
            background-color: #004d40;
            color: white;
            padding-top: 20px;
        }

        .main-content {
            margin-left: 270px;
            padding: 20px;
        }

        /* Nút Thêm */
        .btn-add {
            background-color: #28a745;
            color: white;
            border-radius: 5px;
        }

        .btn-add:hover {
            background-color: #218838;
            color: white;
        }

        /* Nút Sửa và Xóa */
        .btn-action i {
            margin-right: 5px;
        }

        /* Nút dấu cộng */
        .btn-toggle {
            cursor: pointer;
        }

        .details-row {
            display: none;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <jsp:include page="../sidebar.jsp" />

    <!-- Main Content -->
    <div class="main-content">
        <h1 class="text-center">Danh sách học kỳ</h1>

        <!-- Nút Thêm -->
        <div class="text-right mb-3">
            <a href="semester?action=new" class="btn btn-add">
                <i class="fas fa-plus"></i> Thêm học kỳ
            </a>
        </div>

        <!-- Bảng danh sách học kỳ -->
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>ID Học Kỳ</th>
                    <th>Tên Học Kỳ</th>
                    <th>Năm Học</th>
                    <th>Số tiền</th>
                    <th></th> <!-- Cột nút cộng -->
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty semesterList}">
                        <c:forEach var="semester" items="${semesterList}" varStatus="loop">
                            <!-- Hàng chính -->
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${semester.semesterId}</td>
                                <td>${semester.semesterName}</td>
                                <td>${semester.year}</td>
                                <td>null</td>
                                <td>
                                    <button class="btn btn-info btn-sm btn-toggle" onclick="toggleDetails(${semester.semesterId})">
                                        <i id="icon-${semester.semesterId}" class="fas fa-plus"></i>
                                    </button>
                                </td>
                            </tr>
                            <!-- Hàng ẩn chứa các nút Sửa và Xóa -->
                            <tr id="details-${semester.semesterId}" class="details-row">
                                <td colspan="5" class="bg-light">
                                    <a href="semester?action=edit&id=${semester.semesterId}" class="btn btn-warning btn-sm btn-action">
                                        <i class="fas fa-edit"></i> Sửa
                                    </a>
                                    <a href="semester?action=delete&id=${semester.semesterId}" 
                                       class="btn btn-danger btn-sm btn-action" 
                                       onclick="return confirm('Bạn có chắc muốn xóa học kỳ này không?');">
                                        <i class="fas fa-trash"></i> Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5" class="text-center">Không có học kỳ nào để hiển thị.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // Hàm mở/đóng hàng chứa nút Sửa và Xóa
        function toggleDetails(id) {
            var detailsRow = document.getElementById('details-' + id);
            var icon = document.getElementById('icon-' + id);

            if (detailsRow.style.display === 'none' || detailsRow.style.display === '') {
                detailsRow.style.display = 'table-row';
                icon.classList.remove('fa-plus');
                icon.classList.add('fa-minus');
            } else {
                detailsRow.style.display = 'none';
                icon.classList.remove('fa-minus');
                icon.classList.add('fa-plus');
            }
        }
    </script>
</body>
</html>
