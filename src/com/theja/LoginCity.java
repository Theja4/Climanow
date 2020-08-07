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
		String city="";
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("select city from city where email = ?")) {
	            preparedStatement.setString(1, (String) request.getAttribute("email"));

	            System.out.println(preparedStatement);
	            ResultSet result = preparedStatement.executeQuery();
	            
	            if(result.next()) {
	            city= result.getString(1);

	            
	            }
	        } catch (Exception e) {
	        	
	        }
		
    	HttpSession session = request.getSession();
    	
		String url=city;
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
				    
				  // request.getRequestDispatcher("/result.jsp").forward(request,response);

			        request.getRequestDispatcher("/logindashboard.jsp").forward(request,response);
				    
				    
				    
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
