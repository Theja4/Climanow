<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Climanow</title>
</head>

  <style><%@include file="/WEB-INF/css/result.css"%></style>
<div class="topnav">
  <a href="dashboard.jsp" style="font-size:30px;color:white;text-align:left;">Climanow</a>
   <div class="search-container">
    <div class="s130">
      <form action="weather" method="get">
        <div class="inner-form" >
          <div class="input-field first-wrap" >            
            <input id="search" type="text" placeholder="Enter city" name="city" />    
            <button id ="searchButton" class="btn-search" type="submit">SEARCH</button>
          </div>      
       	</div>        
      </form>
    </div>    
  </div>
  
  <div style="padding-left:70%;padding-top:10px;">
  
  <a href="login.jsp" style="color:white;text-align:right;">Log in</a>
  <a href="signup.jsp" style="color:white;">Sign up</a></div>
</div>


<%
	double temp = (double) request.getAttribute("temp");
	temp=temp-273;
	long finaltemp = Math.round(temp);
	
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
	
	String path=request.getParameter("img");
	
	
	//out.println("tempmax is : "+finaltempmax+" °C");
	//out.println("tempmin is : "+finaltempmin+" °C");
	//out.println("Feels Like is : "+finalFeelsLike+" °C");
	//out.println("humidity is : "+humidity+" %");
	
	
%>




<main class="main-container">

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
      <div class="current-stats__value"><%out.println(+finaltempmax+"°c"); %></div>
      <div class="current-stats__label">High</div>
      <div class="current-stats__value"><%out.println(+finaltempmin+"°c"); %></div>
      <div class="current-stats__label">Low</div>
    </div>
    <div>
      <div class="current-stats__value">7mph</div>
      <div class="current-stats__label">Wind</div>
      <div class="current-stats__value">0%</div>
      <div class="current-stats__label">Rain</div>
    </div>
    <div>
      <div class="current-stats__value">05:27</div>
      <div class="current-stats__label">Sunrise</div>
      <div class="current-stats__value">20:57</div>
      <div class="current-stats__label">Sunset</div>
    </div>
  </div>

  <div class="weather-by-hour">
    <h2 class="weather-by-hour__heading">Today's weather</h2>
    <div class="weather-by-hour__container">
      <div class="weather-by-hour__item">
        <div class="weather-by-hour__hour">3am</div>
        <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        <div>14&deg;</div>
      </div>
      <div class="weather-by-hour__item">
        <div class="weather-by-hour__hour">6am</div>
        <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        <div>16&deg;</div>
      </div>
      <div class="weather-by-hour__item">
        <div class="weather-by-hour__hour">9am</div>
        <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        <div>17&deg;</div>
      </div>
      <div class="weather-by-hour__item">
        <div class="weather-by-hour__hour">12pm</div>
        <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        <div>19&deg;</div>
      </div>
      <div class="weather-by-hour__item">
        <div class="weather-by-hour__hour">3pm</div>
        <img src="icons/sunny.svg" alt="Sunny">
        <div>21&deg;</div>
      </div>
      <div class="weather-by-hour__item">
        <div class="weather-by-hour__hour">6pm</div>
        <img src="icons/sunny.svg" alt="Sunny">
        <div>20&deg;</div>
      </div>
      <div class="weather-by-hour__item">
        <div class="weather-by-hour__hour">9pm</div>
        <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        <div>18&deg;</div>
      </div>
    </div>
  </div>


  <div class="next-5-days">
    <h2 class="next-5-days__heading">Next 5 days</h2>
    <div class="next-5-days__container">

      <div class="next-5-days__row">

        <div class="next-5-days__date">
          Tue
          <div class="next-5-days__label">30/7</div>
        </div>

        <div class="next-5-days__low">
          10&deg;
          <div class="next-5-days__label">Low</div>
        </div>

        <div class="next-5-days__high">
          21&deg;
          <div class="next-5-days__label">High</div>
        </div>

        <div class="next-5-days__icon">
          <img src="icons/sunny.svg" alt="Sunny">
        </div>

        <div class="next-5-days__rain">
          0%
          <div class="next-5-days__label">Rain</div>
        </div>

        <div class="next-5-days__wind">
          12mph
          <div class="next-5-days__label">Wind</div>
        </div>

      </div>
      <div class="next-5-days__row">

        <div class="next-5-days__date">
          Wed
          <div class="next-5-days__label">31/7</div>
        </div>

        <div class="next-5-days__low">
          9&deg;
          <div class="next-5-days__label">Low</div>
        </div>

        <div class="next-5-days__high">
          18&deg;
          <div class="next-5-days__label">High</div>
        </div>

        <div class="next-5-days__icon">
          <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        </div>

        <div class="next-5-days__rain">
          3%
          <div class="next-5-days__label">Rain</div>
        </div>

        <div class="next-5-days__wind">
          7mph
          <div class="next-5-days__label">Wind</div>
        </div>

      </div>
      <div class="next-5-days__row">

        <div class="next-5-days__date">
          Thur
          <div class="next-5-days__label">1/8</div>
        </div>

        <div class="next-5-days__low">
          7&deg;
          <div class="next-5-days__label">Low</div>
        </div>

        <div class="next-5-days__high">
          15&deg;
          <div class="next-5-days__label">High</div>
        </div>

        <div class="next-5-days__icon">
          <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        </div>

        <div class="next-5-days__rain">
          75%
          <div class="next-5-days__label">Rain</div>
        </div>

        <div class="next-5-days__wind">
          11mph
          <div class="next-5-days__label">Wind</div>
        </div>

      </div>
      <div class="next-5-days__row">

        <div class="next-5-days__date">
          Tue
          <div class="next-5-days__label">2/8</div>
        </div>

        <div class="next-5-days__low">
          12&deg;
          <div class="next-5-days__label">Low</div>
        </div>

        <div class="next-5-days__high">
          24&deg;
          <div class="next-5-days__label">High</div>
        </div>

        <div class="next-5-days__icon">
          <img src="icons/sunny.svg" alt="Sunny">
        </div>

        <div class="next-5-days__rain">
          2%
          <div class="next-5-days__label">Rain</div>
        </div>

        <div class="next-5-days__wind">
          8mph
          <div class="next-5-days__label">Wind</div>
        </div>

      </div>
      <div class="next-5-days__row">

        <div class="next-5-days__date">
          Tue
          <div class="next-5-days__label">30/7</div>
        </div>

        <div class="next-5-days__low">
          10&deg;
          <div class="next-5-days__label">Low</div>
        </div>

        <div class="next-5-days__high">
          21&deg;
          <div class="next-5-days__label">High</div>
        </div>

        <div class="next-5-days__icon">
          <img src="icons/mostly-sunny.svg" alt="Mostly sunny">
        </div>

        <div class="next-5-days__rain">
          0%
          <div class="next-5-days__label">Rain</div>
        </div>

        <div class="next-5-days__wind">
          12mph
          <div class="next-5-days__label">Wind</div>
        </div>

      </div>

    </div>
  </div>

</main>






</body>
</html>