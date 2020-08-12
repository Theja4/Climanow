<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style><%@include file="/WEB-INF/css/logindashboardnew.css"%></style>


<body>
<div class="topnav">

  <a href="logindashboard.jsp" style="font-size:30px;color:white;text-align:left;">Climanow</a>
   <div  style="padding-top:8px;float:right;">
   
	<form action="<%=request.getContextPath()%>/logout" method="post">
		<button class ="logout-button" type="submit" >log out</button>
	</form>
  
  </div> 
  <form action="LoginWeather" method="get">
  <div class="search-container">
    <input id="search" type="text" placeholder=" Enter city" name="city" />    
    <button  class="fa fa-search" type="submit">SEARCH</button>
  </div>
 </form>
</div>
<h1 style="text-align:center; position: absolute;top: 35%;left: 35%; color:white;">You can pin any location to your<br> dashboard!</h1>
	
</body>
</html>