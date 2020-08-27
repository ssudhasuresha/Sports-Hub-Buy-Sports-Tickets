import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			// utility.printHtml("LeftNavigationBar.html");
			// pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			// pw.print("<a style='font-size: 24px;'>Account</a>");
			// pw.print("</h2><div class='entry'>");
			pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
			pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
			pw.print("<h1 class='mb-3 bread'> Account</h1>");
			pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Account<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
			pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
			// pw.print("<h2 class='mb-4'>See What Games are being Played Around You</h2><div class='GameList' style='height:55rem;overflow-y:scroll;'><div class='row d-flex'> ");
			User user=utility.getUser();
			pw.print("<table class='gridtable'>");
			
			
			pw.print("<tr>");
			pw.print("<td> User Name: </td>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
			pw.print("</tr>");
			
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
					// FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\PaymentDetails.txt"));
					// ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					// orderPayments = (HashMap)objectInputStream.readObject();
				}
			catch(Exception e)
				{
			
				}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment od:entry.getValue())	
					if(od.getUserName().equals(user.getName()))
					size= size+1;
				}
				
			if(size>0)
				{	
					
					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td>");
					pw.print("<td>OrderStatus:</td></tr>");
					for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
					{
						for(OrderPayment oi:entry.getValue())	
						if(oi.getUserName().equals(user.getName())) 
						{
							pw.print("<form method='get' action='ViewOrder'>");
							pw.print("<tr>");			
							pw.print("<td><input type='checkbox' name='orderName' value='"+oi.getOrderName()+"'></td>");			
							pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
							pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
							pw.print("<td>Pending</td>");
							pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
							
							pw.print("</tr>");
							pw.print("</form>");
						}
					
					}
					
					pw.print("</table>");
					
				}

				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
				
				
				
				
				
			pw.print("</table>");

			if (utility.usertype().equals("manager"))
					{
							//pw.print("<tr><td colspan=2>");
							pw.print("<a href='addnewcustomer' style='font-size: 23px;'>Add Customer</a><br>");
							
							// pw.print("<a href='EventModify?button=Updateevent' style='font-size: 23px;'>Update Event</a><br>");
							// pw.print("<a href='EventModify?button=Deleteevent' style='font-size: 23px;'>Delete Event</a><br>");
							showcustomer(pw);
							//showeventList(pw);
					}		
			pw.print("</div></div></div></div></section>");     		
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}

	/*protected void showeventList(PrintWriter pw) throws ServletException, IOException {

		pw.print("<br><br><br><br><br><h3>Product List</h3>");
		pw.print("<div class='entry' style='overflow: auto;height: 35em;'><table class='gridtable' >");
		pw.print("<tr>");
		pw.print("<td>Name</td><td>Price</td><td>Manufracturer</td><td>Category</td><td colspan=2></td>");
		pw.print("</tr>");

		HashMap<String, Event> hm = new HashMap<String, Event>();
		 try{
             hm=MySqlDataStoreUtilities.getEvents();
         } catch(Exception e){
             System.out.print(e);
         }
		 for(Map.Entry<String, Event> entry : hm.entrySet()){
			Event ev = entry.getValue();
		 	pw.print("<tr>");
		 	pw.print("<td>"+ev.getEventName()+"</td><td>"+ev.getMaxPrice()+"</td><td>Event</td>");
		 	//pw.print("<td><a href='modifyproduct?&product_id="+fitnesswatch.getId()+"'>Update Products</a><br></td>");
		 	//pw.print("<td><a href='deleteproduct?type=fitnesswatch&product_id="+fitnesswatch.getId()+"'>Delete Products</a></td>");
		 	pw.print("</tr>");
			
		
		}
		
		pw.print("</table></div>");
	
	}*/

	protected void showcustomer(PrintWriter pw) throws ServletException, IOException{
	
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");

		pw.print("<br><br><br><br><br><h3>Orders List</h3>");
		pw.print("<div class='entry' style='overflow: auto;height: 35em;'><table class='gridtable' >");
		pw.print("<tr>");
		pw.print("<td> Order ID</td><td>Username</td><td>order name </td><td>Order price<td colspan=2></td>");
		pw.print("</tr>");
		
			try
			{
				orderPayments=MySqlDataStoreUtilities.selectOrder();

				// FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\PaymentDetails.txt"));
				// ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				// orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}

		
		
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
			for(OrderPayment oi:entry.getValue()){
					pw.print("<tr>");			
					pw.print("<td>"+oi.getOrderId()+"</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
					pw.print("<td><a href='modifyorder?type=user&order_id="+oi.getOrderId()+"&userName="+oi.getUserName()+"'>Add to this Order ID</a><br></td>");
					//pw.print("<td><a href='#' onclick=>Add to this Order ID</a><br></td>");
					pw.print("<td><a href='ViewOrder?cust_order=cust&orderName="+oi.getOrderName()+"&orderId="+oi.getOrderId()+"&Order=CancelOrder'>Delete order</a></td>");
					pw.print("</tr>");
			}
		}
		pw.print("</table></div>");
	}
}




