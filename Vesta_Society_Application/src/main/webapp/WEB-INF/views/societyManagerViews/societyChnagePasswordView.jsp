<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Change Password</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Rubik:wght@500;700&display=swap');

    body {
      background-color: #F8F8F8;
      font-family: 'Rubik', sans-serif;
      margin: 0;
    }

    .main-content {
      margin-left: 260px;
      padding: 2rem;
    }

    .password-card {
      background-color: #E5E0D8;
      padding: 2rem;
      border-radius: 16px;
      max-width: 500px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
    }

    h2 {
      color: #748873;
      font-weight: 600;
      margin-bottom: 1.5rem;
      text-align: center;
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
      margin-bottom: 1rem;
    }

    .btn-submit {
      background-color: #D1A980;
      color: #fff;
      border-radius: 30px;
      padding: 8px 20px;
      font-weight: 600;
      border: none;
      transition: background-color 0.3s ease;
    }

    .btn-submit:hover {
      background-color: #b88c5f;
    }

    .btn-profile {
      background-color: #748873;
      color: #fff;
      border-radius: 30px;
      padding: 8px 20px;
      font-weight: 600;
      border: none;
      margin: 2rem auto 0;
      display: block;
      text-align: center;
      width: fit-content;
      transition: background-color 0.3s ease;
    }

    .btn-profile:hover {
      background-color: #5c6b5d;
    }

    .alert {
      border-radius: 12px;
      font-weight: 500;
      text-align: center;
      margin-bottom: 1.5rem;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <div class="password-card">

    <!-- ✅ Message Block -->
    <c:if test="${not empty passwordSuccessMessage}">
      <div class="alert alert-success">${passwordSuccessMessage}</div>
    </c:if>

    <c:if test="${not empty passwordErrorMessage}">
      <div class="alert alert-danger">${passwordErrorMessage}</div>
    </c:if>
    
    <c:if test="${not empty passwordError}">
      <div class="alert alert-danger">${passwordError}</div>
    </c:if>

    <h2>Change Password</h2>
    <form action="${pageContext.request.contextPath}/SocietymanagerChangePassword" method="post">
      <label class="form-label">New Password</label>
      <input type="password" name="newPassword" class="form-control" required />

      <label class="form-label">Confirm Password</label>
      <input type="password" name="confirmPassword" class="form-control" required />

      <div class="text-center mt-3">
        <button type="submit" class="btn btn-submit">Update Password</button>
      </div>
    </form>
  </div>

  <a href="ProfileServlet" class="btn btn-profile">View Profile</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
