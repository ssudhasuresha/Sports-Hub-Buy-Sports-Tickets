import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.lang.Math; 

@WebServlet("/Cart")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		// String maker = request.getParameter("maker");
        // String image = request.getParameter("image");
        // String productprice=request.getParameter("price");
		// System.out.print("name" + name + "type" + type + "maker" + maker + "image" + image + " productprice " + productprice );
        System.out.print("name" + name + "id" + id );
		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
		
		utility.storeProduct(name, id);
        displayCart(request, response);
				
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
    

    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
        utility.printHtml("Header.html");
        pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
        // pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
        // pw.print("<h1 class='mb-3 bread'>List of Games</h1>");
        // pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Games<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
        pw.print("<div class='container' style='background:white;padding-top: 3em;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
        pw.print("<h2 class='mb-4'>Cart</h2><div class='CartList'><div class='row d-flex'><form name ='Cart' action='CheckOut' method='post'> ");
        if(utility.CartCount()>0)
		{
            pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			//DecimalFormat format = new DecimalFormat("##.##");
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<tr>");
				pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td><td><a name='Order' value='Delete Item' href='deletefromcart?name="+oi.getName()+"' >Delete Item</a></td>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("</tr>");
				//pw.print("<script>functionvar r = confirm('Product is deleted successfully');if(r==true || r==false){window.location.href ='Home';}</script>");
				total = total +oi.getPrice();
				i++;
			}
			//total=;
			String test = String.format("%1.2f",total);
			pw.print("<input type='hidden' name='orderTotal' value='"+test+"'>");	
			pw.print("<tr><th></th><th>Total</th><th>"+test+"</th>");
			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			pw.print("</table></form></div><div class='row'>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			pw.print(carousel.carouselfeature(utility));
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
        // for(int i=0;i<=20;i++){
        //     if(i%4==0 && i>4){
        //         pw.print("</div><div class='row d-flex'>");
        //     }
        //     pw.print("<div class='col-lg-3 p-sm-3 '><div id='game_"+i+"' class='scoreboard'>");
        //     pw.print("<div class='divider text-center'><h4>Game Name "+i+"</h4><span> Game Description <span> </div>");
        //     pw.print("<div class='d-sm-flex mb-4 sport-team'><img src='images/bg_1.jpg' class='logo' style='margin: 10% 50%;'/></div> "); 
        //     pw.print("<div class='text-center'><p><form method='post' action='Cart'>");
        //     pw.print("<input type='hidden' name='name' value='game1'><input type='hidden' name='type' value='football'><input type='hidden' name='image' value='images/bg_1'><input type='hidden' name='maker' value='eventmanager1'>");
        //     pw.print("<input type='submit' class='btn btn-primary' value='Buy Tickets'></form></p></div>");
        //     pw.print("</div></div>");
        // } 
        
	    pw.print("</div></div></div></div></section>");     
				
		//utility.printHtml("About.html");
		utility.printHtml("Footer.html");
    }

}
