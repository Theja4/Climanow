<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
     
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="images/favicon.ico" >
    
<meta >

<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
        <title>Climanow</title>
        <script src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js"></script>
        <script src="https://api.windy.com/assets/map-forecast/libBoot.js"></script>
        <style>
            #windy {
                width: 100%;
                height: 300px;
            }
        </style>
</head>

  <style><%@include file="/WEB-INF/css/loginresult.css"%></style>
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


<%
response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //its for HTTP 1.1
response.setHeader("Pragma", "no-cache"); //Http 1.0
		
	if(session.getAttribute("email")==null){
		response.sendRedirect("login.jsp");
	}	
String email=session.getAttribute("email").toString();
request.setAttribute("email",email);	


	double temp = (double) request.getAttribute("temp");
	temp=temp-273;
	long finaltemp = Math.round(temp);
	
	double temp1 = (double) request.getAttribute("temp-1");
	temp1=temp1-273;
	long finaltemp1 = Math.round(temp1);
	
	double tempmin = (double) request.getAttribute("tempmin");
	tempmin=tempmin-273;
	long finaltempmin = Math.round(tempmin);
	
	double tempmax = (double) request.getAttribute("tempmax");
	tempmax=tempmax-273;
	long finaltempmax = Math.round(tempmax);
	
	double feelsLike = (double) request.getAttribute("feels_like");
	feelsLike=feelsLike-273;
	long finalFeelsLike = Math.round(feelsLike);
	

	String day1 = request.getAttribute("day1").toString();
	String weather = request.getAttribute("description").toString();
	String description1 = request.getAttribute("description-1").toString();
	String name = request.getAttribute("name").toString();
	request.setAttribute("name",name);
	String country = request.getAttribute("country").toString();
	String humidity = request.getAttribute("humidity").toString();
	String date = request.getAttribute("date").toString();
	String wind = request.getAttribute("wind").toString();
	String aqi = request.getAttribute("aqi").toString();
	String aq = request.getAttribute("aq").toString();
	String path=request.getParameter("img");
	String img1=request.getParameter("img1");
	
	String humidity1=request.getAttribute("humidity1").toString();
	String wind1 = request.getAttribute("wind_speed-1").toString();
	//out.println("tempmax is : "+finaltempmax+" °C");feelslike-1
	//out.println("tempmin is : "+finaltempmin+" °C");
	//out.println("Feels Like is : "+finalFeelsLike+" °C");
	//out.println("humidity is : "+humidity+" %");
	
	
%>




<main class="main-container" style="padding-top:1%;">

  <div class="location-and-date">
    <h1 class="location-and-date__location"><%out.println(name +", "+ country); %></h1>
    <div><%out.println(date); %></div>
  </div>
<form action="<%=request.getContextPath()%>/pin" method="post">
		<input type="hidden" name="newcity" value="<%out.println(name);%>" />
		<button class ="pin-button" type="submit" >pin </button>
	</form>
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



  <div class="next-5-days">
    <h2 class="next-5-days__heading">Last 5 days</h2>
    <div class="next-5-days__container">
	<div class="next-5-days__row">

        <div class="next-5-days__date">          
          <%out.println(day1);%>
        </div>

        <div class="next-5-days__low">
          <%out.println(finaltemp1+"°c"); %>
          <div class="next-5-days__label">temp</div>
        </div>

        <div class="next-5-days__high">
          <%out.println(humidity1+"%"); %>
          <div class="next-5-days__label">humidity</div>
        </div>

        <div class="next-5-days__icon">
        <%out.println(description1); %>
        </div>

        <div class="next-5-days__rain">
          <%out.println(wind1+" kmph"); %>
          <div class="next-5-days__label">Wind</div>
        </div>

        <div class="next-5-days__wind">
          <img alt="1" src="${img1}">
        </div>

      </div><div class="next-5-days__row">

        <div class="next-5-days__date">          
          <%out.println(day1);%>
        </div>

        <div class="next-5-days__low">
          <%out.println(finaltemp1+"°c"); %>
          <div class="next-5-days__label">temp</div>
        </div>

        <div class="next-5-days__high">
          <%out.println(humidity1+"%"); %>
          <div class="next-5-days__label">humidity</div>
        </div>

        <div class="next-5-days__icon">
        <%out.println(description1); %>
        </div>

        <div class="next-5-days__rain">
          <%out.println(wind1+" kmph"); %>
          <div class="next-5-days__label">Wind</div>
        </div>

        <div class="next-5-days__wind">
          <img alt="1" src="${img1}">
        </div>

      </div>
      <div class="next-5-days__row">

        <div class="next-5-days__date">          
          <%out.println(day1);%>
        </div>

        <div class="next-5-days__low">
          <%out.println(finaltemp1+"°c"); %>
          <div class="next-5-days__label">temp</div>
        </div>

        <div class="next-5-days__high">
          <%out.println(humidity1+"%"); %>
          <div class="next-5-days__label">humidity</div>
        </div>

        <div class="next-5-days__icon">
        <%out.println(description1); %>
        </div>

        <div class="next-5-days__rain">
          <%out.println(wind1+" kmph"); %>
          <div class="next-5-days__label">Wind</div>
        </div>

        <div class="next-5-days__wind">
          <img alt="1" src="${img1}">
        </div>

      </div>
      
</main>
<img alt="1" src="">
<div id="windy"></div>
        <script >
            const options = {
    key: 'CB9zM1BVX8QoqKdXavUinECL4AYNQKJM', 
    // Put additional console output
    verbose: true,

    // Optional: Initial state of the map
    lat: "${lat}", 
    lon: "${lon}",
    zoom: 5,
};

// Initialize Windy API
windyInit(options, windyAPI => {
    // windyAPI is ready, and contain 'map', 'store',
    // 'picker' and other usefull stuff

    const { map } = windyAPI;
    // .map is instance of Leaflet map

    L.popup()
        .setLatLng(["${lat}", "${lon}"])
        .setContent('here')
        .openOn(map);
});
        </script>

</body>
</html>