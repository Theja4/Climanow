package com.theja.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.theja.dao.SignUpDao;
import com.theja.model.User;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private SignUpDao userDao = new SignUpDao();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName =request.getParameter("userName");
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		
		User user=new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		
		 try {
				SignUpDao.registerUser(user);
				session.setAttribute("email", email);
				session.setAttribute("username", userName);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 
		 response.sendRedirect("logindashboardnew.jsp");
	}

}
