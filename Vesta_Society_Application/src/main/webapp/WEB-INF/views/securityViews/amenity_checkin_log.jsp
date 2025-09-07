<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Amenity Check-In</title>

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

    .form-container {
      background-color: #fbeae7;
      border-radius: 16px;
      padding: 30px;
      max-width: 600px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0,0,0,0.08);
    }

    h3 {
      text-align: center;
      color: #2C3E50;
      margin-bottom: 20px;
      font-weight: 700;
    }

    .form-label {
      font-weight: 600;
      color: #2C3E50;
    }

    .form-control, .form-select {
      border-radius: 12px;
      border: 1px solid #d8cfcf;
    }

    .btn-submit {
      background-color: #A8D5BA;
      color: #2C3E50;
      border-radius: 30px;
      font-weight: 600;
      padding: 10px 24px;
      border: none;
      transition: background 0.3s ease;
      width: 100%;
    }

    .btn-submit:hover {
      background-color: #8fc9a9;
    }

    .alert {
      border-radius: 12px;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/securityNavbar.jsp" %>

  <div class="main-content">
    <div class="form-container">
      <h3>Amenity Check-In</h3>

      <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-success">${sessionScope.message}</div>
        <c:remove var="message" scope="session"/>
      </c:if>
      <c:if test="${not empty sessionScope.error}">
        <div class="alert alert-danger">${sessionScope.error}</div>
        <c:remove var="error" scope="session"/>
      </c:if>

      <form action="AmenityCheckinServlet" method="post">
        <div class="mb-3">
          <label class="form-label">Society</label>
          <input type="text" class="form-control" value="${societyName}" readonly />
        </div>

        <div class="mb-3">
          <label class="form-label">Amenity</label>
          <select name="amenityId" class="form-select" required>
            <option value="">-- Select Amenity --</option>
            <c:forEach var="a" items="${amenities}">
              <option value="${a.amenityId}">${a.name}</option>
            </c:forEach>
          </select>
        </div>

        <div class="mb-3">
          <label class="form-label">Member ID</label>
          <input type="number" name="memberId" class="form-control" required />
        </div>

        <div class="mb-3">
          <label class="form-label">Remarks</label>
          <textarea name="remarks" class="form-control" placeholder="Optional remarks from member"></textarea>
        </div>

        <button type="submit" class="btn-submit">Log Check-In</button>
      </form>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
