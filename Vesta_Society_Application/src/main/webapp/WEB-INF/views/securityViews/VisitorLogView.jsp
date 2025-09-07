<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Visitor Approval Logs</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
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

    h3 {
      color: #2C3E50;
      font-weight: 600;
      text-align: center;
      margin-bottom: 2rem;
    }

    .table {
      background-color: #fbeae7;
      border-radius: 12px;
      overflow: hidden;
    }

    th {
      background-color: #A8D5BA;
      color: #2c2c2c;
      font-weight: 600;
      text-align: center;
    }

    td {
      vertical-align: middle;
      color: #2c2c2c;
      text-align: center;
    }

    .visitor-photo {
      width: 60px;
      height: 60px;
      object-fit: cover;
      border-radius: 50%;
      box-shadow: 0 0 6px rgba(0,0,0,0.1);
    }

    .status-pill {
      display: inline-block;
      min-width: 100px;
      padding: 4px 12px;
      border-radius: 20px;
      font-weight: 500;
      font-size: 0.9rem;
      text-align: center;
    }

    .Pending {
      background-color: #ffeeba;
      color: #856404;
    }

    .Approved {
      background-color: #c3e6cb;
      color: #155724;
    }

    .Rejected {
      background-color: #f5c6cb;
      color: #721c24;
    }

    .btn-sm.btn-success {
      font-size: 0.85rem;
      padding: 4px 12px;
      border-radius: 20px;
      background-color: #4CAF50;
      border: none;
    }

    .btn-sm.btn-success:hover {
      background-color: #3e8e41;
      color: #fff;
    }

    .action-cell {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 8px;
    }
  </style>
</head>
<body>

<%@ include file="../../partials/securityNavbar.jsp" %>

<div class="main-content">
  <h3>Visitor Approval Logs</h3>

  <c:if test="${not empty message}">
    <div class="alert alert-success text-center">${message}</div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="alert alert-danger text-center">${error}</div>
  </c:if>

  <div class="table-responsive">
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>Visitor Name</th>
          <th>Purpose</th>
          <th>Flat No</th>
          <th>Member Name</th>
          <th>Photo</th>
          <th>Status / Action</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="log" items="${visitorLogs}">
          <tr>
            <td>${log.visitorName}</td>
            <td>${log.purpose}</td>
            <td>${log.flatNo}</td>
            <td>${log.memberFullName}</td>
            <td>
              <c:if test="${not empty log.photoPath}">
                <img src="${pageContext.request.contextPath}/images/${log.photoPath}" class="visitor-photo" alt="Visitor Photo" />
              </c:if>
              <c:if test="${empty log.photoPath}">
                <span class="text-muted">No photo</span>
              </c:if>
            </td>
            <td>
              <div class="action-cell">
                <span class="status-pill ${log.status}">${log.status}</span>

                <c:if test="${log.status == 'Pending'}">
                  <form action="VisitorLogViewServlet" method="post">
                    <input type="hidden" name="visitorId" value="${log.visitorId}" />
                    <input type="hidden" name="action" value="approve" />
                    <button type="submit" class="btn btn-sm btn-success">✅ Approve</button>
                  </form>
                </c:if>
              </div>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
