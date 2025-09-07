<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Societies</title>

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

        .btn-warning:hover {
            background-color: #f5c58c;
        }

        .btn-danger {
            background-color: #F7A5A5;
            border: none;
            color: #2c2c2c;
            font-weight: 500;
            padding: 6px 14px;
            border-radius: 20px;
        }

        .btn-danger:hover {
            background-color: #e68c8c;
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
            <h2 class="fw-bold">Society List</h2>
            <a href="SocietyCrudServlet?action=create" class="btn btn-success">
                <i class="bi bi-building-add me-2"></i> Add New Society
            </a>
        </div>

        <!-- Table Section -->
        <div class="table-container">
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Address Line 1</th>
                            <th>Address Line 2</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Pincode</th>
                            <th>Created At</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="s" items="${societyList}">
                            <tr>
                                <td>${s.societyId}</td>
                                <td>${s.name}</td>
                                <td>${s.addressLine1}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty s.addressLine2}">—</c:when>
                                        <c:otherwise>${s.addressLine2}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${s.city}</td>
                                <td>${s.state}</td>
                                <td>${s.pincode}</td>
                                <td>${s.createdAt}</td>
                                <td>
                                    <div class="d-flex justify-content-center gap-2">
                                        <a href="SocietyCrudServlet?action=edit&societyId=${s.societyId}" class="btn btn-warning btn-sm">
                                            <i class="bi bi-pencil-square"></i> Edit
                                        </a>
                                        <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                                                data-bs-target="#confirmDeleteModal"
                                                data-societyid="${s.societyId}" data-societyname="${s.name}">
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
                ⚠️ <strong>This action is <u>irreversible</u>.</strong><br>
                You are about to permanently delete <span id="societyName" class="fw-bold text-decoration-underline text-danger"></span>.<br><br>
                <strong>All associated data will be permanently removed, including:</strong><br>
                • Registered users linked to this society<br>
                • Login credentials and access history<br>
                • Member profiles, roles, and contact details<br>
                • Administrative records and audit logs<br><br>
                <strong>Please proceed only if you are absolutely sure.</strong>
            </p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Delete</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap JS + Modal Logic -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
      const deleteModal = document.getElementById('confirmDeleteModal');
      deleteModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const societyId = button.getAttribute('data-societyid');
        const societyName = button.getAttribute('data-societyname');

        document.getElementById('societyName').textContent = societyName;
        document.getElementById('confirmDeleteBtn').href = "SocietyCrudServlet?action=delete&societyId=" + societyId;
      });
    </script>
</body>
</html>
