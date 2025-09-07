<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>

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

        .form-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 32px;
            max-width: 650px;
            margin: auto;
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

        .btn-primary {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 30px;
            transition: background 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #e68c8c;
        }

        .btn-secondary {
            background-color: #d3c0c0;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 30px;
        }

        .btn-secondary:hover {
            background-color: #bbaaaa;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <h3 class="mb-4 text-center">Update User Details</h3>

        <div class="form-container">
            <form action="UserCrudServlet" method="post">
                <!-- Hidden fields -->
                <input type="hidden" name="action" value="saveUpdate" />
                <input type="hidden" name="userId" value="${user.userID}" />
                <input type="hidden" name="memberId" value="${user.memberID}" />

                <!-- Username (read-only) -->
                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" class="form-control" value="${user.username}" readonly />
                </div>

                <!-- Role Dropdown -->
                <div class="mb-3">
                    <label for="role" class="form-label">Role</label>
                    <select name="role" class="form-select" required>
                        <option value="">Select Role</option>
                        <option value="ADMIN" <c:if test="${user.role == 'ADMIN'}">selected</c:if>>Admin</option>
                        <option value="SOCIETY MANAGER" <c:if test="${user.role == 'SOCIETY MANAGER'}">selected</c:if>>Society Manager</option>
                        <option value="SECURITY" <c:if test="${user.role == 'SECURITY'}">selected</c:if>>Security</option>
                        <option value="TREASURER" <c:if test="${user.role == 'TREASURER'}">selected</c:if>>Treasurer</option>
                        <option value="RESIDENT" <c:if test="${user.role == 'RESIDENT'}">selected</c:if>>Resident</option>
                    </select>
                </div>

                <!-- Status Dropdown -->
                <div class="mb-3">
                    <label for="status" class="form-label">Status</label>
                    <select name="status" class="form-select" required>
                        <option value="Active" <c:if test="${user.status == 'Active'}">selected</c:if>>Active</option>
                        <option value="Inactive" <c:if test="${user.status == 'Inactive'}">selected</c:if>>Inactive</option>
                    </select>
                </div>

                <!-- Society Dropdown -->
                <div class="mb-3">
                    <label for="societyID" class="form-label">Society</label>
                    <select name="societyID" class="form-select" required>
                        <option value="">Select Society</option>
                        <c:forEach var="society" items="${societyList}">
                            <option value="${society.societyId}" <c:if test="${society.societyId == user.societyId}">selected</c:if>>
                                ${society.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Buttons -->
                <div class="d-flex justify-content-center mt-4">
                    <button type="submit" class="btn btn-primary me-3">
                        <i class="bi bi-check-circle me-1"></i> Save Changes
                    </button>
                    <a href="UserCrudServlet?action=view" class="btn btn-secondary">
                        <i class="bi bi-x-circle me-1"></i> Cancel
                    </a>
                </div>
            </form>
        </div>
    </div>

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
