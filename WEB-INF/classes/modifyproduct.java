import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;

@WebServlet("/modifyproduct")

public class modifyproduct extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayModifyproduct(request, response);
	}
	
	protected void displayModifyproduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login access this page");
				response.sendRedirect("Login");
				return;
			}
		}
		catch(Exception e)
			{
		
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
	
		utility.printHtml("Footer.html");
		 }
	
}

