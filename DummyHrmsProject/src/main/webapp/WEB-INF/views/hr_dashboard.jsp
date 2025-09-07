<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HR Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">HRMS</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="ViewLeavesServlet">Leaves</a></li>
                    <li class="nav-item"><a class="nav-link" href="ViewHolidaysServlet">Holidays</a></li>
                    <li class="nav-item"><a class="nav-link" href="CreateDesignationServlet">Create Designation</a></li>
                    <li class="nav-item"><a class="nav-link" href="IncrementSalaryServlet">Increment Salary</a></li>
                    <li class="nav-item"><a class="nav-link" href="ViewEmployeesServlet">All Employees</a></li>
                </ul>
                <span class="navbar-text">
                    Welcome, <a href="HRProfileServlet?id=${hrId}" class="text-info">${hrName}</a>
                </span>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-4">
        <h2 class="mb-3">HR Dashboard</h2>
        <p>Select an option from the navigation bar to manage HR operations.</p>

        <!-- Optional: Quick links or cards -->
        <div class="row">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Manage Leaves</h5>
                        <p class="card-text">Approve or reject employee leave requests.</p>
                        <a href="ViewLeavesServlet" class="btn btn-light">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Create Designation</h5>
                        <p class="card-text">Add or update job roles in your organization.</p>
                        <a href="CreateDesignationServlet" class="btn btn-light">Go</a>
                    </div>
                </div>
            </div>
            <!-- Add more cards as needed -->
        </div>
    </div>
</body>
</html>
