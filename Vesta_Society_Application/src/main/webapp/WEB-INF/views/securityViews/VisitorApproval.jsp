<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Visitor Approval Form</title>
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
            padding: 30px;
            margin-top: 40px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
            max-width: 700px;
        }

        h3 {
            color: #2C3E50;
            font-weight: 600;
            text-align: center;
            margin-bottom: 10px;
        }

        .society-name {
            text-align: center;
            font-size: 1.2rem;
            font-weight: 500;
            color: #5D688A;
            margin-bottom: 20px;
        }

        .form-label {
            font-weight: 500;
            color: #2c2c2c;
        }

        .form-control, .form-select {
            border-radius: 12px;
        }

        .btn-submit {
            background-color: #A8D5BA;
            border: none;
            border-radius: 30px;
            padding: 8px 20px;
            font-weight: 500;
            color: #2c2c2c;
        }

        .btn-submit:hover {
            background-color: #8fc9a9;
        }

        .alert {
            border-radius: 12px;
            font-weight: 500;
        }
    </style>
</head>
<body>
<%@ include file="../../partials/securityNavbar.jsp" %>

<div class="container d-flex justify-content-center">
    <div class="form-container w-100">
        <h3>Visitor Approval Form</h3>

        <div class="society-name">
            Society: <strong>${society.name}</strong>
        </div>

        <!-- Feedback Messages -->
        <c:if test="${not empty message}">
            <div class="alert alert-success text-center">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>

        <!-- Step 1: Building Selection -->
        <c:if test="${empty selectedBuilding}">
            <form action="VisitorEntryApprovalServlet" method="get">
                <input type="hidden" name="action" value="selectBuilding" />
                <div class="mb-3">
                    <label class="form-label">Select Building</label>
                    <select name="buildingId" class="form-select" required>
                        <option value="" disabled selected>Select building</option>
                        <c:forEach var="b" items="${buildingList}">
                            <option value="${b.buildingId}">${b.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-submit">
                        <i class="bi bi-arrow-right-circle me-1"></i> Next
                    </button>
                </div>
            </form>
        </c:if>

        <!-- Step 2: Visitor Details -->
        <c:if test="${not empty selectedBuilding}">
            <form action="VisitorEntryApprovalServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="societyId" value="${society.societyId}" />
                <input type="hidden" name="buildingId" value="${selectedBuilding}" />

                <div class="mb-3">
                    <label class="form-label">Visitor Name</label>
                    <input type="text" name="visitorName" class="form-control" />
                </div>

                

                <div class="mb-3">
                    <label class="form-label">Purpose <span class="text-danger">*</span></label>
                    <input type="text" name="purpose" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label class="form-label">Select Flat</label>
                    <select name="flatId" class="form-select" required>
                        <option value="" disabled selected>Select flat</option>
                        <c:forEach var="f" items="${flatList}">
                            <option value="${f.flatId}">${f.flatNo}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Visitor Photo</label>
                    <input type="file" name="visitorPhoto" class="form-control" accept="image/*" />
                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-submit">
                        <i class="bi bi-check-circle me-1"></i> Log Entry
                    </button>
                </div>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
