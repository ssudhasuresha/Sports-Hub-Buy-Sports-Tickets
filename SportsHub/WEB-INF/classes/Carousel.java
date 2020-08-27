/* This code is used to implement carousel feature in Website. Carousels are used to implement slide show feature. This  code is used to display 
all the accessories related to a particular product. This java code is getting called from cart.java. The product which is added to cart, all the 
accessories realated to product will get displayed in the carousel.*/
  

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;	

			
			
public class Carousel{
			
	public String  carouselfeature(Utilities utility){
				
		ProductRecommenderUtility prodRecUtility = new ProductRecommenderUtility();
		
		HashMap<String, Console> hm = new HashMap<String, Console>();
		StringBuilder sb = new StringBuilder();
		String myCarousel = null;
			
		String name = null;
		String CategoryName = null;
		if(CategoryName==null){
			try{
			// hm=MySqlDataStoreUtilities.getProducts();
			// hm=MySqlDataStoreUtilities.getAllProducts();
			// public static ArrayList<String> getAllProducts(){
			name = "";
			}catch(Exception e)
			{
				
			}
			
		}
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		prodRecmMap = prodRecUtility.readOutputFile();
		
		
		
		int l =0;
		for(String user: prodRecmMap.keySet())
		{
			if(user.equals(utility.username()))
			{
				String products = prodRecmMap.get(user);
				products=products.replace("[","");
				products=products.replace("]","");
				products=products.replace("\"", " ");
				ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(products.split(",")));
<<<<<<< HEAD
				System.out.println("ProductList: " + productsList);
		        myCarousel = "myCarousel"+l;
					
				sb.append("<section class='ftco-section testimony-section' data-stellar-background-ratio='0.5'><div id='content'><div class='post'><h2 class='title meta'>");
=======

		        myCarousel = "myCarousel"+l;
					
				sb.append("<div id='content'><div class='post'><h2 class='title meta'>");
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520
				sb.append("<a style='font-size: 24px;'>"+""+" Recommended Products</a>");
				
				sb.append("</h2>");

<<<<<<< HEAD
				sb.append("<div class='container row '>");
=======
				sb.append("<div class='container'>");
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520
				/* Carousels require the use of an id (in this case id="myCarousel") for carousel controls to function properly.
				The .slide class adds a CSS transition and animation effect, which makes the items slide when showing a new item.
				Omit this class if you do not want this effect. 
				The data-ride="carousel" attribute tells Bootstrap to begin animating the carousel immediately when the page loads. 
		 
				*/
<<<<<<< HEAD
				sb.append("<div class='carousel slide  ' id='"+myCarousel+"' data-ride='carousel'>");
=======
				sb.append("<div class='carousel slide' id='"+myCarousel+"' data-ride='carousel'>");
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520
				
				/*The slides are specified in a <div> with class .carousel-inner.
				The content of each slide is defined in a <div> with class .item. This can be text or images.
				The .active class needs to be added to one of the slides. Otherwise, the carousel will not be visible.
				*/
<<<<<<< HEAD
				// sb.append("<div class='carousel-inner'>");
=======
				sb.append("<div class='carousel-inner'>");
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520
						
				int k = 0;
				for(String prod : productsList){
					prod= prod.replace("'", "");
					Event g = new Event();
<<<<<<< HEAD
					// System.out.println(prod.trim());
					g = ProductRecommenderUtility.getProduct(prod.trim());
					if(k%4==0 && k>4){
						sb.append("</div><div class='row d-flex'>");
					}
					sb.append("<div class='col-lg-3 p-sm-3 ' style='width:1000px;'><div id='game_"+g.getEventId()+"' class='scoreboard'>");
=======
					g = ProductRecommenderUtility.getProduct(prod.trim());
					if (k==0 )
					{
						
						sb.append("<div class='item active'><div class='col-md-6' style = 'background-color: #58acfa;border :1px solid #cfd1d3'>");
					}
					else
					{
						sb.append("<div class='item'><div class='col-md-6' style = 'background-color: #58acfa ;border :1px solid #cfd1d3' >");
					}
					sb.append("<div class='col-lg-3 p-sm-3 '><div id='game_"+g.getEventId()+"' class='scoreboard'>");
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520
					sb.append("<div class='divider text-center'><h4> "+g.getEventName()+"</h4><span>"+g.getSubGenre()+" <span> <span>"+g.getGenre()+"</span> <br/> <span>"+g.getEventDate()+" </span>  <span>"+g.getEventTime()+"</span></div>");
					sb.append("<div class='d-sm-flex mb-4 sport-team'><img src='"+g.getImageUrl()+"' class='logo' style='margin: 10% 50%;'/></div> "); 
					sb.append("<div class='text-center'><p><form method='post' action='Cart'>");
					sb.append("<input type='hidden' name='name' value='"+g.getEventName()+"'><input type='hidden' name='id' value='"+g.getEventId()+"'>");
					sb.append("<input type='submit' class='btn btn-primary' value='Buy Tickets'></form></p>");
					
					sb.append("<p><form method='post' action='WriteReview'>"+
							"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
							"<input type='hidden' name='name' value='"+g.getEventName()+"'>"+
							"<input type='hidden' name='type' value='"+g.getGenre()+"'>"+
							"<input type='hidden' name='maker' value='"+g.getSubGenre()+"'>"+
							"<input type='hidden' name='price' value='"+g.getMaxPrice()+"'>"+
							"<input type='hidden' name='access' value=''>"+
							"<input type='submit' value='WriteReview' class='btn btn-primary'></form></p>");
							
					// sb.append("<input type='submit' class='btn btn-primary' value='Write Review'></form></p></div>");
					 sb.append("<p><form method='post' action='ViewReview'>"+
							"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
							"<input type='hidden' name='name' value='"+g.getEventName()+"'>"+
							"<input type='hidden' name='type' value='"+g.getGenre()+"'>"+
							"<input type='hidden' name='maker' value='"+g.getSubGenre()+"'>"+
							"<input type='hidden' name='access' value=''>"+
							"<input type='submit' value='ViewReview' class='btn btn-primary'></form></p>");
							if (utility.usertype() != null){
								if(utility.usertype().equals("manager")){
						sb.append("<div class='row'> <div class='col-md-5'><form method='get' action='EventModify'>"+
						"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
						"<input type='hidden' name='button' value='Updateevent'>"+
						"<input type='submit' value='Edit Event' class='btn btn-primary'></form></div>");
						sb.append(" <div class='col-md-5'><form method='get' action='EventModify'>"+
						"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
						"<input type='hidden' name='button' value='Deleteevent'>"+
						"<input type='submit' value='Delete Event' class='btn btn-primary'></form></div></div>");
<<<<<<< HEAD
					}
				}
					// System.out.println(g);
					// if (k==0 )
					// {
						
					// 	sb.append("<div class='item active'><div class='col-md-6' style = 'background-color: #58acfa;border :1px solid #cfd1d3'>");
					// }
					// else
					// {
					// 	sb.append("<div class='item'><div class='col-md-6' style = 'background-color: #58acfa ;border :1px solid #cfd1d3' >");
					// }
					// sb.append("<div><p ><form method='post'  action='Cart'>"+
					// 	"<div class='col-lg-4 p-sm-4 '><div id='game_"+g.getEventId()+"' class='scoreboard'>"+
					// 	"<div class='divider text-center'><h4> "+g.getEventName()+"</h4><span>"+g.getSubGenre()+" <span> <span>"+g.getGenre()+"</span> <br/> <span>"+g.getEventDate()+" </span>  <span>"+g.getEventTime()+"</span></div>"+
					// 	"<div class='d-sm-flex mb-4 sport-team'><img src='"+g.getImageUrl()+"' class='logo' style='margin: 10% 50%;'/></div> "+
					// 	"<div class='text-center'><p><form method='post' action='Cart'>"+
					// 	"<input type='hidden' name='name' value='"+g.getEventName()+"'><input type='hidden' name='id' value='"+g.getEventId()+"'>"+
					// 	"<input type='submit' class='btn btn-primary' value='Buy Tickets'></form></p>");
					
					// // sb.append("<p><form method='post' action='Cart'>"+
					// // 	"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
					// // 	"<input type='hidden' name='name' value='"+g.getEventName()+"'>"+
					// // 	"<input type='hidden' name='type' value='"+g.getGenre()+"'>"+
					// // 	"<input type='hidden' name='maker' value='"+g.getSubGenre()+"'>"+
					// // 	"<input type='hidden' name='price' value='"+g.getMaxPrice()+"'>"+
					// // 	"<input type='hidden' name='access' value=''>"+
					// // 	"<input type='submit' value='BuyTickets' class='btn btn-primary'></form></p>");

					// // sb.append("<p><form method='post' action='WriteReview'>"+
					// // 		"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
					// // 		"<input type='hidden' name='name' value='"+g.getEventName()+"'>"+
					// // 		"<input type='hidden' name='type' value='"+g.getGenre()+"'>"+
					// // 		"<input type='hidden' name='maker' value='"+g.getSubGenre()+"'>"+
					// // 		"<input type='hidden' name='price' value='"+g.getMaxPrice()+"'>"+
					// // 		"<input type='hidden' name='access' value=''>"+
					// // 		"<input type='submit' value='WriteReview' class='btn btn-primary'></form></p>");
							
					// // // sb.append("<input type='submit' class='btn btn-primary' value='Write Review'></form></p></div>");
					// //  sb.append("<p><form method='post' action='ViewReview'>"+
					// // 		"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
					// // 		"<input type='hidden' name='name' value='"+g.getEventName()+"'>"+
					// // 		"<input type='hidden' name='type' value='"+g.getGenre()+"'>"+
					// // 		"<input type='hidden' name='maker' value='"+g.getSubGenre()+"'>"+
					// // 		"<input type='hidden' name='access' value=''>"+
					// // 		"<input type='submit' value='ViewReview' class='btn btn-primary'></form></p>");
					// 		if (utility.usertype() != null){
					// 			if(utility.usertype().equals("manager")){
					// 	sb.append("<div class='row'> <div class='col-md-5'><form method='get' action='EventModify'>"+
					// 	"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
					// 	"<input type='hidden' name='button' value='Updateevent'>"+
					// 	"<input type='submit' value='Edit Event' class='btn btn-primary'></form></div>");
					// 	sb.append(" <div class='col-md-5'><form method='get' action='EventModify'>"+
					// 	"<input type='hidden' name='id' value='"+g.getEventId()+"'>"+
					// 	"<input type='hidden' name='button' value='Deleteevent'>"+
					// 	"<input type='submit' value='Delete Event' class='btn btn-primary'></form></div></div>");
					// 			}
					// 		}
					// 		sb.append("</div></div></div></div>");
							k++;
=======
								}
							}
					k++;
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520
					
				}
				
			
				sb.append("</div>");
				/*		The "Left and right controls" part:
					This code adds "left" and "right" buttons that allows the user to go back and forth between the slides manually.
				The data-slide attribute accepts the keywords "prev" or "next", which alters the slide position relative to its current position.
				*/
				sb.append("<a class='left carousel-control' href='#"+myCarousel+"' data-slide='prev' style = 'width : 10% ;background-color:#D7e4ef; opacity :1'>"+
						"<span class='glyphicon glyphicon-chevron-left' style = 'color :red'></span>"+
						"<span class='sr-only'>Previous</span>"+
						"</a>");
				sb.append("<a class='right carousel-control' href='#"+myCarousel+"' data-slide='next' style = 'width : 10% ;background-color:#D7e4ef; opacity :1'>"+
						"<span class='glyphicon glyphicon-chevron-right' style = 'color :red'></span>"+

							"<span class='sr-only'>Next</span>"+
							"</a>");

			
				sb.append("</div>");
			
				sb.append("</div></div>");
				sb.append("</div>");
				l++;
			
				}
			}
			return sb.toString();
		}
	}
