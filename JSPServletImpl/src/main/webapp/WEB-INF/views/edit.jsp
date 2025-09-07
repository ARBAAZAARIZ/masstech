<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="e" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="AddEmpServlet" enctype="multipart/form-data">
		ID <input type="text" readonly="readonly" name="eid" value="${em.eid}"><br><br>
		
		Name <input type="text" name="ename" value="${em.ename}"><br><br>
		
		Email <input type="text" name="email" value="${em.email}"><br><br>
		
		Salary <input type="text" name="esalary" value="${em.esalary}"><br><br>
		
		<img src="${em.profile_photo}">
		Profile Photo <input type="file" name="profile_photo"><br><br>
	
		<input type="submit" value="Save">
	</form>
</body>
</html>