
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

@WebServlet("/modifyorder")

public class modifyorder extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

      
        String order_id = request.getParameter("order_id");
        String userName = request.getParameter("userName");
       
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Product List</a>");
			pw.print("</h2><div class='entry'>");
            if (utility.usertype().equals("manager")){
                showproductList(pw,order_id,userName);
            }else{
                pw.print("<h4 style='color:red'>You are not authorised</h4>");
            }
            pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");	        		
	}

	/* Display Account Details of the Customer (Name and Usertype) */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        String TOMCAT_HOME = System.getProperty("catalina.home");

        Integer order_id = Integer.parseInt(request.getParameter("order_id"));
        String userName = request.getParameter("user_id");
        String order_name = request.getParameter("product_name");
        Double order_price=Double.parseDouble(request.getParameter("product_price"));
        String order_address="";
        String order_credit = "";
        String delivery_date = "";
        String new_product_id = request.getParameter("product_id"); 
        


        //utility.storePayment(orderId,oi.getName(),oi.getPrice(),userName,userAddress,creditCardNo);
		
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
        }catch(Exception e)
        {
        
        }
		
		
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
            for(OrderPayment oi:entry.getValue())
            {
                if(oi.getUserName().equals(userName) && oi.getOrderId() == order_id){
                    order_address = oi.getUserAddress();
                    order_credit = oi.getCreditCardNo();
                    delivery_date = oi.getDeliveryDate();
                }
            }
        }

        HttpSession session=request.getSession(); 	
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Product List</a>");
        pw.print("</h2><div class='entry'>");
        if(order_address!="" && order_credit!=""){
            utility.storePayment(order_id,order_name,order_price,userName,order_address,order_credit,delivery_date);
            pw.print("<h4 style='color:red'>New Product Has been addded to your order "+order_id+" </h4>");
        } else {
            pw.print("<h4 style='color:red'>Something Went Wrong</h4>");
        }

        pw.print("</h2></div></div></div>");		
        utility.printHtml("Footer.html");	    

        








    }
	protected void showproductList(PrintWriter pw,String order_id, String user_id) throws ServletException, IOException {

		pw.print("<br><br><br><br><br><h3>Product List</h3>");
		pw.print("<div style='overflow: auto;height: 500px; class='entry'><table class='gridtable' '>");
		pw.print("<tr>");
		pw.print("<td>Name</td><td>Price</td><td>Manufracturer</td><td>Category</td><td colspan=2></td>");
		pw.print("</tr>");
		pw.print("</table></div>");
	
	}


}




