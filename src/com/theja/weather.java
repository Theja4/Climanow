package com.theja;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        var req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        HttpResponse<String> resp;
		try {
			resp = client.send(req, HttpResponse.BodyHandlers.ofString());
			String json=resp.body();
			
			
			Object obj=JSONValue.parse(json);  
		    JSONObject myresp = (JSONObject) obj;
		    
		    
			Object obj1=JSONValue.parse(myresp.get("main").toString()); 
		    JSONObject mysubresp = (JSONObject) obj1;
		    
		    request.setAttribute("temp", mysubresp.get("temp"));
		    
		     System.out.println(myresp);
		     
		     request.getRequestDispatcher("/result.jsp").forward(request,response);
		     
		     
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
       
		
		
	}

}
