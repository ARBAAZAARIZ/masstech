<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registered Vehicles</title>

    <!-- Bootstrap & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Page Styling -->
    <style>
        body {
            background: linear-gradient(to right, #FFF2EF, #FFDBB6);
            font-family: 'Segoe UI', sans-serif;
        }

        .main-content {
            margin-left: 270px;
            padding: 2rem;
        }

        h2 {
            color: #5D688A;
            font-weight: 600;
        }

        .table-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 24px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        .table {
            border: 2px solid #d8cfcf;
            border-radius: 12px;
            overflow: hidden;
        }

        .table thead {
            background: linear-gradient(to right, #F7A5A5, #FFDBB6);
            color: #2c2c2c;
            font-weight: 600;
            font-size: 1rem;
        }

        .table th, .table td {
            padding: 14px 16px;
            vertical-align: middle;
            border: 1px solid #d8cfcf;
        }

        .table tbody tr:hover {
            background-color: #fff6f3;
            transition: background 0.3s ease;
        }

        .btn-success {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 8px 20px;
            border-radius: 30px;
        }

        .btn-success:hover {
            background-color: #e68c8c;
        }

        .btn-warning {
            background-color: #FFD6A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 6px 14px;
            border-radius: 20px;
        }

        .btn-warning:hover {
            background-color: #f5c58c;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <!-- Header Section -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold">Registered Vehicles</h2>
            <a href="VehicleServlet?action=register" class="btn btn-success">
                <i class="bi bi-plus-circle me-2"></i> Register Vehicle
            </a>
        </div>

        <!-- Table Section -->
        <div class="table-container">
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>Vehicle ID</th>
                            <th>Registration No</th>
                            <th>Type</th>
                            <th>Flat ID</th>
                            <th>Member ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="v" items="${vehicleList}">
                            <tr>
                                <td>${v.vehicleId}</td>
                                <td>${v.registrationNo}</td>
                                <td>${v.type}</td>
                                <td>${v.flatId}</td>
                                <td>${v.memberId}</td>
                                <td>
                                    <a href="VehicleServlet?action=edit&vehicleId=${v.vehicleId}" class="btn btn-warning btn-sm">
                                        <i class="bi bi-pencil-square me-1"></i> Update Vehicle
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
