package com.bank.ControllerOrServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/otp")
public class OTPValidation extends HttpServlet
{
   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String tempotp=request.getParameter("otp");
	int uotp=Integer.parseInt(tempotp);
	 HttpSession session=request.getSession();
	 Integer otp=(Integer) session.getAttribute("gotp");
	 String emailid=(String) session.getAttribute("useremailid");
	 PrintWriter writer=response.getWriter();
	 response.setContentType("text/html");
	 if (otp!=null) {
		 if (uotp==otp) {
				
				writer.println("<center><h1>Login Successfull....!!!!With EmailId :"+emailid+"</h1></center>");
				
			} else {
				
				RequestDispatcher rd=request.getRequestDispatcher("Password.html");
				rd.include(request, response);
				writer.println("<center><h1>Invalid OTP...!!</h1></center>");

			}
	} else {
		RequestDispatcher rd=request.getRequestDispatcher("Emailid.html");
		rd.include(request, response);
		writer.println("<center><h1>Session TimeOut...!!</h1></center>");

	}
	
}
}
