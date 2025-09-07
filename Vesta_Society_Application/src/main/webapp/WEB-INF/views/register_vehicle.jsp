<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Vehicle</title>

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
            max-width: 650px;
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
        <h3 class="mb-4 text-center">Vehicle Registration</h3>

        <!-- Feedback Messages -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center fw-semibold">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success text-center fw-semibold">${message}</div>
        </c:if>

        <form action="VehicleServlet" method="post" class="form-container">
            <input type="hidden" name="action" value="register" />

            <!-- Building Name Dropdown -->
            <div class="mb-3">
                <label class="form-label">Building Name</label>
                <select name="buildingName" class="form-select" required>
                    <option value="">-- Select Building --</option>
                    <c:forEach var="b" items="${buildingList}">
                        <option value="${b.name}">${b.name}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Flat Number Dropdown -->
            <div class="mb-3">
                <label class="form-label">Flat Number</label>
                <select name="flatNo" class="form-select" required>
                    <option value="">-- Select Flat --</option>
                    <c:forEach var="f" items="${flatList}">
                        <option value="${f.flatNo}">${f.flatNo}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Username -->
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" placeholder="Registered member username" required />
            </div>

            <!-- Registration Number -->
            <div class="mb-3">
                <label class="form-label">Vehicle Registration No</label>
                <input type="text" name="registrationNo" class="form-control" placeholder="e.g. MH12AB1234" required />
            </div>

            <!-- Vehicle Type -->
            <div class="mb-3">
                <label class="form-label">Vehicle Type</label>
                <select name="type" class="form-select" required>
                    <option value="">-- Select Type --</option>
                    <option value="TwoWheeler">Two Wheeler</option>
                    <option value="FourWheeler">Four Wheeler</option>
                    <option value="Other">Other</option>
                </select>
            </div>

            <!-- Buttons -->
            <div class="d-flex justify-content-center mt-4">
                <button type="submit" class="btn btn-success me-3">
                    <i class="bi bi-check-circle me-1"></i> Register Vehicle
                </button>
                <a href="VehicleServlet" class="btn btn-secondary">
                    <i class="bi bi-x-circle me-1"></i> Cancel
                </a>
            </div>
        </form>
    </div>

    <%@ include file="../partials/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
