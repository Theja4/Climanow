package com.theja.dao;

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

/**
 * Servlet implementation class userDao
 */
@WebServlet("/userDao")
public class userDao extends HttpServlet {
	 public static String getUserName(Login login) throws ClassNotFoundException {
	       
	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("select username from users where email = ?")) {
	            preparedStatement.setString(1, login.getEmail());

	            System.out.println(preparedStatement);
	            ResultSet result = preparedStatement.executeQuery();
	            
	            if(result.next()) {
	            String username = result.getString(1);

	            System.out.println("uname"+username);
	            return username;
	            
	            }
	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
			return "";
	        
	    }

	    
	    public static String getCity(Login login) throws ClassNotFoundException {
		       
	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("select city from city where email = ?")) {
	            preparedStatement.setString(1, login.getEmail());

	            System.out.println(preparedStatement);
	            ResultSet result = preparedStatement.executeQuery();
	            
	            if(result.next()) {
	            String city= result.getString(1);

	            return city;
	            
	            }
	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
			return "";
	        
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
