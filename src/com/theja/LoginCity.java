package com.theja;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theja.model.Login;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Servlet implementation class LoginCity
 */
@WebServlet("/LoginCity")
public class LoginCity extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession session=request.getSession();  	
	if(session.getAttribute("city")==null) {
		request.getRequestDispatcher("/logindashboardnewuser.jsp").forward(request,response);
	    
	}
	else {
	String email=(String) session.getAttribute("email");
	String city= session.getAttribute("city").toString();
	city=city.toLowerCase();
	char[] c=city.toCharArray();
	StringBuilder sk=new StringBuilder();
	for(char ch:c) {
		System.out.print(ch+" ");
		if((ch>=97 && ch<=122)||ch==' ')
			sk.append(ch);
	}
	
	city=c.toString();
	city = city.concat("$");
	//city =  city.replaceAll("/[^A-Za-z]/", "");
	System.out.println("what's the prob "+sk+" this is it");
	StringBuffer sb=new StringBuffer();
	sb.append("https://api.openweathermap.org/data/2.5/weather?q=");
	sb.append(sk);
	sb.append("&appid=bb0c9ba8278ff5cfe5c0dbd617481bed");
	
    String url = sb.toString();
    url=url.replace(' ', '+');
    var req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
    var client = HttpClient.newBuilder().build();        
    
    System.out.println(url);
    HttpResponse<String> resp;
    try {
		resp = client.send(req, HttpResponse.BodyHandlers.ofString());
		String json=resp.body();			
		
		Object obj=JSONValue.parse(json);  
	    JSONObject myresp = (JSONObject) obj;	    

	    request.setAttribute("name", myresp.get("name"));	    
	   
	    request.setAttribute("email", email);
	    
	    
	    Object objsys=JSONValue.parse(myresp.get("sys").toString()); 
	    JSONObject sysresp = (JSONObject) objsys;
	    request.setAttribute("country", sysresp.get("country"));
	    
	    Object objwind=JSONValue.parse(myresp.get("wind").toString()); 
	    JSONObject windresp = (JSONObject) objwind;
	    request.setAttribute("wind", windresp.get("speed"));
	    
	    Object coordwind=JSONValue.parse(myresp.get("coord").toString()); 
	    JSONObject coordresp = (JSONObject) coordwind;
	    request.setAttribute("lon", coordresp.get("lon"));
	    request.setAttribute("lat", coordresp.get("lat"));
	    
	    Object objweather=JSONValue.parse(myresp.get("weather").toString());
	    JSONArray weatherarrayresp=(JSONArray) objweather;
	    JSONObject weatherresp = (JSONObject) weatherarrayresp.get(0);
	    
		Object objmain=JSONValue.parse(myresp.get("main").toString()); 
	    JSONObject mainresp = (JSONObject) objmain;
	    
	    StringBuilder path=new StringBuilder("http://openweathermap.org/img/wn/");
	    path.append(weatherresp.get("icon"));
	    path.append("@2x.png");
	    String imgpath=path.toString();
	    request.setAttribute("img",imgpath);
	    request.setAttribute("description",weatherresp.get("description") );
	    request.setAttribute("temp", mainresp.get("temp"));
	    request.setAttribute("tempmin", mainresp.get("temp_min"));
	    request.setAttribute("tempmax", mainresp.get("temp_max"));
	    request.setAttribute("humidity", mainresp.get("humidity"));
	    request.setAttribute("feels_like", mainresp.get("feels_like"));
	   
	    java.util.Date date=new java.util.Date();  
	    long epoch=Instant.now().getEpochSecond();;  
	    long day=86400;
	    
	    String datestr=date.toString();
	    request.setAttribute("date", datestr);
	    
	    String lon=coordresp.get("lon").toString();
	    String lat=coordresp.get("lat").toString();
	    
	    StringBuffer sbuff=new StringBuffer();
		 sbuff.append("http://api.airvisual.com/v2/nearest_city?lat=");
		 sbuff.append(lat);
		 sbuff.append("&lon=");
		 sbuff.append(lon);
		 sbuff.append("&key=1bf8e445-9963-4988-a183-0f36ebdfee86");
			
		
	        String urlaqi = sbuff.toString();
	        urlaqi=urlaqi.replace(' ', '+');
	        var reqaqi = HttpRequest.newBuilder().GET().uri(URI.create(urlaqi)).build();
	        var clientaqi = HttpClient.newBuilder().build();
	     
	        HttpResponse<String> respaqi;
			try {
				respaqi = client.send(reqaqi, HttpResponse.BodyHandlers.ofString());
				String jsonaqi=respaqi.body();
				Object objaqi=JSONValue.parse(jsonaqi);  
			    JSONObject myrespaqi = (JSONObject) objaqi;
				
			    
			    
			    Object objdata=JSONValue.parse(myrespaqi.get("data").toString()); 
			    JSONObject dataresp = (JSONObject) objdata;
			    
			    
			    
			    Object objcurr=JSONValue.parse(dataresp.get("current").toString()); 
			    JSONObject currresp = (JSONObject) objcurr;
			    
			    
			    
			    Object objpol=JSONValue.parse(currresp.get("pollution").toString()); 
			    JSONObject polresp = (JSONObject) objpol;
			    
			    int aqi=Integer.parseInt(polresp.get("aqius").toString());
			    int aq=0;
			    if(aqi<=50) 
			    {
			    	request.setAttribute("aq", "Good");
			    	request.setAttribute("imgaq","images/1.png");
			    	}
			    else if(aqi>50 && aqi<=100)
			    {
			    	request.setAttribute("aq", "Moderate");
			    	request.setAttribute("imgaq","images/2.png");
			    	}
			    else if(aqi>100 && aqi<=200)
			    {
			    	request.setAttribute("aq", "Unhealthy");
			    	request.setAttribute("imgaq","images/3.png");
			    	}
			    else if(aqi>200)
			    {
			    	request.setAttribute("aq", "Poisonous");
			    	request.setAttribute("imgaq","images/4.png");
			    	}
			    
			    request.setAttribute("aqi", polresp.get("aqius"));				    
			    
			  //https://api.weatherbit.io/v2.0/alerts?lat=17.38&lon=78.48&key=01f66e5e7bbb445ba4fd6ab61b27a070

			    StringBuffer sbuffAlert=new StringBuffer();
				 sbuffAlert.append("https://api.weatherbit.io/v2.0/alerts?lat=");
				 sbuffAlert.append(lat);
				 sbuffAlert.append("&lon=");
				 sbuffAlert.append(lon);
				 sbuffAlert.append("&key=01f66e5e7bbb445ba4fd6ab61b27a070");
				
			    
			    String urlAlert = sbuffAlert.toString();
		        urlAlert=urlAlert.replace(' ', '+');
		        var reqAlert = HttpRequest.newBuilder().GET().uri(URI.create(urlAlert)).build();
		        var clientAlert = HttpClient.newBuilder().build();
		     
		        HttpResponse<String> respAlert;
				try {
					respAlert = client.send(reqAlert, HttpResponse.BodyHandlers.ofString());
					String jsonAlert=respAlert.body();
					Object objAlert=JSONValue.parse(jsonAlert);  
				    JSONObject myrespAlert = (JSONObject) objAlert;
				    System.out.println("NNNNNNNNN"+urlAlert);
				    
				    System.out.println("NNNNNNNNN"+myrespAlert.toJSONString());
				    
				    Object objAlertArray=JSONValue.parse(myrespAlert.get("alerts").toString());
				    JSONArray arrayAlert=(JSONArray) objAlertArray;
				    if(arrayAlert.isEmpty()) {
				    	session.setAttribute("title", "No Alerts have a nice day");
				    }
				    else {
					    JSONObject weatherAlert = (JSONObject)arrayAlert.get(0);
					    String alert=(String) weatherAlert.get("title");
					    session.setAttribute("title", alert);
					    System.out.println("alertLogin city"+alert);
				    }
				    
				    
				  // request.getRequestDispatcher("/result.jsp").forward(request,response);
				    System.out.println("Should redirect");
				    
			        request.getRequestDispatcher("/logindashboard.jsp").forward(request,response);
				    
				    
				    
				}
				 catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        
			    
			    
			}
			 catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    
	    
	    
	    
	     System.out.println(myresp);
	     System.out.println(epoch);		     
    }catch (IOException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	}
	}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession session=request.getSession();  	
	if(session.getAttribute("city")==null) {
		
	}
	else {
	String email=(String) session.getAttribute("email");
	String city= session.getAttribute("city").toString();
	city=city.toLowerCase();
	char[] c=city.toCharArray();
	StringBuilder sk=new StringBuilder();
	for(char ch:c) {
		System.out.print(ch+" ");
		if((ch>=97 && ch<=122)||ch==' ')
			sk.append(ch);
	}
	
	city=c.toString();
	city = city.concat("$");
	//city =  city.replaceAll("/[^A-Za-z]/", "");
	System.out.println("what's the prob "+sk+" this is it");
	StringBuffer sb=new StringBuffer();
	sb.append("https://api.openweathermap.org/data/2.5/weather?q=");
	sb.append(sk);
	sb.append("&appid=bb0c9ba8278ff5cfe5c0dbd617481bed");
	
    String url = sb.toString();
    url=url.replace(' ', '+');
    var req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
    var client = HttpClient.newBuilder().build();        
    
    System.out.println(url);
    HttpResponse<String> resp;
    try {
		resp = client.send(req, HttpResponse.BodyHandlers.ofString());
		String json=resp.body();			
		
		Object obj=JSONValue.parse(json);  
	    JSONObject myresp = (JSONObject) obj;	    

	    request.setAttribute("name", myresp.get("name"));	    

	    request.setAttribute("email", email);
	    
	    
	    Object objsys=JSONValue.parse(myresp.get("sys").toString()); 
	    JSONObject sysresp = (JSONObject) objsys;
	    request.setAttribute("country", sysresp.get("country"));
	    
	    Object objwind=JSONValue.parse(myresp.get("wind").toString()); 
	    JSONObject windresp = (JSONObject) objwind;
	    request.setAttribute("wind", windresp.get("speed"));
	    
	    Object coordwind=JSONValue.parse(myresp.get("coord").toString()); 
	    JSONObject coordresp = (JSONObject) coordwind;
	    request.setAttribute("lon", coordresp.get("lon"));
	    request.setAttribute("lat", coordresp.get("lat"));
	    
	    Object objweather=JSONValue.parse(myresp.get("weather").toString());
	    JSONArray weatherarrayresp=(JSONArray) objweather;
	    JSONObject weatherresp = (JSONObject) weatherarrayresp.get(0);
	    
		Object objmain=JSONValue.parse(myresp.get("main").toString()); 
	    JSONObject mainresp = (JSONObject) objmain;
	    
	    StringBuilder path=new StringBuilder("http://openweathermap.org/img/wn/");
	    path.append(weatherresp.get("icon"));
	    path.append("@2x.png");
	    String imgpath=path.toString();
	    request.setAttribute("img",imgpath);
	    request.setAttribute("description",weatherresp.get("description") );
	    request.setAttribute("temp", mainresp.get("temp"));
	    request.setAttribute("tempmin", mainresp.get("temp_min"));
	    request.setAttribute("tempmax", mainresp.get("temp_max"));
	    request.setAttribute("humidity", mainresp.get("humidity"));
	    request.setAttribute("feels_like", mainresp.get("feels_like"));
	   
	    java.util.Date date=new java.util.Date();  
	    long epoch=Instant.now().getEpochSecond();;  
	    long day=86400;
	    
	    String datestr=date.toString();
	    request.setAttribute("date", datestr);
	    
	    String lon=coordresp.get("lon").toString();
	    String lat=coordresp.get("lat").toString();
	    
	    StringBuffer sbuff=new StringBuffer();
		 sbuff.append("http://api.airvisual.com/v2/nearest_city?lat=");
		 sbuff.append(lat);
		 sbuff.append("&lon=");
		 sbuff.append(lon);
		 sbuff.append("&key=1bf8e445-9963-4988-a183-0f36ebdfee86");
			
		
	        String urlaqi = sbuff.toString();
	        urlaqi=urlaqi.replace(' ', '+');
	        var reqaqi = HttpRequest.newBuilder().GET().uri(URI.create(urlaqi)).build();
	        var clientaqi = HttpClient.newBuilder().build();
	     
	        HttpResponse<String> respaqi;
			try {
				respaqi = client.send(reqaqi, HttpResponse.BodyHandlers.ofString());
				String jsonaqi=respaqi.body();
				Object objaqi=JSONValue.parse(jsonaqi);  
			    JSONObject myrespaqi = (JSONObject) objaqi;
				
			    
			    
			    Object objdata=JSONValue.parse(myrespaqi.get("data").toString()); 
			    JSONObject dataresp = (JSONObject) objdata;
			    
			    
			    
			    Object objcurr=JSONValue.parse(dataresp.get("current").toString()); 
			    JSONObject currresp = (JSONObject) objcurr;
			    
			    
			    
			    Object objpol=JSONValue.parse(currresp.get("pollution").toString()); 
			    JSONObject polresp = (JSONObject) objpol;
			    
			    int aqi=Integer.parseInt(polresp.get("aqius").toString());
			    int aq=0;
			    if(aqi<=50) 
			    {
			    	request.setAttribute("aq", "Good");
			    	request.setAttribute("imgaq","images/1.png");
			    	}
			    else if(aqi>50 && aqi<=100)
			    {
			    	request.setAttribute("aq", "Moderate");
			    	request.setAttribute("imgaq","images/2.png");
			    	}
			    else if(aqi>100 && aqi<=200)
			    {
			    	request.setAttribute("aq", "Unhealthy");
			    	request.setAttribute("imgaq","images/3.png");
			    	}
			    else if(aqi>200)
			    {
			    	request.setAttribute("aq", "Poisonous");
			    	request.setAttribute("imgaq","images/4.png");
			    	}
			    
			    request.setAttribute("aqi", polresp.get("aqius"));				    
			    
			  //https://api.weatherbit.io/v2.0/alerts?lat=17.38&lon=78.48&key=01f66e5e7bbb445ba4fd6ab61b27a070

			    StringBuffer sbuffAlert=new StringBuffer();
				 sbuffAlert.append("https://api.weatherbit.io/v2.0/alerts?lat=");
				 sbuffAlert.append(lat);
				 sbuffAlert.append("&lon=");
				 sbuffAlert.append(lon);
				 sbuffAlert.append("&key=01f66e5e7bbb445ba4fd6ab61b27a070");
				
			    
			    String urlAlert = sbuffAlert.toString();
		        urlAlert=urlAlert.replace(' ', '+');
		        var reqAlert = HttpRequest.newBuilder().GET().uri(URI.create(urlAlert)).build();
		        var clientAlert = HttpClient.newBuilder().build();
		     
		        HttpResponse<String> respAlert;
				try {
					respAlert = client.send(reqAlert, HttpResponse.BodyHandlers.ofString());
					String jsonAlert=respAlert.body();
					Object objAlert=JSONValue.parse(jsonAlert);  
				    JSONObject myrespAlert = (JSONObject) objAlert;
				    System.out.println("NNNNNNNNN"+urlAlert);
				    
				    System.out.println("NNNNNNNNN"+myrespAlert.toJSONString());
				    
				    Object objAlertArray=JSONValue.parse(myrespAlert.get("alerts").toString());
				    JSONArray arrayAlert=(JSONArray) objAlertArray;
				    if(arrayAlert.isEmpty()) {
				    	session.setAttribute("title", "No Alerts have a nice day");
				    }
				    else {
					    JSONObject weatherAlert = (JSONObject)arrayAlert.get(0);
					    String alert=(String) weatherAlert.get("title");
					    session.setAttribute("title", alert);
					    System.out.println("alertLogin city"+alert);
				    }
				    
				    
				  // request.getRequestDispatcher("/result.jsp").forward(request,response);
				    System.out.println("Should redirect");
				    
			        request.getRequestDispatcher("/logindashboard.jsp").forward(request,response);
				    
				    
				    
				}
				 catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        
			    
			    
			}
			 catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    
	    
	    
	    
	     System.out.println(myresp);
	     System.out.println(epoch);		     
    }catch (IOException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	}
    
    
}

}
