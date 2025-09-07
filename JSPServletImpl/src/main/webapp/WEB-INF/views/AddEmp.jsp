<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="AddEmpServlet" enctype="multipart/form-data">
		Name <input type="text" name="ename"><br><br>
		
		Email <input type="text" name="email"><br><br>
		
		Salary <input type="text" name="esalary"><br><br>
		
		Profile Photo <input type="file" name="profile_photo"><br><br>
	
		<input type="submit" value="Save">
	</form>
</body>
</html>