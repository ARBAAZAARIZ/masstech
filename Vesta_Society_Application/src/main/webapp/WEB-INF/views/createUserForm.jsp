<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    
    <style>
        body {
            background: linear-gradient(to right, #FFF2EF, #FFDBB6);
            font-family: 'Segoe UI', sans-serif;
        }

        .form-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 32px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        h3 {
            color: #5D688A;
            font-weight: 600;
        }

        label.form-label {
            font-weight: 500;
            color: #4a4a4a;
        }

        .form-control, .form-select {
            border-radius: 12px;
            border: 1px solid #d8cfcf;
            padding: 10px 14px;
            font-size: 1rem;
        }

        .form-control:focus, .form-select:focus {
            border-color: #F7A5A5;
            box-shadow: 0 0 0 0.2rem rgba(247, 165, 165, 0.25);
        }

        .btn-success {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 30px;
            transition: background 0.3s ease;
        }

        .btn-success:hover {
            background-color: #e68c8c;
        }

        .alert-danger {
            border-radius: 12px;
            font-weight: 500;
        }
    </style>
</head>
<body class="bg-light">
<%@ include file="../partials/navbar.jsp" %>

<div class="container mt-5">
    <h3 class="mb-4 text-center">Create New User</h3>

    <!-- Error Message Display -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <div class="form-container mx-auto" style="max-width: 650px;">
        <form action="UserCrudServlet" method="post">
            <input type="hidden" name="action" value="create" />

            <!-- Username -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" name="username" class="form-control" required />
            </div>
            
            <!-- Full Name -->
            <div class="mb-3">
                <label for="fullname" class="form-label">Full Name</label>
                <input type="text" name="fullname" class="form-control" required />
            </div>

            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" class="form-control" required />
            </div>

            <!-- Role Dropdown -->
            <div class="mb-3">
                <label for="role" class="form-label">Role</label>
                <select name="role" class="form-select" required>
                    <option value="">Select Role</option>
                    <option value="ADMIN">Admin</option>
                    <option value="SOCIETY MANAGER">Society Manager</option>
                    <option value="SECURITY">Security</option>
                    <option value="TREASURER">Treasurer</option>
                    <option value="RESIDENT">Resident</option>
                </select>
            </div>

            <!-- Society Dropdown -->
            <div class="mb-3">
                <label for="societyName" class="form-label">Society</label>
                <select name="societyID" class="form-select" required>
                    <option value="">Select Society</option>
                    <c:forEach var="society" items="${societyList}">
                        <option value="${society.societyId}">${society.name}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Submit Button -->
            <div class="d-flex justify-content-center mt-4">
                <button type="submit" class="btn btn-success">Create User</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
