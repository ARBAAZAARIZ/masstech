<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Verify OTP</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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

    .otp-card {
      background-color: #E5E0D8;
      padding: 2rem;
      border-radius: 16px;
      max-width: 500px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
      text-align: center;
    }

    h2 {
      color: #748873;
      font-weight: 600;
      margin-bottom: 1.5rem;
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

    .btn-resend {
      background-color: #748873;
      color: #fff;
      border-radius: 30px;
      padding: 8px 20px;
      font-weight: 600;
      border: none;
      margin-top: 1rem;
      transition: background-color 0.3s ease;
    }

    .btn-resend:hover {
      background-color: #5c6b5d;
    }

    .timer {
      font-size: 1rem;
      color: #dc3545;
      margin-top: 1rem;
    }

    .alert {
      border-radius: 12px;
      font-weight: 500;
      margin-bottom: 1rem;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <div class="otp-card">
    <h2>Enter 6 Digit OTP</h2>

    <!-- ✅ Error Message Block -->
    <c:if test="${not empty otpErrorMessage}">
      <div class="alert alert-danger">${otpErrorMessage}</div>
    </c:if>

    <form action="SocietymanagerChangePassword" method="get">
    <input type="hidden" name="action" value="checkOTP" >
      <input type="text" name="otp" class="form-control" placeholder="Enter OTP" required />
      <button type="submit" class="btn btn-submit">Submit OTP</button>
    </form>

    <form action="SocietymanagerChangePassword?action=generateOTP" method="get">
      <button type="submit" class="btn btn-resend">Resend OTP</button>
    </form>

    <div class="timer" id="timer">Redirecting in 60 seconds...</div>
  </div>
</div>

<script>
  let countdown = 300;
  const timerElement = document.getElementById("timer");

  const interval = setInterval(() => {
    countdown--;
    timerElement.textContent = `Redirecting in ${countdown} seconds...`;

    if (countdown <= 0) {
      clearInterval(interval);
      window.location.href = "ProfileServlet";
    }
  }, 1000);
</script>

</body>
</html>
