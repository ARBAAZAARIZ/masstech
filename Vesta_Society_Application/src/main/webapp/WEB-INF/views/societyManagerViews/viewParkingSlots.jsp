<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Parking Slots</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
  <style>
    body {
      background-color: #F8F8F8;
      font-family: 'Segoe UI', sans-serif;
      margin: 0;
    }

    .main-content {
      margin-left: 260px;
      padding: 2rem;
    }

    h2 {
      color: #748873;
      font-weight: 600;
      margin-bottom: 2rem;
      text-align: center;
    }

    .table-wrapper {
      background-color: #E5E0D8;
      padding: 1rem;
      border-radius: 16px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
    }

    .pastel-table {
      border-collapse: separate;
      border-spacing: 0;
      width: 100%;
      border-radius: 12px;
      overflow: hidden;
    }

    .pastel-table thead {
      background-color: #D1A980;
      color: #fff;
      font-weight: 600;
    }

    .pastel-table th, .pastel-table td {
      padding: 12px;
      vertical-align: middle;
      border: 1px solid #ddd;
      text-align: center;
    }

    .pastel-table tbody tr:hover {
      background-color: #fdf6f0;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <h2>Parking Slots in Your Society</h2>

  <c:if test="${empty parkingSlotlist}">
    <div class="alert alert-warning text-center">No parking slots have been created yet.</div>
  </c:if>

  <c:if test="${not empty parkingSlotlist}">
    <div class="table-wrapper">
      <table class="table pastel-table table-bordered table-hover">
        <thead>
          <tr>
            <th>Slot ID</th>
            <th>Identifier</th>
            <th>Covered</th>
            <th>Society ID</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="slot" items="${parkingSlotlist}">
            <tr>
              <td>${slot.slotId}</td>
              <td>${slot.identifier}</td>
              <td><c:out value="${slot.covered ? 'Yes' : 'No'}" /></td>
              <td>${slot.societyId}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>
</div>

</body>
</html>
