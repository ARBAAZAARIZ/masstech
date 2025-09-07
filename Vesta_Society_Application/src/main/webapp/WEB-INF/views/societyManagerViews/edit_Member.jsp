<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Member</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

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

    .form-container {
      background-color: #E5E0D8;
      border-radius: 16px;
      padding: 2rem;
      max-width: 600px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
    }

    h3 {
      color: #748873;
      font-weight: 600;
      text-align: center;
      margin-bottom: 1.5rem;
    }

    .form-label {
      font-weight: 600;
      color: #748873;
    }

    .form-control {
      border-radius: 12px;
      border: 1px solid #D1A980;
    }

    .btn-submit {
      background-color: #D1A980;
      color: #fff;
      border-radius: 30px;
      font-weight: 600;
      padding: 10px 24px;
      border: none;
      width: 100%;
    }

    .btn-submit:hover {
      background-color: #b88c5f;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/societyManagerNavbar.jsp" %>

  <div class="main-content">
    <div class="form-container">
      <h3>Edit Member Details</h3>

      <form action="UserServlet" method="post">
        <input type="hidden" name="memberId" value="${member.memberId}" />

        <div class="mb-3">
          <label class="form-label">Full Name</label>
          <input type="text" name="fullName" class="form-control" value="${member.fullName}"  />
        </div>

        <div class="mb-3">
          <label class="form-label">Email</label>
          <input type="email" name="email" class="form-control" value="${member.email}"  />
        </div>

        <div class="mb-3">
          <label class="form-label">Phone</label>
          <input type="text" name="phone" class="form-control" value="${member.phone}" />
        </div>

        <div class="mb-3">
          <label class="form-label">Status</label>
          <select name="status" class="form-control">
            <option value="Active" ${member.status == 'Active' ? 'selected' : ''}>Active</option>
            <option value="Inactive" ${member.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
          </select>
        </div>

        <button type="submit" class="btn-submit">Update Member</button>
      </form>
    </div>
  </div>

</body>
</html>
