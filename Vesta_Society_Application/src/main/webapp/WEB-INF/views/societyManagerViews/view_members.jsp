<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Members</title>

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

    .btn-search {
      background-color: #D1A980;
      border: none;
      border-radius: 30px;
      padding: 8px 20px;
      font-weight: 500;
      color: #fff;
    }

    .btn-search:hover {
      background-color: #b88c5f;
    }

    .member-table {
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

    .alert {
      border-radius: 12px;
      font-weight: 500;
      margin-bottom: 20px;
    }

    .alert-danger {
      background-color: #f8d7da;
      color: #721c24;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/societyManagerNavbar.jsp" %>

  <div class="main-content">
    <h2>Members in ${societyName}</h2>

    <form action="MemberServlet" method="get" class="d-flex justify-content-center">
  		<input type="text" name="searchName" class="form-control me-2" placeholder="Search by Member Name..." />
  		<button type="submit" class="btn btn-search">
    			<i class="bi bi-search"></i> Search
  		</button>
	</form>


    <c:if test="${not empty error}">
      <div class="alert alert-danger text-center">${error}</div>
    </c:if>

    <div class="member-table table-responsive">
      <table class="table table-bordered align-middle text-center">
        <thead>
          <tr>
            <th>Photo</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
            <th>Created At</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="m" items="${memberList}">
            <tr>
              <td>
                <img src="${pageContext.request.contextPath}/images/${m.pofile_photo}" alt="Profile" class="profile-img" />
              </td>
              <td>${m.fullName}</td>
              <td>${m.email}</td>
              <td>${m.phone}</td>
              <td>${m.status}</td>
              <td>${m.createdAt}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
