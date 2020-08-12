package com.theja.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.theja.model.Login;


@WebServlet("/pin")
public class pin extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		 HttpSession session = request.getSession();
		 String email=session.getAttribute("email").toString();
			request.setAttribute("email",email);	

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");
        		
            PreparedStatement preparedStatement = connection
            .prepareStatement("select city from city where email = ?")) {
            preparedStatement.setString(1, email);

            System.out.println(preparedStatement);
            ResultSet result = preparedStatement.executeQuery();
           
            if(result.next()) {

            String city= result.getString(1);
            String newcity=request.getParameter("newcity").toString();
          
	        try (Connection connectionexists = DriverManager
		            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");

		            PreparedStatement preparedStatementexists = connectionexists
		            .prepareStatement("delete from city where email=?")) {
	        	
	        	preparedStatementexists.setString(1, email);
	        	System.out.println(preparedStatementexists);
                
	        	preparedStatementexists.execute();
	        	try (Connection connectionadd = DriverManager
			            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");

			            PreparedStatement preparedStatementadd = connectionadd
			            .prepareStatement("insert into city values(?,?)")) {		        	
		        	preparedStatementadd.setString(1, email);
		        	preparedStatementadd.setString(2, newcity);
		        	System.out.println(preparedStatementadd);
	                
		        	preparedStatementadd.execute();
		        	
		        	System.out.println(city);

	                System.out.println(newcity);
	                System.out.println(preparedStatementexists);
		        }
		        catch (SQLException e) {
		            printSQLException(e);
		        } 
	        	System.out.println(city);

                System.out.println(newcity);
                System.out.println(preparedStatementexists);
	        }
	        catch (SQLException e) {
	            printSQLException(e);
	        }           
	        
	        
	        session.setAttribute("city", newcity);
	        response.sendRedirect("LoginCity"); 
            }	            
            else {
            	try (Connection connectionexists = DriverManager
			            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");

			            PreparedStatement preparedStatementexists = connectionexists
			            .prepareStatement("INSERT INTO city (email, city) VALUES  (?, ?); ")) {

                    
    	            String newcity=(String) session.getAttribute("city");
            		preparedStatementexists.setString(1, email);
		        	preparedStatementexists.setString(2, newcity);
		        	System.out.println("inside pin"+preparedStatementexists);
		        	preparedStatementexists.execute();
		        	
		        	request.setAttribute("city",newcity);
		        	response.sendRedirect("LoginCity"); 
		        	//request.getRequestDispatcher("LoginCity").forward(request,response);
				     
		        	
		        }
		        catch (SQLException e) {
		           printSQLException(e);
		        }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
        }
	
	
}



