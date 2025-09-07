<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Complaints</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
      margin-bottom: 1.5rem;
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

    .status-form select {
      border-radius: 12px;
      padding: 6px;
      border: 1px solid #D1A980;
    }

    .btn-update {
      background-color: #748873;
      color: #fff;
      border-radius: 20px;
      padding: 6px 14px;
      font-weight: 500;
      border: none;
      transition: background-color 0.3s ease;
    }

    .btn-update:hover {
      background-color: #5c6b5d;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <h2>Complaints</h2>

  <c:if test="${empty complaintList}">
    <div class="alert alert-warning text-center">No complaints found for this society.</div>
  </c:if>

  <c:if test="${not empty complaintList}">
    <div class="table-wrapper">
      <table class="table pastel-table table-bordered table-hover text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>Society</th>
            <th>User</th>
            <th>Flat</th>
            <th>Category</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Update</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="c" items="${complaintList}">
            <tr>
              <td>${c.complaintId}</td>
              <td>${c.societyId}</td>
              <td>${c.userId} - ${c.userName}</td>
              <td>${c.flatId} - ${c.flatNo}</td>
              <td>${c.category}</td>
              <td>${c.title}</td>
              <td>${c.description}</td>
              <td>${c.status}</td>
              <td>
                <form action="ViewComplaints" method="get" class="status-form d-flex justify-content-center">
                  <input type="hidden" name="action" value="update" />
                  <input type="hidden" name="complaintId" value="${c.complaintId}" />
                  <select name="status" class="form-select me-2" required>
                    <option value="Open" ${c.status == 'Open' ? 'selected' : ''}>Open</option>
                    <option value="In Progress" ${c.status == 'In Progress' ? 'selected' : ''}>In Progress</option>
                    <option value="Resolved" ${c.status == 'Resolved' ? 'selected' : ''}>Resolved</option>
                    <option value="Closed" ${c.status == 'Closed' ? 'selected' : ''}>Closed</option>
                  </select>
                  <button type="submit" class="btn btn-update">Update</button>
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>
</div>

</body>
</html>
