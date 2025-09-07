<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #FFF2EF, #FFDBB6);
            font-family: 'Segoe UI', sans-serif;
            padding: 30px;
        }

        .form-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 30px;
            max-width: 600px;
            margin: auto;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        h3 {
            color: #2C3E50;
            font-weight: 600;
            text-align: center;
            margin-bottom: 30px;
        }

        .form-label {
            font-weight: 500;
            color: #2c2c2c;
        }

        .form-control, .form-select {
            border-radius: 12px;
            border: 1px solid #d8cfcf;
            box-shadow: none;
        }

        .btn-success {
            background-color: #A8D5BA;
            border: none;
            border-radius: 30px;
            padding: 10px 20px;
            font-weight: 500;
            color: #2c2c2c;
        }

        .btn-success:hover {
            background-color: #8fc9a9;
        }

        .text-muted {
            font-size: 0.9rem;
        }
    </style>
</head>
<body>

 <%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="form-container">
    <h3>Update Profile</h3>

    <form action="UpdateProfileServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="memberId" value="${member.memberId}" />
        <input type="hidden" name="existingPhoto" value="${member.pofile_photo}" />

        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" value="${authUser.username}" class="form-control" readonly />
        </div>

        <div class="mb-3">
            <label class="form-label">Full Name</label>
            <input type="text" name="fullName" value="${member.fullName}" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" value="${member.email}" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" name="phone" value="${member.phone}" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Profile Photo</label>
            <input type="file" name="profilePhoto" class="form-control" accept="image/*" />
            <small class="text-muted">Leave blank to keep existing photo</small>
        </div>

        <div class="text-center mt-4">
            <button type="submit" class="btn btn-success">Update Profile</button>
        </div>
    </form>
</div>
</body>
</html>
