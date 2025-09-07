<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Raise Incident Notification</title>

  <!-- Bootstrap & Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Page Styling -->
  <style>
    body {
      background: linear-gradient(to right, #FFF2EF, #FFDBB6);
      font-family: 'Segoe UI', sans-serif;
      margin: 0;
    }

    .main-content {
      margin-left: 270px;
      padding: 2rem;
    }

    .form-container {
      background-color: #fbeae7;
      border-radius: 16px;
      padding: 30px;
      max-width: 600px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0,0,0,0.08);
    }

    h3 {
      text-align: center;
      color: #2C3E50;
      margin-bottom: 20px;
      font-weight: 700;
    }

    .form-label {
      font-weight: 600;
      color: #2C3E50;
    }

    .form-control {
      border-radius: 12px;
      border: 1px solid #d8cfcf;
    }

    .btn-submit {
      background-color: #FFB6B6;
      color: #2C3E50;
      border-radius: 30px;
      font-weight: 600;
      padding: 10px 24px;
      border: none;
      transition: background 0.3s ease;
      width: 100%;
    }

    .btn-submit:hover {
      background-color: #f89a9a;
    }

    .alert {
      border-radius: 12px;
      font-weight: 500;
      margin-bottom: 20px;
    }

    .alert-success {
      background-color: #d4edda;
      color: #155724;
    }

    .alert-danger {
      background-color: #f8d7da;
      color: #721c24;
    }
  </style>
</head>
<body>

  <%@ include file="../../partials/securityNavbar.jsp" %>

  <div class="main-content">
    <div class="form-container">
      <h3>Raise Incident Notification</h3>

      <c:if test="${not empty message}">
        <div class="alert alert-success text-center">${message}</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
      </c:if>

      <form action="IncidentNotificationServlet" method="post">
        <div class="mb-3">
          <label class="form-label">Message</label>
          <textarea name="message" class="form-control" rows="4" required placeholder="Describe the incident..."></textarea>
        </div>

        <button type="submit" class="btn-submit">Send Notification</button>
      </form>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
