<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login - Vesta Society</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Rubik:wght@500;700&display=swap');

    body {
      margin: 0;
      font-family: 'Rubik', sans-serif;
      background: linear-gradient(to bottom right, #fffaf3, #f5e9dc);
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .login-card {
      background-color: #fef6f0;
      border-radius: 20px;
      padding: 2.5rem;
      max-width: 420px;
      width: 100%;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
      color: #2d545e;
    }

    .login-card h4 {
      text-align: center;
      font-weight: 700;
      margin-bottom: 2rem;
      color: #c89666;
    }

    .form-label {
      font-weight: 500;
      color: #715a5a;
    }

    .form-control {
      background-color: #ffffff;
      border: 1px solid #c89666;
      color: #2d545e;
      border-radius: 12px;
      padding: 10px;
    }

    .form-control::placeholder {
      color: #aaa;
    }

    .btn-login {
      background-color: #2d545e;
      color: #fff;
      border-radius: 30px;
      padding: 10px;
      font-weight: 600;
      border: none;
      width: 100%;
      transition: background-color 0.3s ease;
    }

    .btn-login:hover {
      background-color: #12343b;
    }

    .btn-forgot {
      display: block;
      margin: 1rem auto 0;
      color: #c89666;
      font-size: 0.9rem;
      text-decoration: underline;
      background: none;
      border: none;
    }

    .alert {
      border-radius: 12px;
      font-weight: 500;
      text-align: center;
      margin-bottom: 1rem;
    }

    .logo {
      text-align: center;
      margin-bottom: 1rem;
    }

    .logo img {
      width: 90px;
      height: 90px;
      border-radius: 40%;
      object-fit: cover;
      box-shadow: 0 0 10px rgba(200, 150, 102, 0.3);
    }
  </style>
</head>
<body>

<div class="login-card">

  <div class="logo">
    <img src="${pageContext.request.contextPath}/images/vesta-logo-2.png" alt="Vesta Logo" />
  </div>

  <h4>Vesta Society</h4>

  <!-- Login Error Message -->
  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <!-- Email Error Message for Forgot Password -->
  <c:if test="${not empty emailError}">
    <div class="alert alert-warning">${emailError}</div>
  </c:if>
  
  <!-- Success Message for Forgot Password -->
  <c:if test="${not empty successChangeMessage}">
    <div class="alert alert-success">${successChangeMessage}</div>
  </c:if>

  <!-- Login Form -->
  <form action="${pageContext.request.contextPath}/loginServlet" method="post">
    <div class="mb-3">
      <label for="username" class="form-label">Username or Email</label>
      <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">Password</label>
      <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
    </div>

    <button type="submit" class="btn btn-login">Login</button>
  </form>

  <!-- Forgot Password -->
<div class="text-center mt-3">
  <a href="${pageContext.request.contextPath}/ForgotPasswordServlet" class="btn-forgot">Forgot Password?</a>
</div>



</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
