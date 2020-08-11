<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style><%@include file="/WEB-INF/css/logindashboard.css"%></style>
<%

	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //its for HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //Http 1.0
			
	if(request.getAttribute("email")==null){
	response.sendRedirect("login.jsp");
	}
	
	

%>



<%
	//HttpSession session=request.getSession();  
	String email=session.getAttribute("email").toString();
	//request.setAttribute("email",email);	

	double temp = (double) request.getAttribute("temp");
	temp=temp-273;
	long finaltemp = Math.round(temp);
	
	String city=request.getAttribute("name").toString();
	
	double tempmin = (double) request.getAttribute("tempmin");
	tempmin=tempmin-273;
	long finaltempmin = Math.round(tempmin);
	
	double tempmax = (double) request.getAttribute("tempmax");
	tempmax=tempmax-273;
	long finaltempmax = Math.round(tempmax);
	
	double feelsLike = (double) request.getAttribute("feels_like");
	feelsLike=feelsLike-273;
	long finalFeelsLike = Math.round(feelsLike);
	
	String weather = request.getAttribute("description").toString();
	String name = request.getAttribute("name").toString();
	String country = request.getAttribute("country").toString();
	String humidity = request.getAttribute("humidity").toString();
	String date = request.getAttribute("date").toString();
	String wind = request.getAttribute("wind").toString();
	String aqi = request.getAttribute("aqi").toString();
	String aq = request.getAttribute("aq").toString();
	String path=request.getParameter("img");
	
	
	//out.println("tempmax is : "+finaltempmax+" °C");feelslike-1
	//out.println("tempmin is : "+finaltempmin+" °C");
	//out.println("Feels Like is : "+finalFeelsLike+" °C");
	//out.println("humidity is : "+humidity+" %");
	
	
%>

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




<main class="main-container" style="padding-top:1%;">

  <div class="location-and-date">
    <h1 class="location-and-date__location"><%out.println(name +", "+ country); %></h1>
    <div><%out.println(date); %></div>
  </div>


  <div class="current-temperature">
    <div class="current-temperature__icon-container">
      <img src="${img}" alt="YYYY">
    </div>
    <div class="current-temperature__content-container">
      <div class="current-temperature__value"><% out.println(finaltemp+"°c"); %></div>
      <div class="current-temperature__summary"><% out.println(weather); %></div>
    </div>
  </div>


  <div class="current-stats">
    <div>
      <div class="current-stats__value"><%out.println(finaltempmax+"°c"); %></div>
      <div class="current-stats__label">High</div>
      <div class="current-stats__value"><%out.println(finaltempmin+"°c"); %></div>
      <div class="current-stats__label">Low</div>
    </div>
    <div>
      <div class="current-stats__value"><%out.println(finalFeelsLike); %></div>
      <div class="current-stats__label">Feels Like</div>
      <div class="current-stats__value"><%out.println(wind+" "+"mph"); %></div>
      <div class="current-stats__label">Wind</div>
    </div>
   
  </div>
	
	<div class="current-stats2">
    <div>
      <div class="current-stats__value"><%out.println(aqi); %></div>
      <div class="current-stats__label">US AQI</div>
      <div class="current-stats__value"><%out.println(aq); %></div>
      <div class="current-stats__label">Air Quality</div>
    </div>
    <div>
      <img class="testimg" alt="hi" src="${imgaq}">
    </div>
   
  </div>
	
</body>
</html>