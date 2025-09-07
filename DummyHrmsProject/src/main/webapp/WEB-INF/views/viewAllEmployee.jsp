<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Directory</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Employee Directory</h2>
        <form action="CrudEmployeeServlet" method="post">
            <button type="submit" class="btn btn-success">+ Add New Employee</button>
        </form>
    </div>

    <!-- Table -->
    <c:if test="${not empty employeeList}">
        <div class="table-responsive">
            <table class="table table-bordered table-hover table-striped align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>DOB</th>
                        <th>Gender</th>
                        <th>Department</th>
                        <th>Designation</th>
                        <th>DOJ</th>
                        <th>Status</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${employeeList}">
                        <tr>
                            <td>${emp.empId}</td>
                            <td>${emp.name}</td>
                            <td>${emp.username}</td>
                            <td>${emp.email}</td>
                            <td>${emp.phone}</td>
                            <td>${emp.dob}</td>
                            <td>${emp.gender}</td>
                            <td>${emp.departmentName}</td>
                            <td>${emp.designationName}</td>
                            <td>${emp.doj}</td>
                            <td>${emp.status}</td>
                            <td>${emp.role}</td>
                            <td>
                                <a href="EditEmployeeServlet?id=${emp.empId}" class="btn btn-sm btn-outline-primary">Edit</a>
                                <a href="DeleteEmployeeServlet?id=${emp.empId}" class="btn btn-sm btn-outline-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <!-- No Data Message -->
    <c:if test="${empty employeeList}">
        <div class="alert alert-warning text-center">No employees found.</div>
    </c:if>
</div>
</body>
</html>
