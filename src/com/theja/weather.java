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
import java.time.Instant;
import java.util.Date;

@WebServlet("/weather")
public class weather extends HttpServlet {
	
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String s=request.getParameter("city");
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.openweathermap.org/data/2.5/weather?q=");
		sb.append(s);
		sb.append("&appid=bb0c9ba8278ff5cfe5c0dbd617481bed");
		

        String url = sb.toString();
        url=url.replace(' ', '+');
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
		    
		     System.out.println(myresp);
		     System.out.println(epoch);		     
		     
		     String lon=coordresp.get("lon").toString();
		     String lat=coordresp.get("lat").toString();
		     
		     StringBuilder sone=new StringBuilder("http://api.openweathermap.org/data/2.5/onecall/timemachine?lat=");
		     sone.append(lat);
		     sone.append("&lon=");
		     sone.append(lon);
		     sone.append("&dt=");
		     sone.append((epoch-day));
		     sone.append("&appid=bb0c9ba8278ff5cfe5c0dbd617481bed");
		     
		     String urlone = sone.toString();
		     	
		        urlone=urlone.replace(' ', '+');
		        var reqone = HttpRequest.newBuilder().GET().uri(URI.create(urlone)).build();
		        var clientone = HttpClient.newBuilder().build();
		     
		        HttpResponse<String> respone;
		        
		        try {
		        	respone = client.send(reqone, HttpResponse.BodyHandlers.ofString());
					String jsonone=respone.body();
					
					Object objone=JSONValue.parse(jsonone);  
				    JSONObject myrespone = (JSONObject) objone;
				    
				    System.out.println(myrespone.toJSONString());
				    
				    Object objcurrent=JSONValue.parse(myrespone.get("current").toString()); 
				    JSONObject current = (JSONObject) objcurrent;
				    request.setAttribute("temp-1", current.get("temp"));
				    request.setAttribute("humidity1", current.get("humidity"));
				    request.setAttribute("wind_speed-1", current.get("wind_speed"));
				    String day1=current.get("dt").toString();
				    Date date1 = new Date(Long.parseLong(day1)*1000);
				    System.out.println(date1);
				    String showday1=date.toString().substring(0, 4);
				    String showmonth1=date.toString().substring(4, 10);
				    request.setAttribute("day1",showday1 );
				    request.setAttribute("month1",showmonth1 );
				    
				    Object weatherone=JSONValue.parse(current.get("weather").toString());
				    JSONArray weatherarrayone=(JSONArray) objweather;
				    JSONObject weatherrespone = (JSONObject) weatherarrayone.get(0);

				    request.setAttribute("description-1", weatherrespone.get("description"));
				   
				    
				    StringBuilder imgsb=new StringBuilder("http://openweathermap.org/img/wn/");
				    imgsb.append(weatherrespone.get("icon"));
				    imgsb.append(".png");
				    String img1=imgsb.toString();
				    request.setAttribute("img1", img1);
					   
				     System.out.println(img1);
		        }
		        catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     
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
				    
				    request.getRequestDispatcher("/result.jsp").forward(request,response);
				     
				    
				    
				    
				}
				 catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		     
		     
		     
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
       
		
		
	}

}
