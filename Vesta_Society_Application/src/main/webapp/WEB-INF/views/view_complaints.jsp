<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Complaints</title>

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

        .btn-warning:hover {
            background-color: #f5c58c;
        }

        .badge {
            font-size: 0.9rem;
            padding: 6px 12px;
            border-radius: 12px;
        }

        .badge-open {
            background-color: #FFD6A5;
            color: #2c2c2c;
        }

        .badge-progress {
            background-color: #A5C8F7;
            color: #2c2c2c;
        }

        .badge-resolved {
            background-color: #A8D5BA;
            color: #2c2c2c;
        }

        .badge-closed {
            background-color: #d3c0c0;
            color: #2c2c2c;
        }

        .alert {
            border-radius: 12px;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <h2 class="mb-4 text-center">All Complaints</h2>

        <!-- Feedback Messages -->
        <c:if test="${not empty message}">
            <div class="alert alert-success text-center fw-semibold">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center fw-semibold">${error}</div>
        </c:if>

        <div class="table-container">
            <table class="table table-bordered table-hover text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Raised By</th>
                        <th>Flat</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${complaintList}">
                        <tr>
                            <td>${c.complaintId}</td>
                            <td>${c.title}</td>
                            <td>${c.category}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${c.status == 'Open'}">
                                        <span class="badge badge-open">Open</span>
                                    </c:when>
                                    <c:when test="${c.status == 'In Progress'}">
                                        <span class="badge badge-progress">In Progress</span>
                                    </c:when>
                                    <c:when test="${c.status == 'Resolved'}">
                                        <span class="badge badge-resolved">Resolved</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-closed">Closed</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${c.raisedByUserId}</td>
                            <td>${c.flatId}</td>
                            <td>${c.createdAt}</td>
                            <td>
                                <a href="ComplaintServlet?action=edit&complaintId=${c.complaintId}" class="btn btn-warning btn-sm">
                                    <i class="bi bi-pencil-square me-1"></i> Update Status
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
