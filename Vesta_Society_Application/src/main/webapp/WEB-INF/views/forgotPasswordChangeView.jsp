<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Change Password</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(to bottom right, #fffaf3, #f5e9dc);
      font-family: 'Segoe UI', sans-serif;
      display: flex;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
    }

    .change-card {
      background-color: #fef6f0;
      border-radius: 16px;
      padding: 2rem;
      max-width: 400px;
      width: 100%;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    }

    .change-card h4 {
      text-align: center;
      font-weight: 600;
      margin-bottom: 1.5rem;
      color: #c89666;
    }

    .form-label {
      font-weight: 500;
      color: #715a5a;
    }

    .form-control {
      border-radius: 12px;
      border: 1px solid #c89666;
      padding: 10px;
    }

    .btn-submit {
      background-color: #2d545e;
      color: #fff;
      border-radius: 30px;
      padding: 10px;
      font-weight: 600;
      border: none;
      width: 100%;
      margin-top: 1rem;
    }

    .alert {
      border-radius: 12px;
      font-weight: 500;
      text-align: center;
      margin-bottom: 1rem;
    }
  </style>
</head>
<body>

<div class="change-card">
  <h4>Set New Password</h4>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <form action="ForgotPasswordServlet" method="post">
    <input type="hidden" name="action" value="changePassword" />
    <div class="mb-3">
      <label for="newPassword" class="form-label">New Password</label>
      <input type="password" class="form-control" id="newPassword" name="newPassword" required>
    </div>
    <div class="mb-3">
      <label for="confirmPassword" class="form-label">Confirm Password</label>
      <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
    </div>
    <button type="submit" class="btn btn-submit">Update Password</button>
  </form>
</div>

</body>
</html>
