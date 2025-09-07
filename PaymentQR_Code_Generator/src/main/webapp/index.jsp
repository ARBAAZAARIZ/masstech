<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="get" action="GenerateUPIQrServlet">
    Amount: <input type="text" name="amount" />
    <br>
    UPI ID: <input type="text" name="upiId" />
    <br>
    <button type="submit">Generate QR</button>
</form>

<% 
    String upiId = request.getParameter("upiId");
    String amount = request.getParameter("amount");
    if (upiId != null && amount != null) {
%>
    <img src="GenerateUPIQrServlet?upiId=<%=upiId%>&amount=<%=amount%>" alt="UPI QR Code" />
<% } %>


<br>
<br>

<button>
<a href="ManagerController"> Add mamanger</a> 
</button>

<button>
<a href="RazorPayServlet"> Pay Razorpay</a>
</button>

</body>
</html>