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

@WebServlet("/Registration")

public class Registration extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}

	/*   Username,Password,Usertype information are Obtained from HttpServletRequest variable and validates whether
		 the User Credential Already Exists or else User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("confirmpassword");
		String usertype = "customer";
		if(!utility.isLoggedin())
			usertype = request.getParameter("usertype");

		//if password and repassword does not match show error message

		if(!password.equals(repassword))
		{
			error_msg = "Passwords doesn't match!";
		}
		else
		{

			//get the userdata from sql database to hashmap
			HashMap<String, User> hm=new HashMap<String, User>();
			
			String message=MySqlDataStoreUtilities.getConnection();
			
			if(message.equals("Successfull"))
			{
				hm=MySqlDataStoreUtilities.selectUser();
			
			// if the user already exist show error that already exist

			if(hm.containsKey(username))
				error_msg = "Username already exist as " + usertype;
			else
			{
				/*create a user object and store details into hashmap
				store the user hashmap into file  */

				User user = new User(firstName,lastName,username,password,usertype);
				hm.put(username, user);
				MySqlDataStoreUtilities.insertUser(firstName,lastName,username,password,repassword,usertype);					
				HttpSession session = request.getSession(true);				
				
				if(!utility.isLoggedin()){
					session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
					response.sendRedirect("Login"); return;
				} else {
					response.sendRedirect("CustomerCreated"); return;
				}
			}
		
		  
		//display the error message
		/*if(utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", error_msg);
			response.sendRedirect("Account"); return;
		
		  }*/
		}
		else 
		{
			error_msg="MySql server is not up and running";
		}
		}
		displayRegistration(request, response, pw, true);
		
	}

	/*  displayRegistration function displays the Registration page of New User */
	
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<section class='ftco-section contact-section'>"
				+ "<div class='container'>"
				+ "<div class='row justify-content-center mb-5 pb-3'>"
				+ "<div class='col-md-7 heading-section text-center ftco-animate'>"
				+ "<h2>Register</h2></div></div>");
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		pw.print("<div class='row block-9'>"
				+ "<div class='col-md-6 d-flex' style='margin-left:340px;'>"
				+ "<form method='post' action='Registration' class='bg-light p-5 contact-form'>"
				+ "<div class='form-group'><input type='text' class='form-control' name='firstname' placeholder='First Name' required></div>"
				+ "<div class='form-group'><input type='text' class='form-control' name='lastname' placeholder='Last Name' required></div>"
				+ "<div class='form-group'><input type='text' class='form-control' name='username' placeholder='User Name' required></div>"
				+ "<div class='form-group'><input type='password' class='form-control' name='password' placeholder='Password' required></div>" 
				+ "<div class='form-group'><input type='password' class='form-control' name='confirmpassword' placeholder='Confirm Password' required></div>"
				+ "<div class='form-group'><select name='usertype' class='form-control'><option value='customer' selected>Customer</option><option value='manager'>Store Manager</option></select></div>"
				+ "<div class='form-group'><input type='submit' value='Submit' class='btn btn-primary py-3 px-5'></div>"
				+ "</form></div></div></div></section>");
		utility.printHtml("Footer.html");
	}
}
