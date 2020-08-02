package com.theja;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  
import org.json.simple.JSONValue;  

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


@WebServlet("/weather")
public class weather extends HttpServlet {
	
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String s=request.getParameter("city");
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.openweathermap.org/data/2.5/weather?q=");
		sb.append(s);
		sb.append("&appid=bb0c9ba8278ff5cfe5c0dbd617481bed");
		

        var url = sb.toString();
        System.out.println(url);
        var req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
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
		      
		    String datestr=date.toString();
		    request.setAttribute("date", datestr);
		    
		     System.out.println(myresp);
		     System.out.println(weatherresp.get("icon"));
		     System.out.println(imgpath);
		     
		     request.getRequestDispatcher("/result.jsp").forward(request,response);
		     
		     
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
       
		
		
	}

}
