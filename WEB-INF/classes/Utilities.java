import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;	

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				// result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				System.out.print(session);
				if(session.getAttribute("usertype").equals("manager")){
					result = result + "<li class='nav-item '><div class='dropdown show nav-link'><div class='dropdown-toggle' id='dropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' type='button' aria-expanded='false' style='-webkit-appearance: inherit;'><a>Hello, "+username+"</a></div>"
					+ "<div class='dropdown-menu' aria-labelledby='dropdownMenuLink'>"
					+"<a class='nav-link dropdown-item' href='Account' style='color:#000000;'>Account</a>"
					+"<a class='nav-link dropdown-item' href='ViewOrder' style='color:#000000;'>View Order</a>"
					+"<a class='nav-link dropdown-item' href='DataAnalytics' style='color:#000000;'>Data Analytics</a>"
					+"<a class='nav-link dropdown-item' href='DataVisualization' style='color:#000000;'>Data Visualization</a>"
					+"<a class='nav-link dropdown-item' href='sales' style='color:#000000;'>Total Sales Report</a>"
					+"<a class='nav-link dropdown-item' href='salesgraph' style='color:#000000;'>Sales Graph</a>"
					// +"<a class='nav-link dropdown-item' href='salesdaily' style='color:#000000;'>Daily Sales Report</a>"
					+ "<a class='nav-link dropdown-item' href='Logout' style='color:#000000;'>Logout</a></div></div></li>"
					// +"<li class='nav-item cta'><a href='BuyTickets' class='nav-link'>Buy Ticket</a></li>"
					+"<li class='nav-item cta'><a href='Cart' class='nav-link'>Cart</a></li>";
				}
				else{
					result = result + "<li class='nav-item'><div class='dropdown show nav-link'><div class='dropdown-toggle' id='dropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' type='button' aria-expanded='false' style='-webkit-appearance: inherit;'><a>Hello, "+username+"</a></div>"
					+ "<div class='dropdown-menu' aria-labelledby='dropdownMenuLink'>"
					+"<a class='nav-link dropdown-item' href='Account' style='color:#000000;'>Account</a>"
					+"<a class='nav-link dropdown-item' href='ViewOrder' style='color:#000000;'>View Order</a>"
					+ "<a class='nav-link dropdown-item' href='Logout' style='color:#000000;'>Logout</a></div></div><li>"
					// +"<li class='nav-item cta'><a href='BuyTickets' class='nav-link'>Buy Ticket</a></li>"
					+"<li class='nav-item cta'><a href='Cart' class='nav-link'>Cart</a></li>";
				}
				
			}
			else{
				result = result + "<li class='nav-item cta'><a href='Login' class='nav-link'>Login</a></li>";
				// result = result + "<li class='nav-item cta'><a href='BuyTickets' class='nav-link'>Buy Ticket</a></li>";
				
			}
			// result = result + "<li class='nav-item '><input type='text' name='searchId' value='' class='input nav-link' id='searchId' onkeyup='doCompletion()' placeholder='Search Here' style='padding: 5px; margin:14px; font-size: 16px;'/></li>";
			// result =  result + "<div id='auto-row'><table id='complete-table' class='gridtable style='position: absolute; width: 315px; background-color: rgb(116, 114, 114);'></table></div>";
			pw.print(result);
			pw.print("</ul></div></div></nav>");
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}

	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String id){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		HashMap<String,Event> hm=MySqlDataStoreUtilities.getEvents();
		Event game = hm.get(id);
		OrderItem orderitem = new OrderItem(game.getEventName(), game.getMaxPrice(), game.getImageUrl(), game.getGenre());
		orderItems.add(orderitem);
		
		
	}

	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}

	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}
	

	public int getOrderPaymentSize(){
		String TOMCAT_HOME = System.getProperty("catalina.home");
		int size=0;
			try
			{
				size=MySqlDataStoreUtilities.selectOrderSize();
			}
			catch(Exception e)
			{
			
			}
			return size;		
	}
	public void storePayment(int orderId,String orderName,double orderPrice,String userName,String userAddress,String creditCardNo, String newDate){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				orderPayments=MySqlDataStoreUtilities.selectOrder();

			// 	FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\PaymentDetails.txt"));
			// 	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			// 	orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,newDate);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try
			{	
				MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,newDate);
				// FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\BestDeals\\PaymentDetails.txt"));
				// ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	// objectOutputStream.writeObject(orderPayments);
				// objectOutputStream.flush();
				// objectOutputStream.close();       
				// fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	public String storeReview(String productid,String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String reatilerpin,String price,String city, String state, String age, String gender, String occupation){
		String message=MongoDBDataStoreUtilities.insertReview(productid,productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city,state,age,gender,occupation);
			if(!message.equals("Successfull"))
			{ return "UnSuccessfull";
			}
			else
			{
			HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
			try
			{
				reviews=MongoDBDataStoreUtilities.selectReview();
			}
			catch(Exception e)
			{
				
			}
			if(reviews==null)
			{
				reviews = new HashMap<String, ArrayList<Review>>();
			}
				// if there exist product review already add it into same list for productname or create a new record with product name
				
			if(!reviews.containsKey(productname)){	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(productname, arr);
			}
			ArrayList<Review> listReview = reviews.get(productname);		
			Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city);
			listReview.add(review);	
				
				// add Reviews into database
			
			return "Successfull";	
			}
		}
		
		/*public HashMap<String, Game> getGames(){
			HashMap<String, Game> hm = new HashMap<String, Game>();
			hm.putAll(SaxParserDataStore.games);
			return hm;
	}
		
		public ArrayList<String> getProductsGame(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Game> entry : getGames().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}*/

}
