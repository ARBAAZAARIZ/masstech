<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Delivery Logs</title>

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
      margin-left: 270px;
      padding: 2rem;
    }

    h2 {
      color: #2C3E50;
      font-weight: 600;
      text-align: center;
      margin-bottom: 30px;
    }

    .table-container {
      background-color: #fbeae7;
      border-radius: 16px;
      padding: 24px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
    }

    .table thead {
      background: linear-gradient(to right, #F7A5A5, #FFDBB6);
      color: #2c2c2c;
      font-weight: 600;
      text-align: center;
    }

    .table tbody tr:hover {
      background-color: #fff6f3;
    }

    .table-responsive {
      border-radius: 12px;
      overflow-x: auto;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/securityNavbar.jsp" %>

  <div class="main-content">
    <h2>Delivery Log for ${authUser.fullName}'s Society</h2>

    <div class="table-container">
      <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle text-center">
          <thead>
            <tr>
              <th>Delivery Person</th>
              <th>Mobile</th>
              <th>Type</th>
              <th>Building</th>
              <th>Flat</th>
              <th>Check-In</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="log" items="${deliveryLogs}">
              <tr>
                <td>${log.deliveryPersonName}</td>
                <td>${log.mobile}</td>
                <td>${log.deliveryType}</td>
                <td>${log.buildingName}</td>
                <td>${log.flatNo}</td>
                <td>${log.checkIn}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
