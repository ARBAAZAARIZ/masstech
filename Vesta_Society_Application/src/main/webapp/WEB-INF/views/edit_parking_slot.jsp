<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Parking Slot</title>

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

        .form-control, .form-select {
            border-radius: 12px;
            border: 1px solid #d8cfcf;
        }

        .form-check-input {
            border: 1px solid #d8cfcf;
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

        .btn-secondary {
            background-color: #d3c0c0;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 8px 20px;
            border-radius: 30px;
        }

        .btn-secondary:hover {
            background-color: #bbaaaa;
        }

        .alert {
            border-radius: 12px;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <h3 class="mb-4 text-center">Edit Parking Slot</h3>

        <!-- Feedback Messages -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center fw-semibold">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success text-center fw-semibold">${message}</div>
        </c:if>

        <form action="ParkingSlotServlet" method="post" class="form-container">
            <input type="hidden" name="action" value="saveUpdate" />
            <input type="hidden" name="slotId" value="${slot.slotId}" />

            <!-- Society Dropdown -->
            <div class="mb-3">
                <label class="form-label">Select Society</label>
                <select name="societyId" class="form-select" required>
                    <option value="">-- Choose Society --</option>
                    <c:forEach var="s" items="${societyList}">
                        <option value="${s.societyId}" <c:if test="${s.societyId == slot.societyId}">selected</c:if>>${s.name}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Identifier -->
            <div class="mb-3">
                <label class="form-label">Slot Identifier</label>
                <input type="text" name="identifier" class="form-control" value="${slot.identifier}" required />
            </div>

            <!-- Covered Checkbox -->
            <div class="form-check mb-4">
                <input class="form-check-input" type="checkbox" name="isCovered" value="1" id="coveredCheck"
                       <c:if test="${slot.covered}">checked</c:if>>
                <label class="form-check-label" for="coveredCheck">Covered Parking</label>
            </div>

            <!-- Buttons -->
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-success me-3">
                    <i class="bi bi-check-circle me-1"></i> Update Slot
                </button>
                <a href="ParkingSlotServlet?action=view" class="btn btn-secondary">
                    <i class="bi bi-x-circle me-1"></i> Cancel
                </a>
            </div>
        </form>
    </div>

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
