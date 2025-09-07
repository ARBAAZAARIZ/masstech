<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Notifications</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body { background-color: #F8F8F8; font-family: 'Segoe UI', sans-serif; margin: 0; }
    .main-content { margin-left: 260px; padding: 2rem; }
    h2 { color: #748873; font-weight: 600; margin-bottom: 1.5rem; text-align: center; }
    .table-wrapper { background-color: #E5E0D8; padding: 1rem; border-radius: 16px; box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06); }
    .pastel-table { border-collapse: separate; border-spacing: 0; width: 100%; border-radius: 12px; overflow: hidden; }
    .pastel-table thead { background-color: #D1A980; color: #fff; font-weight: 600; }
    .pastel-table th, .pastel-table td { padding: 12px; vertical-align: middle; border: 1px solid #ddd; text-align: center; }
    .pastel-table tbody tr:hover { background-color: #fdf6f0; }
    .btn-read { background-color: #748873; color: #fff; border-radius: 20px; padding: 6px 14px; font-weight: 500; border: none; }
    .btn-read:hover { background-color: #5c6b5d; }
    .profile-img { width: 40px; height: 40px; border-radius: 50%; object-fit: cover; }
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">
  <h2>Notifications</h2>

  <c:if test="${empty notificationList}">
    <div class="alert alert-warning text-center">No notifications found.</div>
  </c:if>

  <c:if test="${not empty notificationList}">
    <div class="table-wrapper">
      <table class="table pastel-table table-bordered table-hover text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>User</th>
            <th>Message</th>
            <th>Status</th>
            <th>Created</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="n" items="${notificationList}">
            <tr>
              <td>${n.notificationId}</td>
              <td>
                <img src="${pageContext.request.contextPath}/images/${n.profilePhoto}" class="profile-img" />
                <br/>${n.fullName} (${n.username})
              </td>
              <td>${n.message}</td>
              <td>${n.readStatus}</td>
              <td>${n.createdAt}</td>
              <td>
                <c:if test="${n.readStatus == 'Unread'}">
                  <a href="ViewNotificationServlet?action=markRead&notificationId=${n.notificationId}" class="btn btn-read">Mark as Read</a>
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>
</div>

</body>
</html>
