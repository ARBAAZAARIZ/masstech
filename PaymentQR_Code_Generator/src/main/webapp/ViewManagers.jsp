<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<thead>

<tr>
 <th>ID</th>
 <th>Manager Name</th>
 <th>Action</th>
</tr>
</thead>

<tbody>


<c:forEach var="m" items="${mamangerList}">
<tr>
<td>${m.id}</td>
<td>${m.mname}</td>

<td><button class="btn btn-danger"><a href="ManagerDeleteController?mid=${m.id}">Delete</a></button></td>
</tr>
</c:forEach>


</tbody>

</table>

</body>
</html>