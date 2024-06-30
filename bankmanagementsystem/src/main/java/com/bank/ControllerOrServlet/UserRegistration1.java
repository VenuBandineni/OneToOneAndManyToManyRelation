package com.bank.ControllerOrServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/registration1")
public class UserRegistration1 extends HttpServlet
{
           @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	String name=request.getParameter("name");
        	String emailid=request.getParameter("emailid");
        	
        	System.out.println(name);
        	System.out.println(emailid);
        }
}
