package com.bank.ControllerOrServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet ("/registration")
public class UserRegistartion extends HttpServlet  
{
   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String name=request.getParameter("name");
	 String emailid=request.getParameter("emailid");
	 String password=request.getParameter("password");
	 
	 System.out.println(name);
	 System.out.println(emailid);
	 System.out.println(password);
}
}
