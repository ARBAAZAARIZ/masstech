<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Society Manager Dashboard</title>

  <!-- Bootstrap & Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Pastel Styling -->
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
      text-align: center;
      margin-bottom: 2rem;
    }

    .card {
      background-color: #E5E0D8;
      border-radius: 16px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
      transition: transform 0.2s ease;
    }

    .card:hover {
      transform: scale(1.02);
    }

    .card-body i {
      font-size: 2rem;
      color: #748873;
    }

    .card-title {
      font-weight: 600;
      color: #2c2c2c;
    }

    .btn-access {
      background-color: #D1A980;
      border: none;
      border-radius: 30px;
      padding: 6px 16px;
      font-weight: 500;
      color: #fff;
    }

    .btn-access:hover {
      background-color: #b88c5f;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/societyManagerNavbar.jsp" %>

  <div class="main-content">
    <h2>Welcome, Society Manager</h2>

    <div class="row g-4">
      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-people-fill"></i>
            <h5 class="card-title mt-2">View Users</h5>
            <a href="UserServlet?action=view" class="btn btn-access mt-2">Manage Users</a>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-house-door-fill"></i>
            <h5 class="card-title mt-2">Assign Flats</h5>
            <a href="AssignFlatServlet?action=view" class="btn btn-access mt-2">Assign Now</a>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-truck"></i>
            <h5 class="card-title mt-2">Assign Parking to USer's</h5>
            <a href="ParkingAssignmentServlet?action=view" class="btn btn-access mt-2">View Vehicle Assigments</a>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-grid-3x3-gap-fill"></i>
            <h5 class="card-title mt-2">View Parking Mapping</h5>
            <a href="ViewParkingMap" class="btn btn-access mt-2">Map Slots</a>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-calendar-check-fill"></i>
            <h5 class="card-title mt-2">Amenity Approvals</h5>
            <a href="ViewAmenityServlet?action=view" class="btn btn-access mt-2">Approve Bookings</a>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-chat-left-text-fill"></i>
            <h5 class="card-title mt-2">Complaints</h5>
            <a href="ViewComplaints?action=view" class="btn btn-access mt-2">Resolve Issues</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
