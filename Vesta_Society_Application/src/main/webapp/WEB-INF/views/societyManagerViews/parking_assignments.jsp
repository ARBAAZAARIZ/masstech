<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Parking Assignments</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body { background-color: #F8F8F8; font-family: 'Segoe UI', sans-serif; margin: 0; }
    .main-content { margin-left: 260px; padding: 2rem; }
    .btn-action { background-color: #D1A980; color: #fff; border-radius: 30px; padding: 6px 16px; font-weight: 500; border: none; }
    .btn-action:hover { background-color: #b88c5f; }
    .table-wrapper { background-color: #E5E0D8; padding: 1rem; border-radius: 16px; box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06); }
    .pastel-table thead { background-color: #D1A980; color: #fff; font-weight: 600; }
    .pastel-table tbody tr:hover { background-color: #fdf6f0; }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2 class="mb-0">Parking Assignments</h2>
    <a href="ParkingAssignmentServlet?action=init" class="btn btn-action">Assign Parking</a>
  </div>

  <div class="table-wrapper">
    <table class="table pastel-table table-bordered table-hover text-center">
      <thead>
        <tr>
          <th>Vehicle ID</th>
          <th>Member Name</th>
          <th>Flat No</th>
          <th>Type</th>
          <th>Society ID</th>
          <th>Slot Identifier</th>
          <th>Covered</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="p" items="${parkingList}">
          <tr>
            <td>${p.vehicleId}</td>
            <td>${p.memberFullName}</td>
            <td>${p.flatNo}</td>
            <td>${p.type}</td>
            <td>${p.societyId}</td>
            <td>${p.identifier}</td>
            <td><c:out value="${p.covered ? 'Yes' : 'No'}" /></td>
            <td>${p.startDate}</td>
            <td>${p.endDate}</td>
            <td>
              <a href="ParkingAssignmentServlet?action=delete&vehicleId=${p.vehicleId}" class="btn btn-sm btn-danger">Delete</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
