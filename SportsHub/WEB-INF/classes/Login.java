import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* User Information(username,password,usertype) is obtained from HttpServletRequest,
		Based on the Type of user(customer,manager) respective hashmap is called and the username and 
		password are validated and added to session variable and display Login Function is called */

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String usertype = request.getParameter("usertype");
		HashMap<String, User> hm=new HashMap<String, User>();
		//user details are validated using a file 
		//if the file containts username and passoword user entered user will be directed to home page
		//else error message will be shown
		try
		{	
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
				
		}
		User user = hm.get(username);
		if(user!=null)
		{
		 String user_password = user.getPassword();
		 if (password.equals(user_password)) 
			{
			HttpSession session = request.getSession(true);
			session.setAttribute("username", user.getName());
			session.setAttribute("usertype", user.getUsertype());
			
			response.sendRedirect("Home");
			return;
			
			}
		}
		displayLogin(request, response, pw, true);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}


	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<section class='ftco-section contact-section'>"
				+ "<div class='container'>"
				+ "<div class='row justify-content-center mb-5 pb-3'>"
				+ "<div class='col-md-7 heading-section text-center ftco-animate'>"
				+ "<h2>Login</h2>"
				+ "</div></div>");
		if (error)
			pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login_msg")!=null){			
			pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}
		pw.print("<div class='row block-9'>"
				+ "<div class='col-md-6 d-flex' style='margin-left:340px;'>"
				+ "<form method='post' action='Login' class='bg-light p-5 contact-form'>"
				+ "<div class='form-group'><input type='text' name='username' class='form-control' placeholder='User Name' required></div>"
				+ "<div class='form-group'><input type='password' name='password' class='form-control' placeholder='Password' required></div>"
				+ "<div class='form-group'><input type='submit' value='Submit' class='btn btn-primary py-3 px-5'></div>"
				+ "<div class='form-group'><h5>New User? Register here!</h5><a href='Registration'> Register</a></div>" 
				+ "</form></div></div></div></section>");
		utility.printHtml("Footer.html");
	}

}
