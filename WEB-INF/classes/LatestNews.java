import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LatestNews")

public class LatestNews extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

			// HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
		// try
		// 	{
				Utilities utility = new Utilities(request,pw);
				utility.printHtml("Header.html");
		pw.print("<div id='content'>");
		pw.print("<div class='post'>");
		pw.print("<h2 class='title'>");
		// pw.print("<a href='#'>Welcome to BestDeals </a></h2>");
		
		pw.print("<div class='entry'>");
		// pw.print("Latest News From Twitter");
		pw.print("<img src='images/site/TVs.png' style='width: 600px; display: block; margin-left: auto; margin-right: auto' />");
		pw.print("<br> <br>");
		// pw.print("<h2  style='padding: 100px'> Always deliver more than expected</h2>");
		pw.print("<br> <br>");
		// pw.print("<h1>We beat our competitors in all aspects. Price-Match Guaranteed</h2>");
		
			String line=null;
			String TOMCAT_HOME = System.getProperty("catalina.home");

			// HashMap<String,Product> productmap=MySqlDataStoreUtilities.getData();
			
			// for(Map.Entry<String, Product> entry : productmap.entrySet())
			// {
				
			// if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
			// {		
				
				
			BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\EWA_Project\\DealMatches.txt")));
			line=reader.readLine().toLowerCase();
	
			String text = "";
			String link = "";
			int j = 0;
			pw.print("<div class='row d-flex'>");
			if(line==null)
			{
				pw.print("<h2 align='center'>No Offers Found</h2>");
				// break;
			}	
			else
			{	
			do {	
			      
				//   if(line.contains(entry.getKey()))
				//   {
					// String text = line.split("https://t.co/",2)[0];
					// String link = "https://t.co/" + line.split("https://t.co/",2)[1];
					int i = 0;
					link = "";
					String[] arrOfStr = line.split("image:", 2); 
					for (String a : arrOfStr) {
						if(i == 1){
							// System.out.println("image");
							link = a;
							i = 0; 
						} else {
							text = a ;
						}
						
						// System.out.println(a); 
						i = i + 1;
					}

					if(j%4==0 && j>4){
						pw.print("</div><div class='row d-flex'>");
					}
					pw.print("<div class='col-lg-3 p-sm-3 '><div class='scoreboard'>");
					pw.print("<div class='divider text-center'><span> "+text+"</span></div>");
					if(link != ""){
						pw.print("<div class='d-sm-flex mb-4 sport-team'><img src='"+link+"' class='stats' /></div> "); 
					}
					
					// pw.print("<h2>"+text+"</h2>");
					// pw.print("<h2>"+link+"</h2>");
					// pw.print("<br>");
					// selectedproducts.put(entry.getKey(),entry.getValue());
					// break;
				//   }
				pw.print("</div></div>");
					j++;
			    }while((line = reader.readLine()) != null);
			
			 
			 
			}
			// }
			// catch(Exception e)
			// {
			// pw.print("<h2 align='center'>No Offers Found</h2>");
			// }
		pw.print("</div>");
		pw.print("</div>");
		// pw.print("<div class='post'>");
		// pw.print("<h2 class='title meta'>");
		// pw.print("<a style='font-size: 24px;'>Deal Matches</a>");
		// pw.print("</h2>");
		// pw.print("<div class='entry'>");
		// if(selectedproducts.size()==0)
		// {
		// pw.print("<h2 align='center'>No Deals Found</h2>");	
		// }
		// else
		// {
		// pw.print("<table id='bestseller'>");
		// pw.print("<tr>");
		// for(Map.Entry<String, Product> entry : selectedproducts.entrySet()){
		// pw.print("<td><div id='shop_item'><h3>"+entry.getValue().getName()+"</h3>");
		// pw.print("<strong>"+entry.getValue().getPrice()+"$</strong>");
		// pw.print("<ul>");
		// pw.print("<li id='item'><img src='images/"+entry.getValue().getType()+"/"+entry.getValue().getImage()+"' alt='' />");
		// pw.print("</li><li>");
		// pw.print("<form action='Cart' method='post'><input type='submit' class='btnbuy' value='Buy Now'>");
		// pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
		// pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		// pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getRetailer()+"'>");
		// pw.print("<input type='hidden' name='access' value=''>");
		// pw.print("</form></li><li>");
		// pw.print("<form action='WriteReview' method='post'><input type='submit' class='btnreview' value='WriteReview'>");
		// pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
		// pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		// pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getRetailer()+"'>");
		// pw.print("<input type='hidden' name='price' value='"+entry.getValue().getPrice()+"'>");
		// pw.print("</form></li>");
		// pw.print("<li>");
		// pw.print("<form action='ViewReview' method='post'><input type='submit' class='btnreview' value='ViewReview'>");
		// pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
		// pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		// pw.print("</form></li></ul></div></td>");
		// }
		// pw.print("</tr></table>");
		// }
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");
		
	}
}
