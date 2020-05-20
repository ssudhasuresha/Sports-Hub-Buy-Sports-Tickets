import java.io.*;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EventModify")

public class EventModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		if(action.equals("Addevent"))
		{
			
			
			pw.print("<section class='ftco-section contact-section'>"
				+ "<div class='container'>"
				+ "<div class='row justify-content-center mb-5 pb-3'>"
				+ "<div class='col-md-7 heading-section text-center ftco-animate'>"
				+ "<h2>Add Event</h2>"
				+ "</div></div>");
				
			
			pw.print("<div class='row block-9'>"
					+ "<div class='col-md-6 d-flex' style='margin-left:340px;'>"
			        + "<form method='get' action='EventCrud' class='bg-light p-5 contact-form'>"
					+ "<h3>Event Id</h3><div class='form-group'><input type='text' name='eventId' value='' class='form-control' required></input></div>"
					+ "<h3>Event Name</h3><div class='form-group'><input type='text' name='eventName' value='' class='form-control' required></input></div>"
					+ "<h3>Event Image</h3><div class='form-group'><input type='text' name='eventImage' value='' class='form-control'></input></div>"
					+ "<h3>Sales Start Date</h3><div class='form-group'><input type='text' name='salesStartDate' value='' class='form-control' required></input></div>" 
					+ "<h3>Sales End Date</h3><div class='form-group'><input type='text' name='salesEndDate' value='' class='form-control' required></input></div>" 
					+ "<h3>Event Date</h3><div class='form-group'><input type='text' name='eventDate' value='' class='form-control' required></input></div>" 
					+ "<h3>Event Time</h3><div class='form-group'><input type='text' name='eventTime' value='' class='form-control' required></input></div>"
					+ "<h3>Event Sales Status</h3><div class='form-group'><input type='text' name='eventSalesStatus' value='' class='form-control' required></input></div>" 
					+ "<h3>Genre</h3><div class='form-group'><select name='genre' class='form-control'><option value='' selected>Please Select Genre</option><option value='Hockey'>Hockey</option><option value='Football'>Football</option><option value='Basketball'>Basketball</option><option value='Golf'>Golf</option><option value='Wrestling'>Wrestling</option></select></div>" 
					+ "<h3>Sub Genre</h3><div class='form-group'><select name='subGenre' class='form-control'><option value='' selected>Please Select SubGenre</option><option value='NHL'>NHL</option><option value='NFL'>NFL</option><option value='NBA'>NBA</option><option value='College'>College</option><option value='Minor League'>Minor League</option><option value='PGA Tour'>PGA Tour</option><option value='Wrestling'>Wrestling</option></select></div>" 
					+ "<h3>Venue Name</h3><div class='form-group'><input type='text' name='venueName' value='' class='form-control' required></input></div>" 
					+ "<h3>Venue Postal Code</h3><div class='form-group'><input type='text' name='venuePostalCode' value='' class='form-control' required></input></div>" 
					+ "<h3>Venue City</h3><div class='form-group'><input type='text' name='venueCity' value='' class='form-control' required></input></div>" 
					+ "<h3>Venue State</h3><div class='form-group'><input type='text' name='venueState' value='' class='form-control' required></input></div>"
					+ "<h3>State Code</h3><div class='form-group'><input type='text' name='stateCode' value='' class='form-control' required></input></div>" 
					+ "<h3>Country</h3><div class='form-group'><input type='text' name='country' value='' class='form-control' required></input></div>" 
					+ "<h3>Country Code</h3><div class='form-group'><input type='text' name='countryCode' value='' class='form-control' required></input></div>" 
					+ "<h3>Address</h3><div class='form-group'><input type='text' name='address' value='' class='form-control' required></input></div>"
					+ "<h3>Longitude</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='long' value='' class='form-control'></input></div>" 
					+ "<h3>Latitude</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='lati' value='' class='form-control'></input></div>" 
					+ "<h3>Phone Details</h3><div class='form-group'><input type='text' name='phoneDetails' value='' class='form-control'></input></div>" 
					+ "<h3>Office Hours</h3><div class='form-group'><input type='text' name='officeHours' value='' class='form-control'></input></div>"
					+ "<h3>Payment Details</h3><div class='form-group'><input type='text' name='paymentDetails' value='' class='form-control'></input></div>"
					+ "<h3>Will Call Details</h3><div class='form-group'><input type='text' name='willCallDetails' value='' class='form-control'></input></div>"
					+ "<h3>Parking Details</h3><div class='form-group'><input type='text' name='parkingDetails' value='' class='form-control'></input></div>"
					+ "<h3>Accessible Seating Details</h3><div class='form-group'><input type='text' name='accessibleSeatingDetails' value='' class='form-control'></input></div>"
					+ "<h3>Min Price</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='minPrice' value='' class='form-control' required></input></div>" 
					+ "<h3>Max Price</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='maxPrice' value='' class='form-control' required></input></div>" 
					+ "<h3>Seat Map</h3><div class='form-group'><input type='text' name='seatMap' value='' class='form-control'></input></div>"
					+ "<h3>Please Note</h3><div class='form-group'><input type='text' name='pleaseNote' value='' class='form-control'></input></div>" 
					+ "<h3>Promoter Name</h3><div class='form-group'><input type='text' name='promoterName' value='' class='form-control'></input></div>" 
					+ "<h3>Promoter Description</h3><div class='form-group'><input type='text' name='promoterDescription' value='' class='form-control'></input></div>" 
					+ "<div class='form-group'><input type='submit' name='button' value='add' class='btn btn-primary py-3 px-5'></div>" 
					+ "</form></div></div></div></section>");
			
		
		
		}else if (action.equals("Updateevent"))
		{
			HashMap<String,Event> allevents = new HashMap<String,Event> ();
			String eventId = request.getParameter("id");
			System.out.println(eventId);
				try{
					
					allevents = MySqlDataStoreUtilities.getEvent(eventId);
				} catch(Exception e){

					System.out.println("the issue"+e);
				}
				System.out.println(allevents);
		    pw.print("<section class='ftco-section contact-section'>"
				+ "<div class='container'>"
				+ "<div class='row justify-content-center mb-5 pb-3'>"
				+ "<div class='col-md-7 heading-section text-center ftco-animate'>"
				+ "<h2>Login</h2>"
				+ "</div></div>");
				
				for(Map.Entry<String, Event> entry : allevents.entrySet()){
					Event g = entry.getValue();
			pw.print("<div class='row block-9'>"
					+ "<div class='col-md-6 d-flex' style='margin-left:340px;'>"
			        + "<form method='get' action='EventCrud' class='bg-light p-5 contact-form'>"
					+ "<h3>Event Id</h3><div class='form-group'><input type='text' name='eventId' value='"+entry.getKey()+"' class='form-control' readonly></input></div>"
					+ "<h3>Event Name</h3><div class='form-group'><input type='text' name='eventName' value='"+g.getEventName()+"' class='form-control' required></input></div>"
					+ "<h3>Event Image</h3><div class='form-group'><input type='text' name='eventImage' value='"+g.getImageUrl()+"' class='form-control'></input></div>"
					+ "<h3>Sales Start Date</h3><div class='form-group'><input type='text' name='salesStartDate' value='"+g.getSalesStartDate()+"' class='form-control'></input></div>" 
					+ "<h3>Sales End Date</h3><div class='form-group'><input type='text' name='salesEndDate' value='"+g.getSalesEndDate()+"' class='form-control'></input></div>" 
					+ "<h3>Event Date</h3><div class='form-group'><input type='text' name='eventDate' value='"+g.getEventDate()+"' class='form-control' required></input></div>" 
					+ "<h3>Event Time</h3><div class='form-group'><input type='text' name='eventTime' value='"+g.getEventTime()+"' class='form-control' required></input></div>"
					+ "<h3>Event Sales Status</h3><div class='form-group'><input type='text' name='eventSalesStatus' value='"+g.getEventSaleStatus()+"' class='form-control'></input></div>" 
					+ "<h3>Genre</h3><div class='form-group'><select name='genre' class='form-control'><option value='"+g.getGenre()+"' selected>Please Select Genre</option><option value='Hockey'>Hockey</option><option value='Football'>Football</option><option value='Basketball'>Basketball</option><option value='Golf'>Golf</option><option value='Wrestling'>Wrestling</option></select></div>" 
					+ "<h3>Sub Genre</h3><div class='form-group'><select name='subGenre' class='form-control'><option value='"+g.getSubGenre()+"' selected>Please Select SubGenre</option><option value='NHL'>NHL</option><option value='NFL'>NFL</option><option value='NBA'>NBA</option><option value='College'>College</option><option value='Minor League'>Minor League</option><option value='PGA Tour'>PGA Tour</option><option value='Wrestling'>Wrestling</option></select></div>" 
					+ "<h3>Venue Name</h3><div class='form-group'><input type='text' name='venueName' value='"+g.getVenuesName()+"' class='form-control'></input></div>" 
					+ "<h3>Venue Postal Code</h3><div class='form-group'><input type='text' name='venuePostalCode' value='"+g.getVenuesPostalCode()+"' class='form-control'></input></div>" 
					+ "<h3>Venue City</h3><div class='form-group'><input type='text' name='venueCity' value='"+g.getVenuesCity()+"' class='form-control'></input></div>" 
					+ "<h3>Venue State</h3><div class='form-group'><input type='text' name='venueState' value='"+g.getVenuesState()+"' class='form-control'></input></div>"
					+ "<h3>State Code</h3><div class='form-group'><input type='text' name='stateCode' value='"+g.getStateCode()+"' class='form-control'></input></div>" 
					+ "<h3>Country</h3><div class='form-group'><input type='text' name='country' value='"+g.getCountryName()+"' class='form-control'></input></div>" 
					+ "<h3>Country Code</h3><div class='form-group'><input type='text' name='countryCode' value='"+g.getCountryCode()+"' class='form-control'></input></div>" 
					+ "<h3>Address</h3><div class='form-group'><input type='text' name='address' value='"+g.getAddress()+"' class='form-control'></input></div>"
					+ "<h3>Longitude</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='long' value='"+g.getLocationLongitude()+"' class='form-control'></input></div>" 
					+ "<h3>Latitude</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='lati' value='"+g.getLocationLatitude()+"' class='form-control'></input></div>" 
					+ "<h3>Phone Details</h3><div class='form-group'><input type='text' name='phoneDetails' value='"+g.getPhoneNumber()+"' class='form-control'></input></div>" 
					+ "<h3>Office Hours</h3><div class='form-group'><input type='text' name='officeHours' value='"+g.getOpenHours()+"' class='form-control'></input></div>"
					+ "<h3>Payment Details</h3><div class='form-group'><input type='text' name='paymentDetails' value='"+g.getAcceptedPaymentDetail()+"' class='form-control'></input></div>"
					+ "<h3>Will Call Details</h3><div class='form-group'><input type='text' name='willCallDetails' value='"+g.getWillCallDetail()+"' class='form-control'></input></div>"
					+ "<h3>Parking Details</h3><div class='form-group'><input type='text' name='parkingDetails' value='"+g.getParkingDetail()+"' class='form-control'></input></div>"
					+ "<h3>Accessible Seating Details</h3><div class='form-group'><input type='text' name='accessibleSeatingDetails' value='"+g.getAccessibleSeatingDetail()+"' class='form-control'></input></div>"
					+ "<h3>Min Price</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='minPrice' value='"+g.getMinPrice()+"' class='form-control' required></input></div>" 
					+ "<h3>Max Price</h3><div class='form-group'><input type='number' step='any' placeholder='please enter numeric data' name='maxPrice' value='"+g.getMaxPrice()+"' class='form-control' required></input></div>" 
					+ "<h3>Seat Map</h3><div class='form-group'><input type='text' name='seatMap' value='"+g.getSeatMap()+"' class='form-control'></input></div>"
					+ "<h3>Please Note</h3><div class='form-group'><input type='text' name='pleaseNote' value='"+g.getPleaseNote()+"' class='form-control'></input></div>" 
					+ "<h3>Promoter Name</h3><div class='form-group'><input type='text' name='promoterName' value='"+g.getPromoterName()+"' class='form-control'></input></div>" 
					+ "<h3>Promoter Description</h3><div class='form-group'><input type='text' name='promoterDescription' value='"+g.getPromoterDescription()+"' class='form-control'></input></div>" 
					+ "<div class='form-group'><input type='submit' name='button' value='update' class='btn btn-primary py-3 px-5'></div>" 
					+ "</form></div></div>");
				}
				pw.print("</div></section>");
		}else
		{
			String eventId = request.getParameter("id");
			try{
                MySqlDataStoreUtilities.deleteevents(eventId);
                pw.print("<script>var r = confirm('Product is deleted successfully');if(r==true || r==false){window.location.href ='GameList';}</script>");
            } catch(Exception e){
                pw.print("<script>var r = confirm('Product is not deleted');if(r==true || r==false){window.location.href ='GameList';}</script>");
            }
			// pw.print("<section class='ftco-section contact-section'>"
			// 	+ "<div class='container'>"
			// 	+ "<div class='row justify-content-center mb-5 pb-3'>"
			// 	+ "<div class='col-md-7 heading-section text-center ftco-animate'>"
			// 	+ "<h2>Login</h2>"
			// 	+ "</div></div>");
				
			// pw.print("<div class='row block-9'>"
			// 		+ "<div class='col-md-6 d-flex' style='margin-left:340px;'>"
			//         + "<form method='get' action='EventCrud' class='bg-light p-5 contact-form'>"
			// 		+ "<h3>Event Id</h3><div class='form-group'><input type='text' name='eventId' value='' class='form-control' required></input></div>"
			// 		+ "<div class='form-group'><input type='submit' name='button' value='delete' class='btn btn-primary py-3 px-5'></div>" 
			// 		+ "</form></div></div></div></section>");
			
		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}