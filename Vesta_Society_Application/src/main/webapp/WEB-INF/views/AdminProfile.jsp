<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Page Styling -->
    <style>
        body {
            background: linear-gradient(to right, #FFF2EF, #FFDBB6);
            font-family: 'Segoe UI', sans-serif;
        }

        .main-content {
            margin-left: 270px;
            padding: 2rem;
        }

        .profile-card {
            background-color: #fff;
            border-radius: 16px;
            padding: 30px;
            max-width: 700px;
            margin: auto;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        .profile-photo {
            width: 120px;
            height: 120px;
            object-fit: cover;
            border-radius: 50%;
            border: 3px solid #4CA1AF;
            margin-bottom: 20px;
        }

        .profile-header {
            text-align: center;
            margin-bottom: 30px;
        }

        .profile-header h4 {
            font-weight: 600;
            color: #2C3E50;
        }

        .profile-header p {
            color: #6c757d;
            margin-bottom: 0;
        }

        .profile-details {
            font-size: 1rem;
        }

        .profile-details .row {
            margin-bottom: 12px;
        }

        .label-col {
            font-weight: 600;
            color: #2C3E50;
            text-align: right;
        }

        .value-col {
            color: #2c2c2c;
        }

        .btn-group {
            margin-top: 30px;
            text-align: center;
        }

        .btn-update, .btn-logout, .btn-password {
            border-radius: 30px;
            padding: 8px 20px;
            font-weight: 500;
            margin: 0 10px;
        }

        .btn-update {
            background-color: #A8D5BA;
            color: #2c2c2c;
            border: none;
        }

        .btn-logout {
            background-color: #FFB6B6;
            color: #2c2c2c;
            border: none;
        }

        .btn-password {
            background-color: #B6D0FF;
            color: #2c2c2c;
            border: none;
        }

        .btn-update:hover {
            background-color: #8fc9a9;
        }

        .btn-logout:hover {
            background-color: #f89a9a;
        }

        .btn-password:hover {
            background-color: #9bbef0;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

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
                <a href="ChangePasswordServlet" class="btn btn-password">Change Password</a>
                <a href="LogoutServlet" class="btn btn-logout">Logout</a>
            </div>
        </div>
    </div>

   

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
