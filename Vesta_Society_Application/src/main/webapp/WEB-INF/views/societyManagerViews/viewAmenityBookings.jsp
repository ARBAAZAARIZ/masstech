<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Amenity Bookings</title>
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

    .btn-sm.btn-danger {
      border-radius: 20px;
      padding: 6px 14px;
      font-weight: 500;
      font-size: 0.9rem;
      background-color: #dc3545;
      border: none;
      transition: background-color 0.3s ease;
    }

    .btn-sm.btn-danger:hover {
      background-color: #b02a37;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <h2>Amenity Bookings</h2>

  <c:if test="${empty bookingList}">
    <div class="alert alert-warning text-center">No bookings found for this society.</div>
  </c:if>

  <c:if test="${not empty bookingList}">
    <div class="table-wrapper">
      <table class="table pastel-table table-bordered table-hover text-center">
        <thead>
          <tr>
            <th>Booking ID</th>
            <th>Amenity</th>
            <th>User ID</th>
            <th>Amount</th>
            <th>Start</th>
            <th>End</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="b" items="${bookingList}">
            <tr>
              <td>${b.bookingId}</td>
              <td>${b.amenityName}</td>
              <td>${b.userId}</td>
              <td>₹${b.amount}</td>
              <td>${b.startTime}</td>
              <td>${b.endTime}</td>
              <td>${b.status}</td>
              <td>
                <c:if test="${b.status == 'Booked' || b.status == 'Pending'}">
 				 <a href="ViewAmenityServlet?action=cancel&bookingId=${b.bookingId}" class="btn btn-sm btn-danger">Cancel</a>
				</c:if>
				
				<c:if test="${b.status == 'Cancelled'}">
 				 <a href="ViewAmenityServlet?action=book&bookingId=${b.bookingId}" class="btn btn-sm btn-success">Book</a>
				</c:if>

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
