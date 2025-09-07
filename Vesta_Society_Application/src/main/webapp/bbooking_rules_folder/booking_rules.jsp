<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Rules</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #FFF2EF, #FFDBB6);
            font-family: 'Segoe UI', sans-serif;
        }

        .rules-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 40px;
            margin-top: 60px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        h2 {
            color: #5D688A;
            font-weight: 600;
            text-align: center;
            margin-bottom: 30px;
        }

        ul {
            list-style: none;
            padding-left: 0;
        }

        ul li {
            background-color: #fff6f3;
            margin-bottom: 12px;
            padding: 12px 18px;
            border-radius: 12px;
            font-weight: 500;
            color: #2c2c2c;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
        }

        .btn-back {
            background-color: #FFD6A5;
            border: none;
            color: #2c2c2c;
            border-radius: 30px;
            padding: 8px 20px;
            font-weight: 500;
        }

        .btn-back:hover {
            background-color: #f5c58c;
        }
    </style>
</head>
<body>
<%@ include file="../WEB-INF/partials/navbar.jsp" %>

<div class="container">
    <div class="rules-container">
        <h2>Booking Rules for Amenities</h2>
        <ul>
            <li>Bookings must be made at least 24 hours in advance.</li>
            <li>Each amenity can be booked for a maximum of 2 hours per day.</li>
            <li>Cancellation should be done at least 6 hours before the booking time.</li>
            <li>Residents must carry their society ID during amenity usage.</li>
            <li>Any damage to amenities will be charged to the booking member.</li>
            <li>Noise levels must be kept minimal during usage.</li>
            <li>Guests are allowed only if accompanied by the resident.</li>
        </ul>

        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/AmenityServlet?action=view" class="btn btn-back">
    <i class="bi bi-arrow-left-circle me-1"></i> Back to Amenities
</a>

        </div>
    </div>
</div>
</body>
</html>
