<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sinh viên</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .main-content {
            margin-left: 250px;
            padding: 20px;
        }

        .btn-add {
            background-color: #28a745;
            color: white;
        }

        .btn-add:hover {
            background-color: #218838;
        }

        /* Căn giữa nội dung trong bảng */
        table th, table td {
            text-align: center;
            vertical-align: middle;
        }

        /* Nút mở rộng */
        .collapse-btn {
            cursor: pointer;
            color: #007bff;
            font-size: 16px;
        }

        /* Footer */
        .footer {
            text-align: center;
            padding: 10px;
            margin-top: 20px;
            font-size: 14px;
            color: gray;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <jsp:include page="../sidebar.jsp"></jsp:include>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <h1 class="text-center mb-4">Danh sách sinh viên</h1>

        <!-- Nút Thêm Sinh Viên -->
        <div class="text-right mb-3">
            <a href="student?action=new" class="btn btn-add">
                <i class="fas fa-plus"></i> Thêm sinh viên
            </a>
        </div>

        <!-- Bảng Danh Sách Sinh Viên -->
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>Tên Sinh Viên</th>
                    <th>Email</th>
                    <th>Lớp</th>
                    <th>Chi tiết</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty studentList}">
                        <c:forEach var="student" items="${studentList}">
                            <tr>
                                <td>${student.id}</td>
                                <td class="text-left">${student.name}</td> <!-- Căn trái -->
                                <td class="text-left">${student.email}</td>
                                <td>${student.className}</td>
                                <td>
                                    <!-- Nút dấu cộng mở rộng -->
                                    <span class="collapse-btn" data-toggle="collapse" 
                                          data-target="#details-${student.id}" 
                                          aria-expanded="false" aria-controls="details-${student.id}">
                                        <i class="fas fa-plus"></i>
                                    </span>
                                </td>
                            </tr>
                            <!-- Nội dung mở rộng -->
                            <tr class="collapse" id="details-${student.id}">
                                <td colspan="5" class="bg-light">
                                    <div class="action-buttons text-center">
                                        <a href="student?action=edit&id=${student.id}" class="btn btn-warning btn-sm mr-2">
                                            <i class="fas fa-edit"></i> Sửa
                                        </a>
                                        <a href="student?action=delete&id=${student.id}" class="btn btn-danger btn-sm"
                                           onclick="return confirm('Bạn có chắc muốn xóa sinh viên này không?');">
                                            <i class="fas fa-trash"></i> Xóa
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5" class="text-center">Không có sinh viên nào để hiển thị.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <!-- Footer -->
    <div class="footer">
        © 2024 Hệ thống quản lý đoàn phí
    </div>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const collapseBtns = document.querySelectorAll('.collapse-btn');

            collapseBtns.forEach(btn => {
                btn.addEventListener('click', function () {
                    const icon = this.querySelector('i');
                    if (icon.classList.contains('fa-plus')) {
                        icon.classList.remove('fa-plus');
                        icon.classList.add('fa-minus');
                    } else {
                        icon.classList.remove('fa-minus');
                        icon.classList.add('fa-plus');
                    }
                });
            });
        });
    </script>
</body>
</html>
