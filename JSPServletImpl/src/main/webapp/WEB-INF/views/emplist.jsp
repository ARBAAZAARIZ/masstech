<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="e" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
	<a href="EmployeeController?action=add">Add Emp</a>

	<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Photo</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Salary</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  	<e:forEach var="emp" items="${emps}">
    <tr>
      <th scope="row">${emp.eid}</th>
      <td><img src="${emp.profile_photo}" height="50" width="50"></td>
      <td>${emp.ename}</td>
      <td>${emp.email}</td>
      <td>${emp.esalary}</td>
      <td>
      <a href="EmployeeController?action=delete&id=${emp.eid}" onclick="return confirm('Are you sure?')"><button type="submit" class="btn btn-sm btn-danger">Delete</button></a>
      <a href="EmployeeController?action=edit&id=${emp.eid}"><button type="submit" class="btn btn-sm btn-success">Edit</button></a>
      </td>
    </tr>
   </e:forEach>
  </tbody>
</table>
</body>
</html>