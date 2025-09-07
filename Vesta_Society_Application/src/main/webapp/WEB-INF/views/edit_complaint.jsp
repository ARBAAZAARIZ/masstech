<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Complaint Status</title>

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

        h3 {
            color: #5D688A;
            font-weight: 600;
        }

        .form-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 30px;
            max-width: 600px;
            margin: auto;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        .form-label {
            font-weight: 500;
            color: #2c2c2c;
        }

        .form-select {
            border-radius: 12px;
            border: 1px solid #d8cfcf;
        }

        .btn-success {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            border-radius: 30px;
            padding: 8px 20px;
        }

        .btn-success:hover {
            background-color: #e68c8c;
        }

        .btn-secondary {
            background-color: #d3c0c0;
            border: none;
            color: #2c2c2c;
            border-radius: 30px;
            padding: 8px 20px;
        }

        .btn-secondary:hover {
            background-color: #bbaaaa;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <h3 class="mb-4 text-center">Update Complaint Status</h3>

        <form action="ComplaintServlet" method="post" class="form-container">
            <input type="hidden" name="action" value="updateStatus" />
            <input type="hidden" name="complaintId" value="${complaint.complaintId}" />

            <!-- Complaint Title -->
            <div class="mb-3">
                <label class="form-label">Complaint Title</label>
                <input type="text" class="form-control" value="${complaint.title}" readonly />
            </div>

            <!-- Status Dropdown -->
            <div class="mb-3">
                <label class="form-label">Current Status</label>
                <select name="status" class="form-select" required>
                    <option value="Open" <c:if test="${complaint.status == 'Open'}">selected</c:if>>Open</option>
                    <option value="In Progress" <c:if test="${complaint.status == 'In Progress'}">selected</c:if>>In Progress</option>
                    <option value="Resolved" <c:if test="${complaint.status == 'Resolved'}">selected</c:if>>Resolved</option>
                    <option value="Closed" <c:if test="${complaint.status == 'Closed'}">selected</c:if>>Closed</option>
                </select>
            </div>

            <!-- Buttons -->
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-success me-3">
                    <i class="bi bi-check-circle me-1"></i> Update
                </button>
                <a href="ComplaintServlet?action=view" class="btn btn-secondary">
                    <i class="bi bi-x-circle me-1"></i> Cancel
                </a>
            </div>
        </form>
    </div>

   

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
