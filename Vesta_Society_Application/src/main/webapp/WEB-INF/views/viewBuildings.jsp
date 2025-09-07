<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Buildings</title>

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

        .btn-danger {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 6px 14px;
            border-radius: 20px;
        }

        .btn-danger:hover {
            background-color: #e68c8c;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold">Building List</h2>
            <a href="BuildingCrudServlet?action=create" class="btn btn-success">
                <i class="bi bi-house-add me-2"></i> Add New Building
            </a>
        </div>

        <div class="table-container">
            <table class="table table-bordered table-hover text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Society ID</th>
                        <th>Name</th>
                        <th>Floors</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="b" items="${buildingList}">
                        <tr>
                            <td>${b.buildingId}</td>
                            <td>${b.societyId}</td>
                            <td>${b.name}</td>
                            <td>${b.floors}</td>
                            <td>${b.createdAt}</td>
                            <td>
                                <a href="BuildingCrudServlet?action=delete&buildingId=${b.buildingId}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Are you sure you want to delete this building?');">
                                    <i class="bi bi-trash-fill"></i> Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
