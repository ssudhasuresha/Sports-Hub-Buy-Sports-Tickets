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

@WebServlet("/addnewcustomer")

public class addnewcustomer extends HttpServlet {
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
	    Utilities utility = new Utilities(request, pw);
			String product_id = request.getParameter("user_id");
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String product_description = request.getParameter("user_password");
            String usertype = "customer";
            HashMap<String, User> hm=new HashMap<String, User>();
			String TOMCAT_HOME = System.getProperty("catalina.home");

			//get the user details from file 

			try
			{
				hm=MySqlDataStoreUtilities.selectUser();
 			//  FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\UserDetails.txt"));
			//  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
            //  hm= (HashMap)objectInputStream.readObject();
            // //  objectInputStream.flush();
            //  objectInputStream.close();       
            //  fileInputStream.close();
			}
			catch(Exception e)
			{
				
            }
            
            if(hm.containsKey(product_id)){

            
                String error_msg = "Username already exist as " + usertype;
                    HttpSession session = request.getSession(true);				
                    session.setAttribute("login_msg", error_msg);
                    pw.print("<h3>here</h3>"); 
                    response.sendRedirect("Account"); return;
            }
			else
			{
				/*create a user object and store details into hashmap
				store the user hashmap into file  */

                User user = new User(first_name, last_name, product_id, product_description,usertype);
                hm.put(product_id, user);
				
				MySqlDataStoreUtilities.insertUser(first_name, last_name, product_id, product_description, product_description,usertype);
			    // FileOutputStream fileOutputStream = new FileOutputStream(TOMCAT_HOME+"\\webapps\\BestDeals\\UserDetails.txt");
        		// ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
           	 	// objectOutputStream.writeObject(hm);
				// objectOutputStream.flush();
				// objectOutputStream.close();       
				// fileOutputStream.close();
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
				if(!utility.isLoggedin()){
					response.sendRedirect("Login"); return;
				} else {
					response.sendRedirect("Account"); return;
                }
            }


			
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAddProduct(request, response);
	}
	
	protected void displayAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Add Customer below</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<table class='gridtable'>");
			if (!utility.usertype().equals("manager"))
			{
				pw.print("<h4 style='color:red'>You are not storage manager, you can not access this page</h4>");
			}
            else{
                pw.print("<form action='addnewcustomer'  method='post'>"
			+"<table style='width:60%;margin-left: 49px '>"
			+"<tr>"
			+"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>First Name of the user:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='first_name' class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"
			+"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Last Name of the user:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='last_name' class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"


			+"<tr>"
		   

			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>user ID:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='text' name='user_id' class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"
			
			 +"<tr>"
			+"<td align='right'>"
			+"<div class='shipping-input-fields'>"
			+"<label>Password:<span style='color:#aa0000'>*</span> :</label>"
			+"</div>"
			+"</td>"
			+"<td>"
			+"<div class='shipping-input-fields'>"
			+"<input type='password' name='user_password'  value='' class='login-input' style='width:249px; height:24px' required/>"
			+"</div>"
			+"</td>"
			+"</tr>"
			
		   
			+"<tr>"
			+"<td colspan='3' align='center'>"
			+"<div>"
			+"<button class='sign-in-up-button' type='submit'>Add user</button>"
			+"</div>"
			+"</td>"
			+"</tr>"
			+"</table>"
			+"</form>" 
			+"</div></div></div>");
            }
			
		utility.printHtml("Footer.html");
		 }
	
}