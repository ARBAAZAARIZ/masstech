<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Security Dashboard</title>

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

    h2 {
      color: #2C3E50;
      font-weight: 600;
      margin-bottom: 2rem;
      text-align: center;
    }

    .card {
      border-radius: 16px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
      transition: transform 0.2s ease;
      background-color: #fbeae7;
    }

    .card:hover {
      transform: scale(1.02);
    }

    .card-body i {
      font-size: 2rem;
      color: #4CA1AF;
    }

    .card-title {
      font-weight: 600;
      color: #2c2c2c;
    }

    .btn-access {
      background-color: #A8D5BA;
      border: none;
      border-radius: 30px;
      padding: 6px 16px;
      font-weight: 500;
      color: #2c2c2c;
    }

    .btn-access:hover {
      background-color: #8fc9a9;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/securityNavbar.jsp" %>

  <div class="main-content">
    <h2>Welcome, Security Guard</h2>

    <div class="row g-4">
      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-person-check-fill"></i>
            <h5 class="card-title mt-2">Vender Visitor Entry</h5>
            <a href="VisitorEntryServlet" class="btn btn-access mt-2">Open Form</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-box-seam"></i>
            <h5 class="card-title mt-2">Delivery Entry</h5>
            <a href="DeliveryEntryServlet" class="btn btn-access mt-2">Open Form</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-truck-front"></i>
            <h5 class="card-title mt-2">Vehicle Log</h5>
            <a href="vehicleLog.jsp" class="btn btn-access mt-2">Open Form</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-door-open"></i>
            <h5 class="card-title mt-2">Amenity Check-in</h5>
            <a href="AmenityCheckinServlet" class="btn btn-access mt-2">Open Form</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-center p-3">
          <div class="card-body">
            <i class="bi bi-exclamation-triangle-fill"></i>
            <h5 class="card-title mt-2">Incident Report</h5>
            <a href="IncidentNotificationServlet" class="btn btn-access mt-2">Open Form</a>
          </div>
        </div>
      </div>
      
      <div class="col-md-4">
  		<div class="card text-center p-3">
    		  <div class="card-body">
      		<i class="bi bi-person-badge"></i>
      		<h5 class="card-title mt-2">Visitor Approval</h5>
      		<a href="VisitorEntryApprovalServlet" class="btn btn-access mt-2">Open Form</a>
    		</div>
  	   </div>
	</div>

      
    </div>
  </div>

 

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
