<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Amenities</title>

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

        .table thead {
            background: linear-gradient(to right, #F7A5A5, #FFDBB6);
            font-weight: 600;
        }

        .table tbody tr:hover {
            background-color: #fff6f3;
        }

        .btn-warning {
            background-color: #FFD6A5;
            border: none;
            color: #2c2c2c;
            border-radius: 20px;
        }

        .btn-danger {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            border-radius: 20px;
        }

        .btn-success {
            background-color: #A8D5BA;
            border: none;
            color: #2c2c2c;
            border-radius: 20px;
        }

        .badge {
            font-size: 0.9rem;
            padding: 6px 12px;
            border-radius: 12px;
        }

        .badge-yes {
            background-color: #A8D5BA;
        }

        .badge-no {
            background-color: #FFD6A5;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>All Amenities</h2>
            <a href="AmenityServlet?action=create" class="btn btn-success">
                <i class="bi bi-plus-circle me-1"></i> Create Amenity
            </a>
        </div>

        <div class="table-container">
            <table class="table table-bordered table-hover text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Amount</th>
                        <th>Booking Required</th>
                        <th>Society ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${amenityList}">
                        <tr>
                            <td>${a.amenityId}</td>
                            <td>${a.name}</td>
                            <td><c:out value="${a.amount}" /></td>
                            <td>
                                <c:choose>
                                    <c:when test="${a.bookingRequired}">
                                        <span class="badge badge-yes">Yes</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-no">No</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${a.societyId}</td>
                            <td>
                                <a href="AmenityServlet?action=edit&amenityId=${a.amenityId}" class="btn btn-warning btn-sm me-2">
                                    <i class="bi bi-pencil-square"></i> Edit
                                </a>
                                <a href="AmenityServlet?action=delete&amenityId=${a.amenityId}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Are you sure you want to delete this amenity?');">
                                    <i class="bi bi-trash"></i> Delete
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
