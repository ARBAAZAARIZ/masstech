<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Assign Parking</title>
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

    .form-section {
      background-color: #E5E0D8;
      padding: 2rem;
      border-radius: 16px;
      max-width: 600px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
    }

    h4 {
      color: #748873;
      font-weight: 600;
      text-align: center;
      margin-bottom: 1.5rem;
    }

    .form-label {
      font-weight: 600;
      color: #748873;
      margin-bottom: 0.5rem;
    }

    .form-control {
      border-radius: 12px;
      border: 1px solid #D1A980;
      padding: 10px;
      font-size: 1rem;
    }

    .btn-action {
      background-color: #D1A980;
      color: #fff;
      border-radius: 30px;
      padding: 10px 24px;
      font-weight: 600;
      border: none;
      transition: background-color 0.3s ease;
    }

    .btn-action:hover {
      background-color: #b88c5f;
    }

    .btn-secondary {
      background-color: #748873;
      color: #fff;
      border-radius: 30px;
      padding: 10px 24px;
      font-weight: 600;
      border: none;
      transition: background-color 0.3s ease;
    }

    .btn-secondary:hover {
      background-color: #5c6b5d;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <div class="form-section">
    <h4>Assign Parking</h4>
    <form action="ParkingAssignmentServlet" method="post">
      <input type="hidden" name="action" value="assign" />

      <label class="form-label">Select Vehicle</label>
      <select name="vehicleId" class="form-control mb-3" required>
        <c:forEach var="v" items="${unassignedVehicles}">
          <option value="${v.vehicleId}">${v.registrationNo} (${v.type})</option>
        </c:forEach>
      </select>

      <label class="form-label">Select Slot</label>
      <select name="slotId" class="form-control mb-3" required>
        <c:forEach var="s" items="${availableSlots}">
          <option value="${s.slotId}">${s.identifier} (${s.covered ? 'Covered' : 'Open'})</option>
        </c:forEach>
      </select>

      <label class="form-label">Start Date</label>
      <input type="date" name="startDate" class="form-control mb-3" required />

      <label class="form-label">End Date (optional)</label>
      <input type="date" name="endDate" class="form-control mb-3" />

      <div class="d-flex justify-content-between">
        <a href="ParkingAssignmentServlet?action=view" class="btn btn-secondary">Cancel</a>
        <button type="submit" class="btn btn-action">Assign</button>
      </div>
    </form>
  </div>
</div>

</body>
</html>
