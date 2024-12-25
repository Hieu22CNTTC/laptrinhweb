<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách các khoản phí</title>
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

        .btn-add {
            background-color: #28a745;
            color: white;
            border-radius: 5px;
        }

        .btn-add:hover {
            background-color: #218838;
            color: white;
        }

        .action-buttons .btn {
            font-size: 14px;
            padding: 5px 10px;
        }

        .btn-warning i, .btn-danger i {
            margin-right: 5px;
        }

        table th, table td {
            text-align: center;
            vertical-align: middle;
        }

        .collapse-btn {
            cursor: pointer;
            color: #007bff;
            font-size: 18px;
        }

        .collapse-content {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <jsp:include page="../sidebar.jsp" />

    <!-- Main Content -->
    <div class="main-content">
        <h1 class="text-center mb-4">Danh Sách Các Khoản Phí</h1>

        <!-- Nút Thêm Khoản Phí -->
        <div class="text-right mb-3">
            <a href="fee?action=new" class="btn btn-add">
                <i class="fas fa-plus"></i> Thêm Khoản Phí
            </a>
        </div>

        <!-- Bảng Danh Sách Khoản Phí -->
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID Khoản Phí</th>
                    <th>Học Kỳ</th>
                    <th>Số Tiền</th>
                    <th>Chi tiết</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty feeList}">
                        <c:forEach var="fee" items="${feeList}">
                            <tr>
                                <td>${fee.feeId}</td>
                                <td>${fee.semesterId}</td>
                                <td>
                                    <fmt:formatNumber value="${fee.amount}" type="number" groupingUsed="true" />
                                </td>
                                <td>
                                    <!-- Dấu cộng trừ mở rộng -->
                                    <span class="collapse-btn" data-toggle="collapse" 
                                          data-target="#details-${fee.feeId}" 
                                          aria-expanded="false" aria-controls="details-${fee.feeId}">
                                        <i class="fas fa-plus"></i>
                                    </span>
                                </td>
                            </tr>
                            <!-- Nội dung mở rộng -->
                            <tr class="collapse collapse-content" id="details-${fee.feeId}">
                                <td colspan="4">
                                    <div class="d-flex justify-content-center action-buttons">
                                        <a href="fee?action=edit&id=${fee.feeId}" class="btn btn-warning btn-sm mr-2">
                                            <i class="fas fa-edit"></i> Sửa
                                        </a>
                                        <a href="fee?action=delete&id=${fee.feeId}" 
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Bạn có chắc muốn xóa khoản phí này không?');">
                                            <i class="fas fa-trash"></i> Xóa
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="4" class="text-center">Không có khoản phí nào để hiển thị.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
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
