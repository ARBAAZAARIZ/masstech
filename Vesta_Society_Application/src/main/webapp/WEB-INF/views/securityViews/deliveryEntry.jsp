<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Delivery Entry</title>

  <!-- Bootstrap & Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Page Styling -->
  <style>
    body {
      background: linear-gradient(to right, #FFF2EF, #FFDBB6);
      font-family: 'Segoe UI', sans-serif;
      margin: 0;
    }

    .main-content {
      margin-left: 270px; /* offset for sidebar */
      padding: 2rem;
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

    .form-container {
      background-color: #fbeae7;
      border-radius: 16px;
      padding: 30px;
      margin: auto;
      margin-top: 40px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
      max-width: 700px;
    }

    .form-label {
      font-weight: 500;
      color: #2c2c2c;
    }

    .form-control, .form-select {
      border-radius: 12px;
      border: 1px solid #d8cfcf;
      box-shadow: none;
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
      margin-bottom: 20px;
    }

    .alert-success {
      background-color: #d4edda;
      color: #155724;
    }

    .alert-danger {
      background-color: #f8d7da;
      color: #721c24;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/securityNavbar.jsp" %>

  <div class="main-content">
    <form action="DeliveryEntryServlet" method="post" class="form-container w-100">
      <h3>Delivery Entry Form</h3>
      <div class="society-name">Society: <strong>${society.name}</strong></div>

      <c:if test="${not empty message}">
        <div class="alert alert-success text-center">${message}</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
      </c:if>

      <input type="hidden" name="societyId" value="${society.societyId}" />

      <div class="mb-3">
        <label class="form-label">Delivery Person Name</label>
        <input type="text" name="deliveryPersonName" class="form-control" required />
      </div>

      <div class="mb-3">
        <label class="form-label">Mobile</label>
        <input type="text" name="mobile" class="form-control" />
      </div>

      <div class="mb-3">
        <label class="form-label">Delivery Type</label>
        <input type="text" name="deliveryType" class="form-control" required />
      </div>

      <div class="mb-3">
        <label class="form-label">Select Building</label>
        <select name="buildingId" class="form-select" required>
          <c:forEach var="b" items="${buildingList}">
            <option value="${b.name}">${b.name}</option>
          </c:forEach>
        </select>
      </div>

      <div class="mb-3">
        <label class="form-label">Select Flat</label>
        <select name="flatId" class="form-select" required>
          <c:forEach var="f" items="${flatList}">
            <option value="${f.flatNo}">${f.flatNo}</option>
          </c:forEach>
        </select>
      </div>

      <div class="text-center mt-4">
        <button type="submit" class="btn btn-submit">
          <i class="bi bi-box-seam me-1"></i> Log Delivery
        </button>
      </div>
    </form>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
