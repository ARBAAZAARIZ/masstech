<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Amenity</title>

    <!-- Bootstrap -->
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

        .btn-success {
            background-color: #A8D5BA;
            border: none;
            color: #2c2c2c;
            border-radius: 30px;
            padding: 8px 20px;
        }

        .btn-success:hover {
            background-color: #8fc9a9;
        }

        .btn-secondary {
            background-color: #d3c0c0;
            border: none;
            color: #2c2c2c;
            border-radius: 30px;
            padding: 8px 20px;
        }

        .btn-secondary:hover {
            background-color: #c4b2b2;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <h3 class="mb-4 text-center">Create Amenity</h3>

        <form action="AmenityServlet" method="post" class="form-container">
            <input type="hidden" name="action" value="saveCreate" />

            <!-- Society Dropdown -->
            <div class="mb-3">
                <label class="form-label">Select Society</label>
                <select name="societyId" class="form-select" required>
                    <option value="" disabled selected>Choose a society</option>
                    <c:forEach var="s" items="${societyList}">
                        <option value="${s.societyId}">${s.name}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Amenity Name -->
            <div class="mb-3">
                <label class="form-label">Amenity Name</label>
                <input type="text" name="name" class="form-control" required />
            </div>

            <!-- Amount -->
            <div class="mb-3">
                <label class="form-label">Amount</label>
                <input type="text" name="amount" class="form-control" required />
            </div>

            <!-- Booking Required -->
            <div class="form-check mb-4">
                <input class="form-check-input" type="checkbox" name="bookingRequired" value="1" id="bookingCheck">
                <label class="form-check-label" for="bookingCheck">Booking Required</label>
            </div>

            <!-- Buttons -->
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-success me-3">
                    <i class="bi bi-check-circle me-1"></i> Create
                </button>
                <a href="AmenityServlet?action=view" class="btn btn-secondary">
                    <i class="bi bi-x-circle me-1"></i> Cancel
                </a>
            </div>
        </form>
    </div>

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
