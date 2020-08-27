import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
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
			 message="unsuccessful";
		     return message;
		}
	}
	
	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,Event> data;
		data=getData();
		
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Event p=(Event)pi.getValue();                   
                if (p.getEventName().toLowerCase().startsWith(searchId))
                {
                        sb.append("<product>");
                        sb.append("<id>" + pi.getKey() + "</id>");
                        sb.append("<productName>" + p.getEventName() + "</productName>");
                        sb.append("</product>");
                }
			}
       }
	   
	   return sb;
	}
	
	public static HashMap<String,Event> getData()
	{
		HashMap<String,Event> hm=new HashMap<String,Event>();
		try
		{
			getConnection();
			
		    String selectproduct="select * from  Eventdetails;";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	Event p = new Event(rs.getString("eventName"),rs.getDouble("maxPrice"),rs.getString("eventDate"), rs.getString("eventTime"),rs.getString("subGenre"),rs.getString("genre"), rs.getString("eventImage"));
				hm.put(rs.getString("eventId"), p);	
		}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}
	// public static void storeData(HashMap<String,Product> productdata)
	// {
	// 	try
	// 	{
		
	// 		getConnection();
				
	// 		String insertIntoProductQuery = "INSERT INTO product(productId,productName,image,retailer,productCondition,productPrice,productType,discount) "
	// 		+ "VALUES (?,?,?,?,?,?,?,?);";	
	// 		for(Map.Entry<String, Product> entry : productdata.entrySet())
	// 		{	

	// 			PreparedStatement pst = conn.prepareStatement(insertIntoProductQuery);
	// 			//set the parameter for each column and execute the prepared statement
	// 			pst.setString(1,entry.getValue().getId());
	// 			pst.setString(2,entry.getValue().getName());
	// 			pst.setString(3,entry.getValue().getImage());
	// 			pst.setString(4,entry.getValue().getRetailer());
	// 			pst.setString(5,entry.getValue().getCondition());
	// 			pst.setDouble(6,entry.getValue().getPrice());
	// 			pst.setString(7,entry.getValue().getType());
	// 			pst.setDouble(8,entry.getValue().getDiscount());
	// 			pst.execute();
	// 		}
	// 	}
	// 	catch(Exception e)
	// 	{	
	
	// 	}		
	// }

}
