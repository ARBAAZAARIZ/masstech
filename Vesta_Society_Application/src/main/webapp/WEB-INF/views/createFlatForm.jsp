<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Flat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
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
        .form-check-label {
            font-weight: 500;
            color: #4a4a4a;
        }
        .btn-success {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 30px;
        }
        .btn-secondary {
            background-color: #d3c0c0;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 30px;
        }
        .alert-danger {
            border-radius: 12px;
            font-weight: 500;
        }
    </style>
</head>
<body>

<%@ include file="../partials/navbar.jsp" %>

<div class="main-content">
    <h3 class="mb-4 text-center">Select Society</h3>

    <!-- Society Selection Form -->
    <div class="form-container mb-5">
        <form action="FlatCrudServlet" method="get">
            <input type="hidden" name="action" value="create" />
            <div class="mb-3">
                <label class="form-label">Choose Society</label>
                <select name="societyId" class="form-select" required>
                    <option value="">-- Select Society --</option>
                    <c:forEach var="s" items="${societyList}">
                        <option value="${s.societyId}" <c:if test="${s.societyId == selectedSocietyId}">selected</c:if>>
                            ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-success">
                    <i class="bi bi-arrow-right-circle me-1"></i> Load Buildings
                </button>
            </div>
        </form>
    </div>

    <!-- Flat Creation Form -->
    <c:if test="${not empty buildingList}">
        <h3 class="mb-4 text-center">Add New Flat</h3>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                <strong>Error:</strong> ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <div class="form-container">
            <form action="FlatCrudServlet" method="post">
                <input type="hidden" name="action" value="saveCreate" />
                <input type="hidden" name="societyId" value="${selectedSocietyId}" />

                <!-- Building Dropdown -->
                <div class="mb-3">
                    <label class="form-label">Select Building</label>
                    <select name="buildingId" class="form-select" required>
                        <option value="">-- Choose Building --</option>
                        <c:forEach var="b" items="${buildingList}">
                            <option value="${b.buildingId}">${b.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Flat No -->
                <div class="mb-3">
                    <label class="form-label">Flat Number</label>
                    <input type="text" name="flatNo" class="form-control" placeholder="e.g. A-101" required />
                </div>

                <!-- Floor No -->
                <div class="mb-3">
                    <label class="form-label">Floor Number</label>
                    <input type="number" name="floorNo" class="form-control" min="0" required />
                </div>

                <!-- Carpet Area -->
                <div class="mb-3">
                    <label class="form-label">Carpet Area (sqft)</label>
                    <input type="number" step="0.01" name="carpetAreaSqft" class="form-control" required />
                </div>

                <!-- Parking -->
                <div class="form-check mb-4">
                    <input class="form-check-input" type="checkbox" name="isParkingAllocated" id="parkingCheck">
                    <label class="form-check-label" for="parkingCheck">Parking Allocated</label>
                </div>

                <!-- Buttons -->
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-success me-3">
                        <i class="bi bi-plus-circle me-1"></i> Create Flat
                    </button>
                    <a href="FlatCrudServlet?action=view" class="btn btn-secondary">
                        <i class="bi bi-x-circle me-1"></i> Cancel
                    </a>
                </div>
            </form>
        </div>
    </c:if>
</div>

<%@ include file="../partials/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
