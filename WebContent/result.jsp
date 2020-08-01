<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

  <style><%@include file="/WEB-INF/css/result.css"%></style>
<body>
<h1>
<%
	double temp = (double) request.getAttribute("temp");
	temp=temp-273;
	long finaltemp = Math.round(temp);
	out.print("temp is : "+finaltemp+" °C");
	
%>
</h1>
<img src="http://openweathermap.org/img/w/10d.png" alt="Girl in a jacket">
</body>
</html>