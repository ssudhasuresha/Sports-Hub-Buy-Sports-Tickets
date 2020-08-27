import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportshub","root","rakshu");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void InsertEvents()
{
	try{
		
		getConnection();
		
		String truncatetableprod = "delete from  eventdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
		
				
		
		String insertEventQurey = "INSERT INTO  sportshub.eventdetails(eventId, eventName,eventImage,salesStartDate,salesEndDate,eventDate,eventTime,eventSaleStatus,genre,subGenre,venuesName,venuesPostalCode,venuesCity,venuesState,stateCode,countryName,countryCode,address,locationLongitude,locationLatitude,phoneNumber,officeHours,paytmentDetails,willCallDetail,parkingDetails,accessibleSeatingDetail,minPrice,maxPrice,seatMap,pleaseNote,promoterName,promoterDescription)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		for(Map.Entry<String,Event> entry : SaxParserDataStore.eventMap.entrySet())
		{   
	        Event event = entry.getValue();
			String name = "Event";
			PreparedStatement pst = conn.prepareStatement(insertEventQurey);
			pst.setString(1,event.getId());
			pst.setString(2,event.getEventName());
			pst.setString(3,event.getImageUrl());
			pst.setString(4,event.getSalesStartDate());
			pst.setString(5,event.getSalesEndDate());
			pst.setString(6,event.getEventDate());
			pst.setString(7,event.getEventTime());
			pst.setString(8,event.getEventSaleStatus());
			pst.setString(9,event.getGenre());
			pst.setString(10,event.getSubGenre());
			pst.setString(11,event.getVenuesName());
			pst.setString(12,event.getVenuesPostalCode());
			pst.setString(13,event.getVenuesCity());
			pst.setString(14,event.getVenuesState());
			pst.setString(15,event.getStateCode());
			pst.setString(16,event.getCountryName());
			pst.setString(17,event.getCountryCode());
			pst.setString(18,event.getAddress());
			pst.setDouble(19,event.getLocationLongitude());
			pst.setDouble(20,event.getLocationLatitude());
			pst.setString(21,event.getPhoneNumber());
			pst.setString(22,event.getOpenHours());
			pst.setString(23,event.getAcceptedPaymentDetail());
			pst.setString(24,event.getWillCallDetail());
			pst.setString(25,event.getParkingDetail());
			pst.setString(26,event.getAccessibleSeatingDetail());
			pst.setDouble(27,event.getMinPrice());
			pst.setDouble(28,event.getMaxPrice());
			pst.setString(29,event.getSeatMap());
			pst.setString(30,event.getPleaseNote());
			pst.setString(31,event.getPromoterName());
			pst.setString(32,event.getPromoterDescription());
			
			pst.executeUpdate();
			
		}
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
} 

public static void insertUser(String firstname,String lastname,String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO registration(firstname,lastname,username,password,usertype) "
		+ "VALUES (?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,firstname);
		pst.setString(2,lastname);
		pst.setString(3,username);
		pst.setString(4,password);
		// pst.setString(5,repassword);
		pst.setString(5,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("firstName"),rs.getString("lastName"),rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


public static HashMap<String,Event> getEvent(String id){

	HashMap<String,Event> hm=new HashMap<String,Event>();
	
	
	try{
		getConnection();
		String selectConsole="select * from  Eventdetails where eventId=?";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		pst.setString(1,id);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
			{	
				Event p = new Event(rs.getString("eventId"),rs.getString("eventName"),rs.getString("eventImage"),rs.getString("salesStartDate"),rs.getString("salesEndDate"),rs.getString("eventDate"),rs.getString("eventTime"),rs.getString("eventSaleStatus"),rs.getString("genre"),rs.getString("subGenre"),rs.getString("venuesName"),rs.getString("venuesPostalCode"),rs.getString("venuesCity"),rs.getString("venuesState"),rs.getString("stateCode"),rs.getString("countryName"),rs.getString("countryCode"),rs.getString("address"),rs.getDouble("locationLongitude"),rs.getDouble("locationLatitude"),rs.getString("phoneNumber"),rs.getString("officeHours"),rs.getString("paytmentDetails"),rs.getString("willCallDetail"),rs.getString("parkingDetails"),rs.getString("accessibleSeatingDetail"),rs.getDouble("minPrice"),rs.getDouble("maxPrice"),rs.getString("seatMap"),rs.getString("pleaseNote"), rs.getString("promoterName"),rs.getString("promoterDescription"));
			hm.put(rs.getString("eventId"), p);
			}
	} catch (Exception e){
		System.out.println("the issue"+e);

	}
	return hm;
}

public static HashMap<String,Event> getEvents(){
	HashMap<String,Event> hm=new HashMap<String,Event>();
	
	
	try{
		getConnection();
		String selectConsole="select * from  Eventdetails where maxPrice > 0 ";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
			{	
				
					Event p = new Event(rs.getString("eventName"),rs.getDouble("maxPrice"),rs.getString("eventDate"), rs.getString("eventTime"),rs.getString("subGenre"),rs.getString("genre"), rs.getString("eventImage"));
					hm.put(rs.getString("eventId"), p);
				
				
			}
	} catch (Exception e){

	}
	return hm;
}


public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from ticket";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("ticketId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("ticketId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("ticketId"));		
			System.out.println("data is"+rs.getInt("ticketId")+orderPayments.get(rs.getInt("ticketId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("ticketId"),rs.getString("username"),rs.getString("eventTitle"),rs.getDouble("amount"),rs.getString("customerAddress"),rs.getString("creditCardNum"), rs.getString("eventDateTime"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}

public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from ticket where ticketId=? and eventTitle=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,String newDate)
{
	try
	{
	
		getConnection();
		String insertIntoCustomerOrderQuery = "INSERT INTO ticket(username,eventTitle,amount,customerAddress,creditCardNum, eventDateTime) "
		+ "VALUES (?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		// pst.setInt(1,orderId);
		pst.setString(1,userName);
		pst.setString(2,orderName);
		pst.setDouble(3,orderPrice);
		pst.setString(4,userAddress);
		pst.setString(5,creditCardNo);
		pst.setString(6,newDate);
		pst.execute();

		// String anotherQuery = "update productdetails  SET quantity = quantity - 1 WHERE productName=?";
		// PreparedStatement pst1 = conn.prepareStatement(anotherQuery);
		// pst1.setString(1,orderName);
		// pst1.execute();
	}
	catch(Exception e)
	{
		System.out.println("sql " + e);
	}		
}


public static String addevents(String eventId,String eventName,String eventImage,String salesStartDate,String salesEndDate,String eventDate,String eventTime,String eventSaleStatus,String genre,String subGenre,String venuesName,String venuesPostalCode,String venuesCity,String venuesState,String stateCode,String countryName,String countryCode,String address,double locationLongitude,double locationLatitude,String phoneNumber,String officeHours,String paytmentDetails,String willCallDetail,String parkingDetail,String accessibleSeatingDetail,double minPrice,double maxPrice,String seatMap,String pleaseNote,String promoterName,String promoterDescription)
{
	String msg = "Event is added successfully";
	try{
		
		getConnection();
		String addEventsQurey = "INSERT INTO  eventdetails(eventId, eventName,eventImage,salesStartDate,salesEndDate,eventDate,eventTime,eventSaleStatus,genre,subGenre,venuesName,venuesPostalCode,venuesCity,venuesState,stateCode,countryName,countryCode,address,locationLongitude,locationLatitude,phoneNumber,officeHours,paytmentDetails,willCallDetail,parkingDetails,accessibleSeatingDetail,minPrice,maxPrice,seatMap,pleaseNote,promoterName,promoterDescription)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		   
			String name = "Event";
	        			
			PreparedStatement pst = conn.prepareStatement(addEventsQurey);
			
			pst.setString(1,eventId);
			pst.setString(2,eventName);
			pst.setString(3,eventImage);
			pst.setString(4,salesStartDate);
			pst.setString(5,salesEndDate);
			pst.setString(6,eventDate);
			pst.setString(7,eventTime);
			pst.setString(8,eventSaleStatus);
			pst.setString(9,genre);
			pst.setString(10,subGenre);
			pst.setString(11,venuesName);
			pst.setString(12,venuesPostalCode);
			pst.setString(13,venuesCity);
			pst.setString(14,venuesState);
			pst.setString(15,stateCode);
			pst.setString(16,countryName);
			pst.setString(17,countryCode);
			pst.setString(18,address);
			pst.setDouble(19,locationLongitude);
			pst.setDouble(20,locationLatitude);
			pst.setString(21,phoneNumber);
			pst.setString(22,officeHours);
			pst.setString(23,paytmentDetails);
			pst.setString(24,willCallDetail);
			pst.setString(25,parkingDetail);
			pst.setString(26,accessibleSeatingDetail);
			pst.setDouble(27,minPrice);
			pst.setDouble(28,maxPrice);
			pst.setString(29,seatMap);
			pst.setString(30,pleaseNote);
			pst.setString(31,promoterName);
			pst.setString(32,promoterDescription);
			
			pst.executeUpdate();	
			
	}
	catch(Exception e)
	{
		msg = "Erro while adding the Event";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateevents(String eventId,String eventName,String eventImage,String salesStartDate,String salesEndDate,String eventDate,String eventTime,String eventSaleStatus,String genre,String subGenre,String venuesName,String venuesPostalCode,String venuesCity,String venuesState,String stateCode,String countryName,String countryCode,String address,double locationLongitude,double locationLatitude,String phoneNumber,String officeHours,String paytmentDetails,String willCallDetail,String parkingDetail,String accessibleSeatingDetail,double minPrice,double maxPrice,String seatMap,String pleaseNote,String promoterName,String promoterDescription)
{ 
    String msg = "Event is updated successfully";
	try{
		
		getConnection();
		String updateEventQurey = "UPDATE eventdetails SET eventId=?, eventName=?, eventImage=?, salesStartDate=?, salesEndDate=?, eventDate=?, eventTime=?, eventSaleStatus=?, genre=?, subGenre=?, venuesName=?, venuesPostalCode=?, venuesCity=?, venuesState=?, stateCode=?, countryName=?, countryCode=?, address=?, locationLongitude=?, locationLatitude=?, phoneNumber=?, officeHours=?, paytmentDetails=?, willCallDetail=?,  parkingDetails=?, accessibleSeatingDetail=?, minPrice=?, maxPrice=?, seatMap=?, pleaseNote=?, promoterName=?, promoterDescription=? where eventId =?;" ;
			
			PreparedStatement pst = conn.prepareStatement(updateEventQurey);
			
			pst.setString(1,eventId);
			pst.setString(2,eventName);
			pst.setString(3,eventImage);
			pst.setString(4,salesStartDate);
			pst.setString(5,salesEndDate);
			pst.setString(6,eventDate);
			pst.setString(7,eventTime);
			pst.setString(8,eventSaleStatus);
			pst.setString(9,genre);
			pst.setString(10,subGenre);
			pst.setString(11,venuesName);
			pst.setString(12,venuesPostalCode);
			pst.setString(13,venuesCity);
			pst.setString(14,venuesState);
			pst.setString(15,stateCode);
			pst.setString(16,countryName);
			pst.setString(17,countryCode);
			pst.setString(18,address);
			pst.setDouble(19,locationLongitude);
			pst.setDouble(20,locationLatitude);
			pst.setString(21,phoneNumber);
			pst.setString(22,officeHours);
			pst.setString(23,paytmentDetails);
			pst.setString(24,willCallDetail);
			pst.setString(25,parkingDetail);
			pst.setString(26,accessibleSeatingDetail);
			pst.setDouble(27,minPrice);
			pst.setDouble(28,maxPrice);
			pst.setString(29,seatMap);
			pst.setString(30,pleaseNote);
			pst.setString(31,promoterName);
			pst.setString(32,promoterDescription);
			pst.setString(33,eventId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Event cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteevents(String eventId)
{   String msg = "Event is deleted successfully";
	try
	{
		
		getConnection();
		String deleteevensQuery ="Delete from eventdetails where eventId=?";
		PreparedStatement pst = conn.prepareStatement(deleteevensQuery);
		pst.setString(1,eventId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Event cannot be deleted";
	}
	return msg;
}

public static HashMap<String,Event> getEventBasedLocation(String city){

	HashMap<String,Event> hm=new HashMap<String,Event>();
	
	
	try{
		getConnection();
		String selectConsole="select * from  eventdetails where venuesCity=?";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		pst.setString(1,city);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
			{	Event p = new Event(rs.getString("eventName"),rs.getDouble("maxPrice"),rs.getString("eventDate"), rs.getString("eventTime"),rs.getString("subGenre"),rs.getString("genre"), rs.getString("eventImage"));
				hm.put(rs.getString("eventId"), p);
			}
	} catch (Exception e){

	}
	return hm;
}

public static ArrayList<String> getAllEvents(){

	ArrayList<String> hm=new ArrayList<String>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select eventName from  Eventdetails";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	
			hm.add(rs.getString("eventName"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;

}

public static int selectOrderSize(){
	// HashMap<String,Event> hm=new HashMap<String,Event>();
int i=0;	
	
	try{
		getConnection();
		String selectConsole="select max(ticketId) from ticket;";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
			{	
				System.out.println(rs.getString("max(ticketId)"));
					i = Integer.parseInt(rs.getString("max(ticketId)"));
				
				
			}
	} catch (Exception e){

	}
	return i;
}

<<<<<<< HEAD
public static HashMap<String,List<String>> getSaleCount(){

	HashMap<String,List<String>> hm=new HashMap<String,List<String>>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select c.ticketID,c.eventTitle,p.maxPrice ,count(c.ticketID) count, c.ticketID*p.maxPrice sales from ticket c, Eventdetails p where c.eventTitle = p.eventName and  p.maxPrice > 0.0 group by c.eventTitle;";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	
			List<String> a = new ArrayList<String>();
			a.add(rs.getString("p.maxPrice"));
			a.add(rs.getString("count"));
			a.add(rs.getString("sales"));
			hm.put(rs.getString("c.eventTitle"),a);
		}
	}
	catch(Exception e)
	{
	}
	return hm;

}
=======
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520


}	