	
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/ProductData")
public class ProductData extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{

PrintWriter pw= response.getWriter();
response.setContentType("text/html");			
 pw.println("<html>");
 pw.println("<body>");

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		// pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		// pw.print("</h2><div class='data'><table id='bestseller'>");
		// pw.print("<tr>");
		// pw.print("<td><div id='shop_item'>");

		pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
        pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
        pw.print("<h1 class='mb-3 bread'>Games search result</h1>");
        pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Games<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
        pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
        pw.print("<h2 class='mb-4' style='margin-left:100px;'></h2><div class='GameList' style='height:55rem;'><div class='row d-flex'> ");
			Event data= (Event)request.getAttribute("data");
			data.setEventId(String.valueOf(request.getAttribute("key")));
			// pw.print("<h4>"+data.getEventName()+"</h4>");
			// pw.print("<strong>$"+data.getMaxPrice()+"</strong><ul>");
			pw.print("<div  class='col-lg-3 p-sm-3 '><div style='width:273px; height:70%;' id='game_"+data.getEventId()+"' class='scoreboard'>");
            pw.print("<div class='divider text-center'><h4> "+data.getEventName()+"</h4><span>"+data.getSubGenre()+" <span> <span>"+data.getGenre()+"</span> <span>"+data.getEventDate()+" </span>  <span>"+data.getEventTime()+"</span></div>");
            pw.print("<div class='d-sm-flex mb-4 sport-team'><img src='"+data.getImageUrl()+"' class='logo' style='margin: 10% 50%;'/></div> "); 
			// 
			pw.print("<div class='text-center'><p><form method='post' action='Cart'>");
            pw.print("<input type='hidden' name='name' value='"+data.getEventName()+"'><input type='hidden' name='id' value='"+data.getEventId()+"'>");
            pw.print("<input type='submit' class='btn btn-primary' value='Buy Tickets'></form></p>");
			// pw.print("<li id='item'><img src='images/"+data.getType()+"/"+data.getImage()+"' alt='' /></li>");
			// pw.print("<li><form method='post' action='Cart'>" +
			// 		"<input type='hidden' name='name' value='"+data.getEventId()+"'>"+
			// 		"<input type='hidden' name='type' value='"+data.getImageUrl()+"'>"+
			// 		"<input type='hidden' name='maker' value='"+data.getEventTime()+"'>"+
			// 		"<input type='hidden' name='price' value='"+data.getMaxPrice()+"'>"+
			// 		"<input type='hidden' name='access' value='"+data.getMaxPrice()+"'>"+
			// 		"<input type='submit' class='btnbuy' value='Buy Tickets'></form></li>");
			// pw.print("<li><form method='post' action='WriteReview'>"+
			// "<input type='hidden' name='name' value='"+data.getEventId()+"'>"+
			// 		// "<input type='hidden' name='type' value='"+data.getImageUrl()+"'>"+
			// 		"<input type='hidden' name='maker' value='"+data.getEventTime()+"'>"+
			// 		"<input type='hidden' name='price' value='"+data.getEventName()+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 	    	"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<p><form method='post' action='WriteReview'>"+
			"<input type='hidden' name='id' value='"+data.getEventId()+"'>"+
			"<input type='hidden' name='name' value='"+data.getEventName()+"'>"+
			"<input type='hidden' name='type' value='"+data.getGenre()+"'>"+
			"<input type='hidden' name='maker' value='"+data.getSubGenre()+"'>"+
			"<input type='hidden' name='price' value='"+data.getMaxPrice()+"'>"+
			"<input type='hidden' name='access' value=''>"+
			"<input type='submit' value='WriteReview' class='btn btn-primary'></form></p>");
			// pw.print("<li><form method='post' action='ViewReview'>"+
			// 		"<input type='hidden' name='name' value='"+data.getEventId()+"'>"+
			// 		"<input type='hidden' name='type' value='"+data.getImageUrl()+"'>"+
			// 		"<input type='hidden' name='maker' value='"+data.getEventTime()+"'>"+		
			// 		"<input type='hidden' name='price' value='"+data.getMaxPrice()+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 	    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("<p><form method='post' action='ViewReview'>"+
			"<input type='hidden' name='id' value='"+data.getEventId()+"'>"+
			"<input type='hidden' name='name' value='"+data.getEventName()+"'>"+
			"<input type='hidden' name='type' value='"+data.getGenre()+"'>"+
			"<input type='hidden' name='maker' value='"+data.getSubGenre()+"'>"+
			"<input type='hidden' name='access' value=''>"+
			"<input type='submit' value='ViewReview' class='btn btn-primary'></form></p>");
			if (utility.usertype() != null){
                if(utility.usertype().equals("manager")){
                pw.print("<div class='row'> <div class='col-md-5'><form method='get' action='EventModify'>"+
                "<input type='hidden' name='id' value='"+data.getEventId()+"'>"+
                "<input type='hidden' name='button' value='Updateevent'>"+
                "<input type='submit' value='Edit Event' class='btn btn-primary'></form></div>");
                pw.print(" <div class='col-md-5'><form method='get' action='EventModify'>"+
                "<input type='hidden' name='id' value='"+data.getEventId()+"'>"+
                "<input type='hidden' name='button' value='Deleteevent'>"+
                "<input type='submit' value='Delete Event' class='btn btn-primary'></form></div></div>");
			}
		}
			pw.print("</ul></div></td>");
			pw.print("</tr>");
			pw.print("</table></div></div></div>");		
			pw.print("</div></div></div></div></section>");     
		utility.printHtml("Footer.html");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	

}