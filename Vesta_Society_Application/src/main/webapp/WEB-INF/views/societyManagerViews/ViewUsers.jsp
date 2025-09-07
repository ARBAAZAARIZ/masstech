<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Users</title>

  <!-- Bootstrap & Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Pastel Styling -->
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

    h2 {
      color: #748873;
      font-weight: 600;
      margin-bottom: 2rem;
      text-align: center;
    }

    .search-bar {
      max-width: 400px;
      margin: 0 auto 2rem auto;
    }

    .search-bar input {
      border-radius: 30px;
      border: 1px solid #D1A980;
      padding: 10px 20px;
      font-size: 1rem;
    }

    .user-table {
      background-color: #E5E0D8;
      border-radius: 16px;
      padding: 1rem;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
    }

    .table thead {
      background-color: #D1A980;
      color: #fff;
      font-weight: 600;
    }

    .table tbody tr:hover {
      background-color: #fdf6f0;
    }

    .profile-img {
      width: 48px;
      height: 48px;
      object-fit: cover;
      border-radius: 50%;
      border: 2px solid #748873;
    }

    .btn-action {
      background-color: #D1A980;
      border: none;
      border-radius: 20px;
      padding: 6px 16px;
      font-weight: 500;
      color: #fff;
    }

    .btn-action:hover {
      background-color: #b88c5f;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/societyManagerNavbar.jsp" %>

  <div class="main-content">
    <div class="d-flex justify-content-between align-items-center mb-4">
  <h2 class="mb-0">Users in ${societyName}</h2>
  <a href="MemberServlet?action=view" class="btn btn-action">
    <i class="bi bi-person-lines-fill me-1"></i> View Members
  </a>
</div>


    

    <div class="user-table table-responsive">
      <table class="table table-bordered align-middle text-center">
        <thead>
          <tr>
            <th>Photo</th>
            <th> User's ID</th>
            <th> Member's ID</th>
            <th>Username</th>
            <th>Role</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="u" items="${usersList}">
            <tr>
              <td>
                <img src="${pageContext.request.contextPath}/images/${u.profile_photo}" alt="Profile" class="profile-img" />
              </td>
              <td>${u.userID}</td>
              <td>${u.memberID}</td>
              <td>${u.username}</td>
              <td>${u.role}</td>
              <td>${u.email}</td>
              <td>${u.phoneNumber}</td>
              <td>${u.status}</td>
              <td>
                
                <a href="UserServlet?action=edit&memberID=${u.memberID}" class="btn btn-action">
                  <i class="bi bi-pencil-fill"></i> Edit
                </a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <br>
    
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
