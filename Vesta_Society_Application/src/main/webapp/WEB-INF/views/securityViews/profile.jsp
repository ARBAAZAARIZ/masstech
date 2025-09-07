<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Profile</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom Pastel Styles (must come after Bootstrap) -->
  <style>
    body {
      background: linear-gradient(to right, #FFF2EF, #FFDBB6);
      font-family: 'Segoe UI', sans-serif;
      padding: 40px;
    }

    .profile-card {
      background-color: #fff;
      border-radius: 20px;
      padding: 40px;
      max-width: 750px;
      margin: auto;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    }

    .profile-header {
      text-align: center;
      margin-bottom: 40px;
    }

    .profile-photo {
      width: 140px;
      height: 140px;
      object-fit: cover;
      border-radius: 50%;
      border: 4px solid #4CA1AF;
      margin-bottom: 20px;
    }

    .profile-header h4 {
      font-size: 1.8rem;
      font-weight: 700;
      color: #2C3E50;
      margin-bottom: 5px;
    }

    .profile-header p {
      font-size: 1.1rem;
      color: #6c757d;
    }

    .profile-details {
      font-size: 1.1rem;
    }

    .profile-details .row {
      margin-bottom: 16px;
    }

    .label-col {
      font-weight: 700;
      color: #2C3E50;
      text-align: right;
      font-size: 1.05rem;
    }

    .value-col {
      font-weight: 600;
      color: #2c2c2c;
      font-size: 1.05rem;
    }

    /* Pastel Buttons */
    .btn.pastel-btn {
      border-radius: 30px;
      padding: 10px 24px;
      font-weight: 600;
      font-size: 1rem;
      transition: all 0.3s ease;
      box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    }

    .btn.pastel-update {
      background-color: #A8D5BA !important;  /* soft green */
      color: #2C3E50 !important;
      border: 1px solid #2C3E50 !important;
    }
    .btn.pastel-update:hover {
      background-color: #8fc9a9 !important;
    }

    .btn.pastel-password {
      background-color: #B6D0FF !important;  /* soft blue */
      color: #2C3E50 !important;
      border: 1px solid #2C3E50 !important;
    }
    .btn.pastel-password:hover {
      background-color: #9bbef0 !important;
    }

    .btn.pastel-logout {
      background-color: #FFB6B6 !important;  /* soft red */
      color: #2C3E50 !important;
      border: 1px solid #2C3E50 !important;
    }
    .btn.pastel-logout:hover {
      background-color: #f89a9a !important;
    }
  </style>
</head>
<body>
  <%@ include file="../../partials/securityNavbar.jsp" %>

  <div class="profile-card">
    <div class="profile-header">
      <img src="${pageContext.request.contextPath}/images/${authUser.profile_photo}"
           alt="Profile Photo" class="profile-photo">
      <h4>${authUser.fullName}</h4>
      <p>${authUser.role}</p>
    </div>

    <div class="profile-details">
      <div class="row">
        <div class="col-4 label-col">Username:</div>
        <div class="col-8 value-col">${authUser.username}</div>
      </div>
      <div class="row">
        <div class="col-4 label-col">Email:</div>
        <div class="col-8 value-col">${authUser.email}</div>
      </div>
      <div class="row">
        <div class="col-4 label-col">Phone:</div>
        <div class="col-8 value-col">${authUser.phone}</div>
      </div>
      <div class="row">
        <div class="col-4 label-col">Status:</div>
        <div class="col-8 value-col">${authUser.status}</div>
      </div>
      <div class="row">
        <div class="col-4 label-col">Last Login:</div>
        <div class="col-8 value-col">${authUser.lastLoginAt}</div>
      </div>
      <div class="row">
        <div class="col-4 label-col">Society:</div>
        <div class="col-8 value-col">${society.name}</div>
      </div>
    </div>

    <div class="d-flex justify-content-center gap-3 mt-4">
      <a href="UpdateProfileServlet" class="btn pastel-btn pastel-update">Update Profile</a>
      <a href="ChangePasswordServlet" class="btn pastel-btn pastel-password">Change Password</a>
      <a href="login.jsp" class="btn pastel-btn pastel-logout">Logout</a>
    </div>
  </div>
</body>
</html>
