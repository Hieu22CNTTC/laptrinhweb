<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách lớp học</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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

        .action-buttons .btn {
            font-size: 14px;
            padding: 5px 10px;
        }

        .btn-add {
            background-color: #28a745;
            color: white;
        }

        .btn-add:hover {
            background-color: #218838;
            color: white;
        }

        .collapse-btn {
            cursor: pointer;
            color: #007bff;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <%@ include file="../sidebar.jsp" %>

    <!-- Main Content -->
    <div class="main-content">
        <div class="container">
            <h1 class="text-center">Danh sách lớp học</h1>
            <div class="text-right mb-3">
                <!-- Nút Thêm Lớp Học -->
                <a href="class?action=new" class="btn btn-add">
                    <i class="fas fa-plus"></i> Thêm lớp học
                </a>
            </div>

            <!-- Bảng Danh Sách -->
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th>#</th>
                        <th>Tên Lớp</th>
                        <th>Mô Tả</th>
                        <th>Chi tiết</th>
                       
                    </tr>
                </thead>
  <tbody>
    <c:choose>
        <c:when test="${not empty classList}">
            <c:forEach var="clazz" items="${classList}">
                <tr>
                    <td>${clazz.classId}</td>
                    <td>${clazz.className}</td>
                    <td>${clazz.description}</td>
                    <td>
                        <!-- Nút dấu cộng mở rộng -->
                        <span class="collapse-btn" data-toggle="collapse" data-target="#details-${clazz.classId}" 
                              aria-expanded="false" aria-controls="details-${clazz.classId}">
                            <i class="fas fa-plus"></i>
                        </span>
                    </td>
                    
                </tr>
                <!-- Nội dung mở rộng -->
                <tr class="collapse" id="details-${clazz.classId}">
                    <td colspan="5" class="bg-light">
                        <div class="action-buttons">
                            <a href="class?action=edit&id=${clazz.classId}" class="btn btn-warning btn-sm">
                                <i class="fas fa-edit"></i> Sửa
                            </a>
                            <a href="class?action=delete&id=${clazz.classId}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Bạn có chắc muốn xóa lớp học này không?');">
                                <i class="fas fa-trash"></i> Xóa
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="5" class="text-center">Không có lớp học nào để hiển thị.</td>
            </tr>
        </c:otherwise>
    </c:choose>
</tbody>
               
            </table>
        </div>
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
