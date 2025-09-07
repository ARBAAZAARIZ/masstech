<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Visitor Log</title>

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

    .badge.bg-warning {
      background-color: #FFD6A5;
      color: #2c2c2c;
      font-weight: 500;
      padding: 6px 12px;
      border-radius: 12px;
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
    <h2>Visitor Log for ${authUser.fullName}'s Society</h2>

    <c:if test="${not empty sessionScope.message}">
      <div class="alert alert-success text-center">${sessionScope.message}</div>
      <c:remove var="message" scope="session"/>
    </c:if>
    <c:if test="${not empty sessionScope.error}">
      <div class="alert alert-danger text-center">${sessionScope.error}</div>
      <c:remove var="error" scope="session"/>
    </c:if>

    <div class="table-container">
      <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle text-center">
          <thead>
            <tr>
              <th>Visitor Name</th>
              <th>Vehicle No</th>
              <th>Purpose</th>
              <th>Building</th>
              <th>Flat</th>
              <th>Check-In</th>
              <th>Check-Out</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="log" items="${visitorLogs}">
              <tr>
                <td>${log.visitorName}</td>
                <td>${log.vehicleNo}</td>
                <td>${log.purpose}</td>
                <td>${log.buildingName}</td>
                <td>${log.flatNo}</td>
                <td>${log.checkIn}</td>
                <td>
                  <c:choose>
                    <c:when test="${log.checkOut != null}">
                      ${log.checkOut}
                    </c:when>
                    <c:otherwise>
                      <a href="VisitorLogServlet?logId=${log.gateLogId}&action=update" class="btn btn-sm btn-warning">
                        <i class="bi bi-box-arrow-right me-1"></i> Check Out
                      </a>
                    </c:otherwise>
                  </c:choose>
                </td>
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
