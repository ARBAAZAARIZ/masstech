<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Assign Flats</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
  background-color: #F8F8F8;
  font-family: 'Segoe UI', sans-serif;
  margin: 0;
}
.empty-state {
  background-color: #E5E0D8;
  padding: 2rem;
  border-radius: 16px;
  text-align: center;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
}


.main-content {
  margin-left: 260px;
  padding: 2rem;
}

h2, h4 {
  color: #748873;
  font-weight: 600;
}

.btn-action {
  background-color: #D1A980;
  color: #fff;
  border-radius: 30px;
  padding: 6px 16px;
  font-weight: 500;
  border: none;
  transition: background-color 0.3s ease;
}

.btn-action:hover {
  background-color: #b88c5f;
}

.btn-secondary {
  background-color: #748873;
  color: #fff;
  border-radius: 30px;
  padding: 6px 16px;
  font-weight: 500;
  border: none;
  transition: background-color 0.3s ease;
}

.btn-secondary:hover {
  background-color: #5c6b5d;
}

.form-section {
  background-color: #E5E0D8;
  padding: 1.5rem;
  border-radius: 16px;
  margin-top: 2rem;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
}

.form-label {
  font-weight: 600;
  color: #748873;
  margin-bottom: 0.5rem;
}

.form-control {
  border-radius: 12px;
  border: 1px solid #D1A980;
  padding: 10px;
  font-size: 1rem;
}

.table thead {
  background-color: #D1A980;
  color: #fff;
  font-weight: 600;
}

.table tbody tr:hover {
  background-color: #fdf6f0;
}

.table td, .table th {
  vertical-align: middle;
}

.table-wrapper {
  background-color: #E5E0D8;
  padding: 1rem;
  border-radius: 16px;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.06);
}

.pastel-table {
  border-collapse: separate;
  border-spacing: 0;
  width: 100%;
  border-radius: 12px;
  overflow: hidden;
}

.pastel-table thead {
  background-color: #D1A980;
  color: #fff;
  font-weight: 600;
}

.pastel-table th, .pastel-table td {
  padding: 12px;
  vertical-align: middle;
  border: 1px solid #ddd;
}

.pastel-table tbody tr:hover {
  background-color: #fdf6f0;
}

.pastel-table td:last-child {
  white-space: nowrap;
}

    
  </style>
</head>
<body>

<%@ include file="../../partials/societyManagerNavbar.jsp" %>

<div class="main-content">

<c:if test="${empty occupancyList and empty buildingList and empty flatList}">
 <div class="empty-state">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2 class="mb-0">No Flats Assigned Yet</h2>
    <a href="AssignFlatServlet?action=init" class="btn btn-action">Assign Flat</a>
  </div>
  <p class="text-muted">Start assigning flats by selecting a building.</p>
</div>

</c:if>



  <!-- Occupancy Table -->
  <c:if test="${not empty occupancyList}">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2 class="mb-0">Flat Occupancy Records</h2>
    <a href="AssignFlatServlet?action=init" class="btn btn-action">Assign Flat</a>
  </div>

  <div class="table-wrapper">
    <table class="table pastel-table table-bordered table-hover text-center">
      <thead>
        <tr>
          <th>Occupancy ID</th>
          <th>Flat ID</th>
          <th>Member ID</th>
          <th>Type</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="o" items="${occupancyList}">
          <tr>
            <td>${o.occupancyId}</td>
            <td>${o.flatId}</td>
            <td>${o.memberId}</td>
            <td>${o.type}</td>
            <td>${o.startDate}</td>
            <td>${o.endDate}</td>
            <td>
  				<a href="AssignFlatServlet?action=edit&occupancyId=${o.occupancyId}" class="btn btn-sm btn-secondary">
    				<i class="bi bi-pencil-fill"></i> Edit
  				</a>
			</td>

          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</c:if>


  <!-- Building Selection Form -->
  <c:if test="${not empty buildingList and empty flatList}">
    <div class="form-section">
      <h4 class="mb-3">Step 1: Choose Building</h4>
      <form action="AssignFlatServlet" method="get">
        <input type="hidden" name="action" value="selectBuilding" />
        <label class="form-label">Building</label>
        <select name="buildingId" class="form-control mb-3" required>
          <c:forEach var="b" items="${buildingList}">
            <option value="${b.buildingId}">${b.name}</option>
          </c:forEach>
        </select>
        <div class="d-flex justify-content-between">
          <a href="AssignFlatServlet?action=view" class="btn btn-secondary">Cancel</a>
          <button type="submit" class="btn btn-action">Next</button>
        </div>
      </form>
    </div>
  </c:if>

  <!-- Flat Assignment Form -->
  <c:if test="${not empty flatList}">
    <div class="form-section">
      <h4 class="mb-3">Step 2: Assign Flat</h4>
      <form action="AssignFlatServlet" method="post">
        <input type="hidden" name="action" value="assign" />
        <input type="hidden" name="buildingId" value="${selectedBuildingId}" />

        <label class="form-label">Flat</label>
        <select name="flatId" class="form-control mb-3" required>
          <c:forEach var="f" items="${flatList}">
            <option value="${f.flatId}">${f.flatNo} (Floor ${f.floorNo})</option>
          </c:forEach>
        </select>

        <label class="form-label">Member ID</label>
        <input type="number" name="memberId" class="form-control mb-3" required />

        <label class="form-label">Occupancy Type</label>
        <select name="type" class="form-control mb-3" required>
          <option value="Owner">Owner</option>
          <option value="Tenant">Tenant</option>
        </select>

        <label class="form-label">Start Date</label>
        <input type="date" name="startDate" class="form-control mb-3" required />

        <label class="form-label">End Date (optional)</label>
        <input type="date" name="endDate" class="form-control mb-3" />

        <div class="d-flex justify-content-between">
          <a href="AssignFlatServlet?action=init" class="btn btn-secondary">← Change Building</a>
          <button type="submit" class="btn btn-action">Assign Flat</button>
        </div>
      </form>
    </div>
  </c:if>

</div>

</body>
</html>
