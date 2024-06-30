package com.bank.ControllerOrServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/emailidvalidation")
public class EmailIdVerification  extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String emailid=request.getParameter("emailid");
    	
    	String select="select * from userinformation where USER_EMAIL_ID=?";
    	HttpSession session=request.getSession();
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection connection=
			DriverManager.getConnection("jdbc:mysql://localhost:3306/teca54?user=root&password=12345");
    		PreparedStatement ps=connection.prepareStatement(select);
    		ps.setString(1, emailid);
    		ResultSet resultset=ps.executeQuery();
    		PrintWriter writer=response.getWriter();
    		response.setContentType("text/html");
    		if (resultset.next()) {
    			
    			session.setAttribute("useremailid", emailid);
    			
    			RequestDispatcher rd=request.getRequestDispatcher("Password.html");
    			rd.forward(request, response);
				
			} else {
               RequestDispatcher rd=request.getRequestDispatcher("Emailid.html");
               rd.include(request, response);
               writer.println("<center><h1>Invalid EmailId</h1></center>");
               
			}
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
