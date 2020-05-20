import java.util.*;
import java.util.Map;



public class Event {
	private String id;
	private String eventId;
	private String eventName;
	private String eventImage;
	private String salesStartDate;
	private String salesEndDate;
	private String eventDate;
	private String eventTime;
	private String eventSaleStatus;
	private String genre;
	private String subGenre;
	private String venuesName;
	private String venuesPostalCode;
	private String venuesCity;
	private String venuesState;
	private String stateCode;
	private String countryName;
	private String countryCode;
	private String address;
	private double locationLongitude;
	private double locationLatitude;
	private String phoneNumber;
	private String officeHours;
	private String paytmentDetails;
	private String willCallDetail;
	private String parkingDetail;
	private String accessibleSeatingDetail;
	private double minPrice;
	private double maxPrice;
	private String seatMap;
	private String pleaseNote;
	private String promoterName;
	private String promoterDescription;
	
	
	public Event(String eventId,String eventName,String eventImage,String salesStartDate,String salesEndDate,String eventDate,String eventTime,String eventSaleStatus,String genre,String subGenre,String venuesName,String venuesPostalCode,String venuesCity,String venuesState,String stateCode,String countryName,String countryCode,String address,double locationLongitude,double locationLatitude,String phoneNumber,String officeHours,String paytmentDetails,String willCallDetail,String parkingDetail,String accessibleSeatingDetail,double minPrice,double maxPrice,String seatMap,String pleaseNote,String promoterName,String promoterDescription){
		// this.id = id;
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImage = eventImage;
		this.salesStartDate = salesStartDate;
		this.salesEndDate = salesEndDate;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventSaleStatus = eventSaleStatus;
		this.genre = genre;
		this.subGenre = subGenre;
		this.venuesName = venuesName;
		this.venuesPostalCode = venuesPostalCode;
		this.venuesCity = venuesCity;
		this.venuesState = venuesState;
		this.stateCode = stateCode;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.address = address;
		this.locationLongitude = locationLongitude;
		this.locationLatitude = locationLatitude;
		this.phoneNumber = phoneNumber;
		this.officeHours = officeHours;
		this.paytmentDetails = paytmentDetails;
		this.willCallDetail = willCallDetail;
		this.parkingDetail = parkingDetail;
		this.accessibleSeatingDetail = accessibleSeatingDetail;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.seatMap = seatMap;
		this.pleaseNote = pleaseNote;
		this.promoterName = promoterName;
		this.promoterDescription = promoterDescription;     		
	}
	public Event(String eventId, String eventName,Double maxPrice, String eventDate,String eventTime, String subGenre, String genre,String eventImage){
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventImage = eventImage;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.genre = genre;
		this.maxPrice = maxPrice;
		this.subGenre = subGenre;
	}

	public Event(String eventName,Double maxPrice, String eventDate,String eventTime, String subGenre, String genre,String eventImage){
		this.eventName = eventName;
		this.eventImage = eventImage;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.genre = genre;
		this.maxPrice = maxPrice;
		this.subGenre = subGenre;
	}
   
	public Event(){
		
	}
	public String getId() {
		return eventId;
	}
	public void setId(String id) {
		this.eventId = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getImageUrl() {
		return eventImage;
	}
	public void setImageUrl(String eventImage) {
		this.eventImage =eventImage;
	}

	public String getSalesStartDate() {
		return salesStartDate;
	}
	public void setSalesStartDate(String salesStartDate) {
		this.salesStartDate = salesStartDate;
	}
	public String getSalesEndDate() {
		return salesEndDate;
	}
	public void setSalesEndDate(String salesEndDate) {
		this.salesEndDate = salesEndDate;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventSaleStatus() {
		return eventSaleStatus;
	}

	public void setEventSaleStatus(String eventSaleStatus) {
		this.eventSaleStatus = eventSaleStatus;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getSubGenre() {
		return subGenre;
	}

	public void setSubGenre(String subGenre) {
		this.subGenre = subGenre;
	}
	
	public String getVenuesName() {
		return venuesName;
	}

	public void setVenuesName(String venuesName) {
		this.venuesName = venuesName;
	}
	
	public String getVenuesPostalCode() {
		return venuesPostalCode;
	}

	public void setVenuesPostalCode(String venuesPostalCode) {
		this.venuesPostalCode = venuesPostalCode;
	}
	
	public String getVenuesCity() {
		return venuesCity;
	}

	public void setVenuesCity(String venuesCity) {
		this.venuesCity = venuesCity;
	}
	
	public String getVenuesState() {
		return venuesState;
	}

	public void setVenuesState(String venuesState) {
		this.venuesState = venuesState;
	}
	
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	
	public double getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getOpenHours() {
		return officeHours;
	}

	public void setOpenHours(String officeHours) {
		this.officeHours = officeHours;
	}
	
	public String getAcceptedPaymentDetail() {
		return paytmentDetails;
	}

	public void setAcceptedPaymentDetail(String paytmentDetails) {
		this.paytmentDetails = paytmentDetails;
	}
	
	public String getWillCallDetail() {
		return willCallDetail;
	}

	public void setWillCallDetail(String willCallDetail) {
		this.willCallDetail = willCallDetail;
	}
	
	public String getParkingDetail() {
		return parkingDetail;
	}

	public void setParkingDetail(String parkingDetail) {
		this.parkingDetail = parkingDetail;
	}
	
	public String getAccessibleSeatingDetail() {
		return accessibleSeatingDetail;
	}

	public void setAccessibleSeatingDetail(String accessibleSeatingDetail) {
		this.accessibleSeatingDetail = accessibleSeatingDetail;
	}
	
	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	
	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	public String getSeatMap() {
		return seatMap;
	}

	public void setSeatMap(String seatMap) {
		this.seatMap = seatMap;
	}
	
	public String getPleaseNote() {
		return pleaseNote;
	}

	public void setPleaseNote(String pleaseNote) {
		this.pleaseNote = pleaseNote;
	}
	
	public String getPromoterName() {
		return promoterName;
	}

	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}
	
	public String getPromoterDescription() {
		return promoterDescription;
	}

	public void setPromoterDescription(String promoterDescription) {
		this.promoterDescription = promoterDescription;
	}
	
}
