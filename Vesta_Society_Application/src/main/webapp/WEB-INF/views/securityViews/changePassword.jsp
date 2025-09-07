<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Change Password</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(to right, #FFF2EF, #FFDBB6);
      font-family: 'Segoe UI', sans-serif;
      padding: 40px;
    }
    .form-container {
      background-color: #fbeae7;
      border-radius: 16px;
      padding: 30px;
      max-width: 500px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0,0,0,0.08);
    }
    h3 {
      text-align: center;
      color: #2C3E50;
      margin-bottom: 20px;
    }
    .form-label {
      font-weight: 600;
      color: #2C3E50;
    }
    .form-control {
      border-radius: 12px;
      border: 1px solid #d8cfcf;
    }
    .btn-change {
      background-color: #B6D0FF;
      color: #2C3E50;
      border-radius: 30px;
      font-weight: 600;
      padding: 10px 24px;
      border: none;
      transition: background 0.3s ease;
      width: 100%;
    }
    .btn-change:hover {
      background-color: #9bbef0;
    }
    .alert {
      border-radius: 12px;
    }
  </style>
</head>
<body>
  <%@ include file="../../partials/securityNavbar.jsp" %>

  <div class="form-container">
    <h3>Change Password</h3>

    <c:if test="${not empty sessionScope.message}">
      <div class="alert alert-success">${sessionScope.message}</div>
      <c:remove var="message" scope="session"/>
    </c:if>
    <c:if test="${not empty sessionScope.error}">
      <div class="alert alert-danger">${sessionScope.error}</div>
      <c:remove var="error" scope="session"/>
    </c:if>

    <form action="ChangePasswordServlet" method="post">
      <div class="mb-3">
        <label class="form-label">Current Password</label>
        <input type="password" name="oldPassword" class="form-control" required />
      </div>
      <div class="mb-3">
        <label class="form-label">New Password</label>
        <input type="password" name="newPassword" class="form-control" required />
      </div>
      <div class="mb-3">
        <label class="form-label">Confirm New Password</label>
        <input type="password" name="confirmPassword" class="form-control" required />
      </div>
      <button type="submit" class="btn-change">Change Password</button>
    </form>
  </div>
</body>
</html>
