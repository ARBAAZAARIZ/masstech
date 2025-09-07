<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login | HRMS</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            margin: 80px auto;
            padding: 30px;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-control:focus {
            box-shadow: none;
            border-color: #0d6efd;
        }
        .error-message {
            color: #dc3545;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="login-container">
        <h3 class="text-center mb-4">Login to HRMS</h3>
        <form action="LoginServlet" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="text" name="email" id="email" class="form-control" placeholder="Enter email" required />
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Enter password" required />
            </div>
            <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>

        <c:if test="${not empty error}">
            <div class="error-message text-center">${error}</div>
        </c:if>
    </div>
</div>

<!-- Bootstrap JS (optional for interactivity) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
