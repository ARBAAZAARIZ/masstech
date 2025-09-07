<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Users</title>

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

        .table {
            border: 2px solid #d8cfcf;
            border-radius: 12px;
            overflow: hidden;
        }

        .table thead {
            background: linear-gradient(to right, #F7A5A5, #FFDBB6);
            color: #2c2c2c;
            font-weight: 600;
            font-size: 1rem;
        }

        .table th, .table td {
            padding: 14px 16px;
            vertical-align: middle;
            border: 1px solid #d8cfcf;
        }

        .table tbody tr:hover {
            background-color: #fff6f3;
            transition: background 0.3s ease;
        }

        .badge {
            font-size: 0.9rem;
            padding: 6px 12px;
            border-radius: 12px;
        }

        .badge.bg-primary {
            background-color: #A5C8F7;
            color: #2c2c2c;
        }

        .badge.bg-success {
            background-color: #A8D5BA;
            color: #2c2c2c;
        }

        .badge.bg-secondary {
            background-color: #d3c0c0;
            color: #2c2c2c;
        }

        .btn-success {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 8px 20px;
            border-radius: 30px;
        }

        .btn-success:hover {
            background-color: #e68c8c;
        }

        .btn-warning {
            background-color: #FFD6A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 6px 14px;
            border-radius: 20px;
        }

        .btn-danger {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 6px 14px;
            border-radius: 20px;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 0.5rem;
        }

        .rounded-circle {
            border: 2px solid #d8cfcf;
            object-fit: cover;
        }

        .modal-content {
            border-radius: 12px;
        }

        .modal-header {
            border-top-left-radius: 12px;
            border-top-right-radius: 12px;
        }

        .modal-footer {
            border-bottom-left-radius: 12px;
            border-bottom-right-radius: 12px;
        }
    </style>
</head>
<body>

    <%@ include file="../partials/navbar.jsp" %>

    <div class="main-content">
        <!-- Header Section -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold">User Management</h2>
            <a href="CreateUserFormServlet" class="btn btn-success">
                <i class="bi bi-person-plus-fill me-2"></i> Create User
            </a>
        </div>

        <!-- Table Section -->
        <div class="table-container">
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Profile</th>
                            <th>Username</th>
                            <th>Role</th>
                            <th>Last Login</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Status</th>
                            <th>Society</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${usersList}">
                            <tr>
                                <td>${user.userID}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty user.profile_photo}">
                                            <img src="${pageContext.request.contextPath}/images/${user.profile_photo}"
                                                 alt="Profile" class="rounded-circle" width="50" height="50" />
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/images/default_user.png"
                                                 alt="Default Profile" class="rounded-circle" width="50" height="50" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${user.username}</td>
                                <td><span class="badge bg-primary">${user.role}</span></td>
                                <td><fmt:formatDate value="${user.lastLogin}" pattern="dd/MM/yyyy" /></td>
                                <td>${user.email}</td>
                                <td>${user.phoneNumber}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.status == 'Active'}">
                                            <span class="badge bg-success">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-secondary">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${user.societyName}</td>
                                <td>
                                    <div class="action-buttons">
                                        <a href="UpdateUserFormAdmin?userId=${user.userID}" class="btn btn-warning btn-sm">
                                            <i class="bi bi-pencil-square"></i> Update
                                        </a>
                                        <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                                                data-bs-target="#confirmDeleteModal" data-userid="${user.userID}">
                                            <i class="bi bi-trash-fill"></i> Delete
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    

    <!-- Delete Confirmation Modal -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-danger">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title" id="confirmDeleteLabel">Confirm Deletion</h5>
            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p class="mb-0 text-danger fw-semibold">
              ⚠️ <strong>This action is irreversible.</strong><br>
              Deleting this user will permanently remove all associated records, including login history, profile data, and society links.<br>
              Proceed at your own risk.
            </p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Delete</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Modal Logic -->
    <script>

  const deleteModal = document.getElementById('confirmDeleteModal');
  deleteModal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const userId = button.getAttribute('data-userid');
    const confirmBtn = document.getElementById('confirmDeleteBtn');
    confirmBtn.href = "UserCrudServlet?action=delete&userId=" + userId;
  });
</script>
</body>
</html>
