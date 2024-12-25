<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Thanh Toán</title>
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

        /* Thêm biểu tượng vào nút */
        .btn-warning i, .btn-danger i {
            margin-right: 5px;
        }

        .toggle-icon {
            transition: all 0.3s ease;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <jsp:include page="../sidebar.jsp" />

   <div class="main-content">
    <div class="container">
        <h2 class="text-center my-4">Danh sách Thanh Toán</h2>

        <!-- Nút Thêm Mới -->
        <div class="d-flex justify-content-end mb-3">
            <a href="payment?action=new" class="btn btn-add">
                <i class="fas fa-plus"></i> Thêm mới
            </a>
        </div>

        <!-- Form tìm kiếm -->
        <form action="payment?action=list" method="get" class="form-inline mb-4">
            <div class="form-group mr-3">
                <label for="semester" class="mr-2">Chọn học kỳ:</label>
                <select id="semester" name="semesterId" class="form-control">
                    <option value="">-- Chọn học kỳ --</option>
                    <c:forEach var="semester" items="${semesterList}">
                        <option value="${semester.semesterId}" 
                            <c:if test="${param.semesterId == semester.semesterId}">selected</c:if>>
                            ${semester.semesterName} - ${semester.year}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group mr-3">
                <label for="classObj" class="mr-2">Chọn lớp:</label>
                <select id="classObj" name="classId" class="form-control">
                    <option value="">-- Chọn lớp --</option>
                    <c:forEach var="classObj" items="${classList}">
                        <option value="${classObj.classId}" 
                            <c:if test="${param.classId == classObj.classId}">selected</c:if>>
                            ${classObj.className}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-search"></i> Tìm
            </button>
        </form>

        <!-- Bảng danh sách thanh toán -->
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>Mã sinh viên</th>
                    <th>Tên sinh viên</th>
                    <th>Lớp học</th>
                    <th>Học kỳ</th>
                    <th>Số Tiền</th>
                    <th>Trạng thái</th>
                    <th>Chi tiết</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty paymentList}">
                        <c:forEach var="payment" items="${paymentList}">
                            <tr>
                                <td>${payment.paymentId}</td>
                                <td>${payment.studentId}</td>
                                <td>${payment.studentName}</td>
                                <td>${payment.className}</td>
                                <td>${payment.semesterName}</td>
                                <td>
                                    <!-- Hiển thị số tiền với dấu phân cách hàng nghìn -->
                                    <fmt:formatNumber value="${payment.amount}" type="number" groupingUsed="true" /> VND
                                </td>
                                <td>${payment.status}</td>
                                <td>
                                    <!-- Nút dấu cộng mở rộng -->
                                    <button class="btn btn-info btn-sm toggle-btn" type="button" data-toggle="collapse" 
                                            data-target="#details-${payment.paymentId}" 
                                            aria-expanded="false" aria-controls="details-${payment.paymentId}">
                                        <i class="fas fa-plus toggle-icon"></i>
                                    </button>
                                </td>
                            </tr>
                                <!-- Nội dung mở rộng -->
                                <tr class="collapse" id="details-${payment.paymentId}">
                                    <td colspan="7" class="bg-light">
                                        <div class="d-flex justify-content-start action-buttons">
                                            <a href="payment?action=edit&id=${payment.paymentId}" 
                                               class="btn btn-warning btn-sm mr-2">
                                                <i class="fas fa-edit"></i> Sửa
                                            </a>
                                            <a href="payment?action=delete&id=${payment.paymentId}" 
                                               class="btn btn-danger btn-sm"
                                               onclick="return confirm('Bạn có chắc muốn xóa không?');">
                                                <i class="fas fa-trash"></i> Xóa
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7" class="text-center">Không có bản ghi nào được tìm thấy.</td>
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

    <!-- Script thay đổi biểu tượng + thành - -->
    <script>
        $(document).ready(function () {
            $('.toggle-btn').click(function () {
                const icon = $(this).find('.toggle-icon');
                if (icon.hasClass('fa-plus')) {
                    icon.removeClass('fa-plus').addClass('fa-minus');
                } else {
                    icon.removeClass('fa-minus').addClass('fa-plus');
                }
            });
        });
    </script>
</body>
</html>
