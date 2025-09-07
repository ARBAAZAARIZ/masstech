<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
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

    .profile-card {
      background-color: #E5E0D8;
      border-radius: 16px;
      padding: 2rem;
      max-width: 700px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
    }

    .profile-photo {
      width: 120px;
      height: 120px;
      object-fit: cover;
      border-radius: 50%;
      border: 3px solid #D1A980;
      margin-bottom: 1rem;
    }

    .profile-header {
      text-align: center;
      margin-bottom: 2rem;
    }

    .profile-header h4 {
      font-weight: 700;
      color: #748873;
    }

    .profile-header p {
      color: #A0A0A0;
      margin-bottom: 0;
    }

    .profile-details .row {
      margin-bottom: 1rem;
    }

    .label-col {
      font-weight: 600;
      color: #748873;
      text-align: right;
    }

    .value-col {
      color: #2c2c2c;
    }

    .btn-group {
      margin-top: 2rem;
      text-align: center;
    }

    .btn-update, .btn-logout, .btn-password {
      border-radius: 30px;
      padding: 8px 20px;
      font-weight: 500;
      margin: 0 10px;
      border: none;
      transition: background-color 0.3s ease;
    }

    .btn-update {
      background-color: #D1A980;
      color: #fff;
    }

    .btn-update:hover {
      background-color: #b88c5f;
    }

    .btn-password {
      background-color: #748873;
      color: #fff;
    }

    .btn-password:hover {
      background-color: #5c6b5d;
    }

    .btn-logout {
      background-color: #dc3545;
      color: #fff;
    }

    .btn-logout:hover {
      background-color: #b02a37;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <div class="profile-card">
    <div class="profile-header">
      <img src="${pageContext.request.contextPath}/images/${authUser.profile_photo}" alt="Profile Photo" class="profile-photo">
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

    <div class="btn-group d-flex justify-content-center gap-3 mt-4">
      <a href="UpdateProfileServlet" class="btn btn-update">Update Profile</a>
      <a href="SocietymanagerChangePassword?action=generateOTP" class="btn btn-password">Change Password</a>
      <a href="LogoutServlet" class="btn btn-logout">Logout</a>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
