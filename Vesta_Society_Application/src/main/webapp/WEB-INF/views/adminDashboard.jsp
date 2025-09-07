<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>

    <!-- Bootstrap & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Page Styling -->
    <style>
        body {
            background: linear-gradient(to right, #FFF2EF, #FFDBB6);
            font-family: 'Segoe UI', sans-serif;
        }

        .dashboard-header {
            background-color: #ffffff;
            border-radius: 12px;
            padding: 24px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            border-left: 6px solid #F7A5A5;
        }

        .dashboard-header h2 {
            color: #5D688A;
            font-weight: 600;
        }

        .card {
            background-color: #fbeae7;
            border: none;
            border-radius: 16px;
            transition: transform 0.2s ease;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.06);
        }

        .card:hover {
            transform: translateY(-4px);
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
        }

        .card-header {
            background-color: #e6b8b8;
            color: #2c2c2c;
            font-weight: 700;
            font-size: 1.2rem;
            text-align: center;
            padding: 1rem;
            border-radius: 16px 16px 0 0;
        }

        .card-body {
            background-color: #fff6f3;
            border-radius: 0 0 16px 16px;
        }

        .btn {
            font-weight: 500;
            letter-spacing: 0.3px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        .btn-primary, .btn-warning, .btn-danger {
            background-color: #F7A5A5;
            border: none;
            color: #4a4a4a;
        }

        .btn-success, .btn-info {
            background-color: #FFDBB6;
            border: none;
            color: #4a4a4a;
        }

        .btn-secondary {
            background-color: #D3C0C0;
            border: none;
            color: #4a4a4a;
        }

        /* Offset for sidebar */
        .main-content {
            margin-left: 270px;
            padding: 2rem;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <div class="dashboard-header text-center mb-4">
            <h2 class="mb-2">Welcome, <c:out value="${authUser.username}" /> 👋</h2>
            <p class="text-muted">Manage your society efficiently from this dashboard</p>
        </div>

        <div class="row g-4 justify-content-center">

            <!-- User & Role Management -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-header"> <i class="bi bi-person-gear me-2"></i> User & Role Management </div>
                    <div class="card-body text-center">
                        <p class="text-muted mb-4">Manage users, assign roles, and control access</p>
                        <a href="UserCrudServlet" class="btn btn-primary px-4 py-2 rounded-pill">
                            <i class="bi bi-people-fill me-2"></i> View All Users
                        </a>
                    </div>
                </div>
            </div>

            <!-- Society Setup -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-header"> <i class="bi bi-building me-2"></i> Society Setup </div>
                    <div class="card-body text-center">
                        <p class="text-muted mb-4">Configure society, buildings, and units</p>
                        <a href="SocietyCrudServlet" class="btn btn-success px-4 py-2 rounded-pill mb-2">
                            <i class="bi bi-house-door me-2"></i> Society Master
                        </a>
                        <a href="BuildingCrudServlet" class="btn btn-success px-4 py-2 rounded-pill mb-2">
                            <i class="bi bi-columns-gap me-2"></i> Building/Wing Form
                        </a>
                        <a href="FlatCrudServlet" class="btn btn-success px-4 py-2 rounded-pill">
                            <i class="bi bi-door-open me-2"></i> Flat/Unit Form
                        </a>
                    </div>
                </div>
            </div>

            <!-- Vehicle & Parking -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-header"> <i class="bi bi-truck-front me-2"></i> Vehicle & Parking </div>
                    <div class="card-body text-center">
                        <p class="text-muted mb-4">Manage parking slots and vehicle registrations</p>
                        <a href="ParkingSlotServlet?action=view" class="btn btn-warning px-4 py-2 rounded-pill mb-2">
                            <i class="bi bi-grid-3x3-gap-fill me-2"></i> Parking Slot Master
                        </a>
                        <a href="VehicleServlet" class="btn btn-warning px-4 py-2 rounded-pill">
                            <i class="bi bi-car-front-fill me-2"></i> Vehicle Registration
                        </a>
                    </div>
                </div>
            </div>

            <!-- Amenities & Booking -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-header"> <i class="bi bi-calendar2-check me-2"></i> Amenities & Booking </div>
                    <div class="card-body text-center">
                        <p class="text-muted mb-4">Set up amenities and booking rules</p>
                        <a href="AmenityServlet?action=view" class="btn btn-info px-4 py-2 rounded-pill mb-2">
                            <i class="bi bi-tree-fill me-2"></i> Amenity Master
                        </a>
                        <a href="bbooking_rules_folder/booking_rules.jsp" class="btn btn-info px-4 py-2 rounded-pill">
                            <i class="bi bi-sliders me-2"></i> Booking Rules Setup
                        </a>
                    </div>
                </div>
            </div>

            <!-- Complaints & Notifications -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-header"> <i class="bi bi-exclamation-triangle-fill me-2"></i> Complaints & Notifications </div>
                    <div class="card-body text-center">
                        <p class="text-muted mb-4">Track complaints and send notifications</p>
                        <a href="ComplaintServlet?action=view" class="btn btn-danger px-4 py-2 rounded-pill mb-2">
                            <i class="bi bi-chat-dots-fill me-2"></i> Complaint Tracking
                        </a>
                        <a href="NotificationServlet" class="btn btn-danger px-4 py-2 rounded-pill">
                            <i class="bi bi-bell-fill me-2"></i> Notification
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <%@ include file="../partials/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
