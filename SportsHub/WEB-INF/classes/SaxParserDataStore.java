
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    Event event;
    static HashMap<String,Event> eventMap;
    String consoleXmlFileName;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    eventMap = new HashMap<String, Event>();
	parseDocument();
	
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
		
        } catch (ParserConfigurationException e) {

            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for Event 
	boolean event_tag = false;
	
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
		
		
        if (elementName.equalsIgnoreCase("Event")) 
		{
			currentElement="Event";
			event = new Event();
            event.setId(attributes.getValue("id"));
		}
		
    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
		
        if (element.equals("Event")) {
			eventMap.put(event.getId(),event);
			return;
        }
		
		if (element.equalsIgnoreCase("event_id")) {
		    event.setEventId(elementValueRead);       
			return;
        }
 
        if (element.equalsIgnoreCase("event_name")) {
		    event.setEventName(elementValueRead);       
			return;
        }
        
		if (element.equalsIgnoreCase("url")) {
            event.setImageUrl(elementValueRead);        
			return;
	    }

		if (element.equalsIgnoreCase("sales_start_date")) {
            event.setSalesStartDate(elementValueRead);
	    return;
        }
		
        if(element.equalsIgnoreCase("sales_end_date")){
            event.setSalesEndDate(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("event_date")) {
            event.setEventDate(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("event_time")) {
            event.setEventTime(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("event_sale_status")) {
            event.setEventSaleStatus(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("genre")) {
            event.setGenre(elementValueRead);
	    return;
        }
        
        if(element.equalsIgnoreCase("subGenre")){
           event.setSubGenre(elementValueRead);
	    return;
        }
		
        if(element.equalsIgnoreCase("venues_name")){
            event.setVenuesName(elementValueRead);
 	    return;
        }
		
        if(element.equalsIgnoreCase("venues_postalCode")){
        	event.setVenuesPostalCode(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("venues_city")) {
        	event.setVenuesCity(elementValueRead);
	    return;
        }
		
        if (element.equalsIgnoreCase("venues_state")) {
        	event.setVenuesState(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("state_code")) {
        	event.setStateCode(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("contry_name")) {
        	event.setCountryName(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("country_code")) {
        	event.setCountryCode(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("address")) {
        	event.setAddress(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("location_longitude")) {
        	event.setLocationLongitude(Double.parseDouble(elementValueRead));
	    return;
        }
		
		if (element.equalsIgnoreCase("location_latitude")) {
        	event.setLocationLatitude(Double.parseDouble(elementValueRead));
	    return;
        }
		
		if (element.equalsIgnoreCase("phoneNumberDetail")) {
        	event.setPhoneNumber(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("openHoursDetail")) {
        	event.setOpenHours(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("acceptedPaymentDetail")) {
        	event.setAcceptedPaymentDetail(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("willCallDetail")) {
        	event.setWillCallDetail(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("parkingDetail")) {
        	event.setParkingDetail(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("accessibleSeatingDetail")) {
        	event.setAccessibleSeatingDetail(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("min_price")) {
        	event.setMinPrice(Double.parseDouble(elementValueRead));
	    return;
        }
		
		if (element.equalsIgnoreCase("max_price")) {
        	event.setMaxPrice(Double.parseDouble(elementValueRead));
	    return;
        }
		
		if (element.equalsIgnoreCase("seatmap")) {
        	event.setSeatMap(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("pleaseNote")) {
        	event.setPleaseNote(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("promoter_name")) {
        	event.setPromoterName(elementValueRead);
	    return;
        }
		
		if (element.equalsIgnoreCase("promoter_description")) {
        	event.setPromoterDescription(elementValueRead);
	    return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
        public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\EWA_Project\\Event_Data.xml");
    } 
	
	
	
}
