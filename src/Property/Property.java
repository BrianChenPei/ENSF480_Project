package Property;

import java.io.Serializable;
import java.util.Random;

public class Property implements Serializable{
	private static final long serialVersionUID = 1L;
	private int ID;
	private String address;
	private String type;
	private int Bedrooms;
	private int Bathrooms;
	private boolean isFurnished;
	private String cityQuadrant;
	private String listingState = "Active";
	private int fee;
	private String landlordName;
	private String landlordEmail;

	public Property(String address, String type, int Bedrooms, int Bathrooms, boolean isFurnished, String cityQuadrant,
			String landlordName, String landlordEmail) {
		this.ID = new Random().nextInt(1000000);
		this.setAddress(address);
		this.setType(type);
		this.setBedrooms(Bedrooms);
		this.setBathrooms(Bathrooms);
		this.setFurnished(isFurnished);
		this.setCityQuadrant(cityQuadrant);
		this.setFee(fee);
		this.setLandlordName(landlordName);
		this.setLandlordEmail(landlordEmail);
	}
	
	public Property(int ID, String address, String type, int Bedrooms, int Bathrooms, boolean isFurnished, String cityQuadrant, 
			String listingState, String landlordName, String landlordEmail) {
		this.ID = ID;
		this.setAddress(address);
		this.setType(type);
		this.setBedrooms(Bedrooms);
		this.setBathrooms(Bathrooms);
		this.setFurnished(isFurnished);
		this.setCityQuadrant(cityQuadrant);
		this.setListingState(listingState);
		this.setFee(fee);
		this.setLandlordName(landlordName);
		this.setLandlordEmail(landlordEmail);
	}
	
	public Property(String address, String type, int Bedrooms, int Bathrooms, boolean isFurnished, String cityQuadrant, 
			int fee, String landlordName, String landlordEmail) {
		this.ID = new Random().nextInt(1000000);
		this.setAddress(address);
		this.setType(type);
		this.setBedrooms(Bedrooms);
		this.setBathrooms(Bathrooms);
		this.setFurnished(isFurnished);
		this.setCityQuadrant(cityQuadrant);
		this.setFee(fee);
		this.setLandlordName(landlordName);
		this.setLandlordEmail(landlordEmail);
	}

	public Property(int ID, String address, String type, int Bedrooms, int Bathrooms, boolean isFurnished, String cityQuadrant,
			String listingState, int fee, String landlordName, String landlordEmail) {
		this.ID = ID;
		this.setAddress(address);
		this.setType(type);
		this.setBedrooms(Bedrooms);
		this.setBathrooms(Bathrooms);
		this.setFurnished(isFurnished);
		this.setCityQuadrant(cityQuadrant);
		this.setListingState(listingState);
		this.setFee(fee); 
		this.setLandlordName(landlordName);
		this.setLandlordEmail(landlordEmail);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBedrooms() {
		return Bedrooms;
	}

	public void setBedrooms(int Bedrooms) {
		this.Bedrooms = Bedrooms;
	}

	public int getBathrooms() {
		return Bathrooms;
	}

	public void setBathrooms(int Bathrooms) {
		this.Bathrooms = Bathrooms;
	}

	public boolean isFurnished() {
		return isFurnished;
	}

	public void setFurnished(boolean isFurnished) {
		this.isFurnished = isFurnished;
	}

	public String getCityQuadrant() {
		return cityQuadrant;
	}

	public void setCityQuadrant(String cityQuadrant) {
		this.cityQuadrant = cityQuadrant;
	}

	public String getListingState() {
		return listingState;
	}

	public void setListingState(String listingState) {
		this.listingState = listingState;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getID() {
		return ID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLandlordName() {
		return landlordName;
	}

	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}

	public String getLandlordEmail() {
		return landlordEmail;
	}

	public void setLandlordEmail(String landlordEmail) {
		this.landlordEmail = landlordEmail;
	}
}