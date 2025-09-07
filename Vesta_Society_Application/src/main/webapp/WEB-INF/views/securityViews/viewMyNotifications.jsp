<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Incident Notifications</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(to right, #FFF2EF, #FFDBB6);
      font-family: 'Segoe UI', sans-serif;
      padding: 40px;
    }

    .log-container {
      background-color: #fff;
      border-radius: 16px;
      padding: 30px;
      max-width: 800px;
      margin: auto;
      box-shadow: 0 6px 16px rgba(0,0,0,0.08);
    }

    h3 {
      text-align: center;
      color: #2C3E50;
      margin-bottom: 30px;
      font-weight: 700;
    }

    .table th {
      background-color: #fbeae7;
      color: #2C3E50;
      font-weight: 600;
    }

    .status-unread {
      color: #d35400;
      font-weight: 600;
    }

    .status-read {
      color: #2C3E50;
      font-weight: 600;
    }
  </style>
</head>
<body>
<%@ include file="../../partials/securityNavbar.jsp" %>

<div class="log-container">
  <h3>My Incident Notifications</h3>

  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th>Message</th>
        <th>Status</th>
        <th>Created At</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="n" items="${notifications}">
        <tr>
          <td>${n.message}</td>
          <td>
            <c:choose>
              <c:when test="${n.readStatus == 'Unread'}">
                <span class="status-unread">Unread</span>
              </c:when>
              <c:otherwise>
                <span class="status-read">Read</span>
              </c:otherwise>
            </c:choose>
          </td>
          <td>${n.createdAt}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
