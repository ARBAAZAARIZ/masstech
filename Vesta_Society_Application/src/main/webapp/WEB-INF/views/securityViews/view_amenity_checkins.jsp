<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Amenity Check-In Logs</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(to right, #FFF2EF, #FFDBB6);
      font-family: 'Segoe UI', sans-serif;
      padding: 40px;
    }

    .log-container {
      background-color: #fff;
      border-radius: 16px;
      padding: 30px;
      max-width: 900px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0,0,0,0.08);
    }

    h3 {
      text-align: center;
      color: #2C3E50;
      margin-bottom: 30px;
      font-weight: 700;
    }

    table {
      font-size: 1rem;
    }

    th {
      background-color: #fbeae7;
      color: #2C3E50;
      font-weight: 600;
    }

    td {
      vertical-align: middle;
    }

    .btn-checkout {
      background-color: #A8D5BA;
      color: #2C3E50;
      border-radius: 20px;
      font-weight: 600;
      padding: 6px 16px;
      border: none;
      transition: background 0.3s ease;
    }

    .btn-checkout:hover {
      background-color: #8fc9a9;
    }

    .text-pending {
      color: #d35400;
      font-weight: 600;
    }

    .text-complete {
      color: #2C3E50;
      font-weight: 600;
    }
  </style>
</head>
<body>
<%@ include file="../../partials/securityNavbar.jsp" %>

<div class="log-container">
  <h3>Amenity Check-In Logs</h3>

  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th>Member ID</th>
        <th>Amenity</th>
        <th>Check-In Time</th>
        <th>Checkout</th>
        <th>Remarks</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="log" items="${logs}">
        <tr>
          <td>${log.memberId}</td>
          <td>${log.amanityName}</td>
          <td>${log.checkinTime}</td>
          <td>
            <c:choose>
              <c:when test="${log.checkoutTime == null}">
                <span class="text-pending">Pending</span>
              </c:when>
              <c:otherwise>
                <span class="text-complete">${log.checkoutTime}</span>
              </c:otherwise>
            </c:choose>
          </td>
          <td>${log.remarks}</td>
          <td>
            <c:if test="${log.checkoutTime == null}">
              <form action="AmenityCheckoutServlet" method="post">
                <input type="hidden" name="checkinId" value="${log.checkinId}" />
                <button type="submit" class="btn btn-checkout">Check Out</button>
              </form>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
