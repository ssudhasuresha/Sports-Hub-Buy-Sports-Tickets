import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EventCrud")

public class EventCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			
			String msg = "good";
			String eventId= "", eventName= "", eventImage= "", salesStartDate= "", salesEndDate= "", eventDate= "", eventTime= "", eventSaleStatus= "", genre= "", subGenre= "", venuesName= "", venuesPostalCode= "", venuesCity= "", venuesState= "", stateCode= "", countryName= "", countryCode= "", address= "", phoneNumber= "", officeHours= "", paytmentDetails= "", willCallDetail= "", parkingDetail= "", accessibleSeatingDetail= "", seatMap= "", pleaseNote= "", promoterName= "", promoterDescription = " ";
			double locationLongitude= 0.0, locationLatitude= 0.0;
			double minPrice=0.0,maxPrice = 0.0;
			HashMap<String,Event> allevents = new HashMap<String,Event> ();
			if (action.equals("add") || action.equals("update"))
			{	 
				 eventId = request.getParameter("eventId");
				 eventName = request.getParameter("eventName");
				 eventImage = request.getParameter("eventImage");
				 salesStartDate = request.getParameter("salesStartDate");
				 salesEndDate = request.getParameter("salesEndDate");
				 eventDate = request.getParameter("eventDate");
				 eventTime = request.getParameter("eventTime");
				 eventSaleStatus = request.getParameter("eventSalesStatus");
				 genre = request.getParameter("genre");
				 subGenre = request.getParameter("subGenre");
				 venuesName = request.getParameter("venueName");
				 venuesPostalCode = request.getParameter("venuePostalCode");
				 venuesCity = request.getParameter("venueCity");
				 venuesState = request.getParameter("venueState");
				 stateCode = request.getParameter("stateCode");
				 countryName = request.getParameter("country");
				 countryCode = request.getParameter("countryCode");
				 address = request.getParameter("address");
				 locationLongitude = Double.parseDouble(request.getParameter("long"));
				 locationLatitude = Double.parseDouble(request.getParameter("lati"));
				 phoneNumber = request.getParameter("phoneDetails");
				 officeHours = request.getParameter("officeHours");
				 paytmentDetails = request.getParameter("paymentDetails");
				 willCallDetail = request.getParameter("willCallDetails");
				 parkingDetail = request.getParameter("parkingDetails");
				 accessibleSeatingDetail = request.getParameter("accessibleSeatingDetails");
				 minPrice = Double.parseDouble(request.getParameter("minPrice"));
				 maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
				 seatMap = request.getParameter("seatMap");
				 pleaseNote = request.getParameter("pleaseNote");
				 promoterName = request.getParameter("promoterName");
				 promoterDescription = request.getParameter("promoterDescription");
			
			}
			else{
				eventId   = request.getParameter("eventId");
			}	
			utility.printHtml("Header.html");

			if(action.equals("add"))
			{
			  	if(genre.equals("Hockey") || genre.equals("Football") || genre.equals("Basketball")){
				  allevents = MySqlDataStoreUtilities.getEvents();
				  if(allevents.containsKey(eventId)){
					  msg = "Event already available";
					 
				  }
					  
			  }
			  
			  if (msg.equals("good"))
			  {  
				  try
				  {
					  msg = MySqlDataStoreUtilities.addevents(eventId, eventName, eventImage, salesStartDate, salesEndDate, eventDate, eventTime, eventSaleStatus, genre, subGenre, venuesName, venuesPostalCode, venuesCity, venuesState, stateCode, countryName, countryCode, address, locationLongitude, locationLatitude, phoneNumber, officeHours, paytmentDetails, willCallDetail, parkingDetail, accessibleSeatingDetail, minPrice, maxPrice, seatMap, pleaseNote, promoterName, promoterDescription);
				  }
				  catch(Exception e)
				  { 
					msg = "Event cannot be inserted";
				  }
				  msg = "Event has been successfully added";
			  }					
			}else if(action.equals("update"))
			{
				
			  if(genre.equals("Hockey") || genre.equals("Football") || genre.equals("Basketball")){
				  allevents = MySqlDataStoreUtilities.getEvents();
				  if(!allevents.containsKey(eventId)){
					  msg = "Event not available";
					 
				  }
					  
			  }
			  if (msg.equals("good"))
			  {		
				
				  try
				  {
					msg = MySqlDataStoreUtilities.updateevents(eventId, eventName, eventImage, salesStartDate, salesEndDate, eventDate, eventTime, eventSaleStatus, genre, subGenre, venuesName, venuesPostalCode, venuesCity, venuesState, stateCode, countryName, countryCode, address, locationLongitude, locationLatitude, phoneNumber, officeHours, paytmentDetails, willCallDetail, parkingDetail, accessibleSeatingDetail, minPrice, maxPrice, seatMap, pleaseNote, promoterName, promoterDescription);
				  }
				  catch(Exception e)
				  { 
					msg = "Event cannot be updated";
				  }
				  msg = "Event has been successfully updated";
			  } 
			}else if(action.equals("delete"))
			{
				  msg = "bad";
				  allevents = MySqlDataStoreUtilities.getEvents();
				  if(allevents.containsKey(eventId)){
					  msg = "good";
				  }
			  
				  if (msg.equals("good"))
				  {		
					
					  try
					  {  
						
						 msg = MySqlDataStoreUtilities.deleteevents(eventId);
					  }
					  catch(Exception e)
					  { 
						msg = "Event cannot be deleted";
					  }
					   msg = "Event has been successfully deleted";
				  }else{
					  msg = "Event not available";
				  }
			}	
				
			pw.print("<section class='ftco-section contact-section'>"
				+ "<div class='container'>"
				+ "<div class='row justify-content-center mb-5 pb-3'>"
				+ "<div class='col-md-7 heading-section text-center ftco-animate'>"
				+ "<h2>"+msg+"</h2>"
				+ "</div></div></div></section>");
			utility.printHtml("Footer.html");
			
	}
}