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

@WebServlet("/sales")
public class Sales extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			// pw.print("<a style='font-size: 24px;'>Sales for all Products</a>");
			// pw.print("</h2><div class='entry' style='overflow: auto;height: 64em;'>");
			
			pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
			pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
			pw.print("<h1 class='mb-3 bread'>List of Games</h1>");
			pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Account<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
			pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
			pw.print("<table class='gridtable' >");
			pw.print("<tr>");
			pw.print("<td>Name</td><td>Price</td><td>Item Sold</td><td>Total Sales</td>");
			pw.print("</tr>");

			HashMap<String,List<String>> hm=new HashMap<String,List<String>>();
			double sales = 0.0;
			hm = MySqlDataStoreUtilities.getSaleCount();
			for(Map.Entry<String,List<String>> entry : hm.entrySet()){
				List<String> a = entry.getValue();
				pw.print("<tr>");
				pw.print("<td>"+ entry.getKey()+"</td><td>"+a.get(0)+"</td><td>"+a.get(1)+"</td><td>"+a.get(2)+"</td>");
				sales = sales + Double.parseDouble(a.get(2));
				pw.print("</tr>");
			}
			pw.print("</table>");
			pw.print("<br/><br/><br/><br/><br/><br/><h3> Total Sales : " + sales + "  </h3>");
			pw.print("</div></div></div></div></section>"); 
			utility.printHtml("Footer.html");
			
		} catch(Exception e){

		}
	
	}
	
	
}