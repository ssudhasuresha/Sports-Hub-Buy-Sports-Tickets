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

@WebServlet("/deletefromcart")

public class deletefromcart extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

      
        String name = request.getParameter("name");
       
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
            // utility.printHtml("LeftNavigationBar.html");
            // pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			// pw.print("<a style='font-size: 24px;'>Product List</a>");
            // pw.print("</h2><div class='entry'>");
            pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
            pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
            pw.print("<h1 class='mb-3 bread'>List of Games</h1>");
            pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Account<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
            pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
            
            ArrayList<OrderItem> order = utility.getCustomerOrders();
            for (OrderItem oi : order) 
			{
                if(oi.getName().equals(name)){
                    OrdersHashMap.orders.remove(utility.username());
                    order.remove(oi);
                    OrdersHashMap.orders.put(utility.username(), order);
                    pw.print("<script>var r = confirm('Cart is updated successfully');if(r==true || r==false){window.location.href ='Cart';}</script>");
                    break;
                }
            }
            pw.print("</div></div></div></div></section>"); 
            
            utility.printHtml("Footer.html");


        }

    }