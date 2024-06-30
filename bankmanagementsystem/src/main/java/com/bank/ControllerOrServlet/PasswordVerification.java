package com.bank.ControllerOrServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/passwordvalidation")
public class PasswordVerification extends HttpServlet
{
   /*=>To Transfer Any value from one servlet class to another servlet class we make use of session object
    * =>In session object data or values are not permanent.It is used to transfer the data from one servlet class to another servlet class
    * =>To set any value in session object we make of the method setAttribute() method which is present in HttpSession interface to get
    *  an object to interface we make use of getSession() which is present in HttpServletRequest interface setAttribute() is double argument method
    *  for the first argument pass the reference variable to the storing value,for the second argument pass the value which is going to store in session object
    *  */
	
	/* =>To get the value from the session object we make use of getAttribute() which is present in HttpSession interface to get an object
	 * to interface we make use of getSession() which is present in HttpServletRequest.
	 * getAttribute() is single argument method,for the argument specify the reference variable as argument.
	 * The return type of the method is object class it will store the value with in the object
	 * class from the object class we need to downcast to requires
	 * Wrapper class.
	 * */
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String password=request.getParameter("password");
    	
    	String select="select * from userinformation where USER_PASSWORD=? and USER_EMAIL_ID=?";
    	HttpSession session=request.getSession();
    	String emailid=(String) session.getAttribute("useremailid");
    	PrintWriter writer=response.getWriter();
		response.setContentType("text/html");
    	if(emailid!=null) {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection connection=
			DriverManager.getConnection("jdbc:mysql://localhost:3306/teca54?user=root&password=12345");
    		PreparedStatement ps=connection.prepareStatement(select);
    		ps.setString(1, password);
    		ps.setString(2, emailid);
    		ResultSet resultset=ps.executeQuery();
    		
    		
    		if (resultset.next()) {
    			Random random=new Random();
    			int otp=random.nextInt(10000); 
    			
    			if(otp<1000) {
    				otp+=1000;
    			}
    			session.setAttribute("gotp", otp);
    			session.setMaxInactiveInterval(10);//It is used to delete or vanish from the session object
    			RequestDispatcher rd=request.getRequestDispatcher("Otp.html");
    			rd.include(request, response);
    			writer.println("<center><h1>Your OTP is :"+otp+"</h1></center>");
				
			} else {
                RequestDispatcher rd=request.getRequestDispatcher("Password.html");
                rd.include(request, response);
                writer.println("<center><h1>Invalid Password</h1></center>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }else {
    	RequestDispatcher rd=request.getRequestDispatcher("Emailid.html");
        rd.include(request, response);
        writer.println("<center><h1>Invalid Password</h1></center>");
    }
     }
}
