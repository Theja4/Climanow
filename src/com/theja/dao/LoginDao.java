package com.theja.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.theja.model.Login;

public class LoginDao {
	 public boolean validate(Login login) throws ClassNotFoundException {
	        boolean status = false;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/Climanow?useSSL=false", "root", "1234");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("select * from users where email = ? and pass = ? ")) {
	            preparedStatement.setString(1, login.getEmail());
	            preparedStatement.setString(2, login.getPassword());

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return status;
	    }

	    private void printSQLException(SQLException ex) {
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
