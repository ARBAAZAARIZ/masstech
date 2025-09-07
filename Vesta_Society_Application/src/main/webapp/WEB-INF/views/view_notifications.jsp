<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notifications</title>

    <!-- Bootstrap & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

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

        h2 {
            color: #5D688A;
            font-weight: 600;
        }

        .table-container {
            background-color: #fbeae7;
            border-radius: 16px;
            padding: 24px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        .badge-unread {
            background-color: #FFD6A5;
            color: #2c2c2c;
        }

        .badge-read {
            background-color: #A8D5BA;
            color: #2c2c2c;
        }

        .btn-success {
            background-color: #F7A5A5;
            border: none;
            border-radius: 20px;
            font-weight: 500;
        }

        .btn-success:hover {
            background-color: #e68c8c;
        }

        .alert {
            border-radius: 12px;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <h2 class="mb-4 text-center">Notifications</h2>

        <c:if test="${not empty message}">
            <div class="alert alert-success text-center fw-semibold">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center fw-semibold">${error}</div>
        </c:if>

        <div class="table-container">
            <table class="table table-bordered table-hover text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Message</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="n" items="${notificationList}">
                        <tr>
                            <td>${n.notificationId}</td>
                            <td>${n.userId}</td>
                            <td>${n.message}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${n.readStatus == 'Unread'}">
                                        <span class="badge badge-unread">Unread</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-read">Read</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${n.createdAt}</td>
                            <td>
                                <c:if test="${n.readStatus == 'Unread'}">
                                    <form action="NotificationServlet" method="post">
                                        <input type="hidden" name="action" value="update" />
                                        <input type="hidden" name="notificationId" value="${n.notificationId}" />
                                        <button type="submit" class="btn btn-success btn-sm">
                                            <i class="bi bi-check-circle me-1"></i> Mark as Read
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
