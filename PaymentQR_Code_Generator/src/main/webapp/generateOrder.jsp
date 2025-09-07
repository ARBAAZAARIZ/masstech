<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>

</head>
<body>
<script>
        var options = {
            "key": "<%= request.getAttribute("key") %>", 
            "amount": "<%= request.getAttribute("amount") %>", 
            "currency": "INR",
            "name": "My App",
            "description": "Test Transaction",
            "order_id": "<%= request.getAttribute("orderId") %>",
            "handler": function (response){
                alert("Payment Successful! Payment ID: " + response.razorpay_payment_id);
                window.location.href("/recipt.jsp");
                
            },
            "prefill": {
                "name": "Krish Kheloji",
                "email": "khelojikrish@gmail.com",
                "contact": "7208921898"
            },
            "theme": {
                "color": "#3399cc"
            }
        };
        var rzp1 = new Razorpay(options);
        rzp1.open();
    </script>
</body>
</html>