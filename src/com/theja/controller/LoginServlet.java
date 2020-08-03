
package com.theja.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.theja.model.Login;
import com.theja.dao.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private LoginDao loginDao;

	    public void init() {
	        loginDao = new LoginDao();
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        Login login = new Login();
	        login.setEmail(email);
	        login.setPassword(password);
	        try {
	            if (loginDao.validate(login)) {
	                HttpSession session = request.getSession();
	                session.setAttribute("email",email);
	                response.sendRedirect("logindashboard.jsp");
	            } else {
	                HttpSession session = request.getSession();
	                response.sendRedirect("login.jsp");
	                //session.setAttribute("user", email);
	                //response.sendRedirect("login.jsp");
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		
		
	}

}
