import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.text.*;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		

		Utilities utility = new Utilities(request, pw);

		long date = System.currentTimeMillis() + 14 * 24 * 3600 * 1000;
		Date newDate = new Date(date);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		// String 14_days_date=df.format(newDate);

		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	
		String userName=request.getParameter("userName");
		String userAddress=request.getParameter("userAddress");
		String creditCardNo=request.getParameter("creditCardNo");

		System.out.print("the user address is" +userAddress);
		System.out.print(creditCardNo);
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() )
		{
			//Random rand = new Random(); 
			//int orderId = rand.nextInt(100);
			int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders())
			{

				//set the parameter for each column and execute the prepared statement

				utility.storePayment(orderId,oi.getName(),oi.getPrice(),userName,userAddress,creditCardNo,df.format(newDate));
			}

			//remove the order details from cart after processing
			
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			// utility.printHtml("LeftNavigationBar.html");
			// pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			// pw.print("<a style='font-size: 24px;'>Order</a>");
			// pw.print("</h2><div class='entry'>");
			pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
		pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
		pw.print("<h1 class='mb-3 bread'>List of Games</h1>");
		pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Account<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
		pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
		
			pw.print("<h2>Your Order");
			pw.print("&nbsp&nbsp");  
			pw.print("is stored ");
			pw.print("<br>Your Order No is "+(orderId));
			pw.print("<br> Delivery Date is  " + df.format(newDate));
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			// utility.printHtml("LeftNavigationBar.html");
			// pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			// pw.print("<a style='font-size: 24px;'>Order</a>");
			// pw.print("</h2><div class='entry'>");
			pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
		pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
		pw.print("<h1 class='mb-3 bread'>List of Games</h1>");
		pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Account<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
		pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
		
		
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			// pw.print("</h2></div></div></div>");
			pw.print("</div></div></div></div></section>"); 		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
