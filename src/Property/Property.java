package Property;

/**
 *
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 */

public class Property {
    protected String id;
    protected String listingState;

    private String type;
    private int bedroom;
    private int bathroom; 
    private boolean furnished;
    private String location;
    
    private int fee;
    private String feePeriodStart;
    private String feePeriodEnd;
    
    private String landlordName;
    private String landlordEmail;
    
    public void setID(String ID) {
        this.id = ID;
    }

    public void setState(String newState) {
        this.listingState = newState;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setBedroom(int bdrm) {
        this.bedroom = bdrm;
    }
    
    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }
    
    public void setFurnished(boolean furnish) {
        this.furnished = furnish;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setFee(int fee) {
        this.fee = fee;
    }
    
    public void setFeePeriodStart(String start) {
        this.feePeriodStart = start;
    }
    
    public void setFeePeriodEnd(String end) {
        this.feePeriodEnd = end;
    }
    
    public void setLandlordName(String name) {
        this.landlordName = name;
    }
    
    public void setLandlordEmail(String email) {
        this.landlordEmail = email;
    }
    
    public String getID() {
        return this.id;
    }
    
    public String getState() {
        return this.listingState;
    }
    
    public String getType() {
        return this.type;
    }

    public int getBedRoom() {
        return this.bedroom;
    }

    public int getBathroom() {
        return this.bathroom;
    }

    public boolean getFurnish() {
        return this.furnished;
    }

    public String getLocation() {
        return this.location;
    }
    
    public int getFee() {
        return this.fee;
    }
    
    public String getFeePeriodStart() {
        return this.feePeriodStart;
    }
    
    public String getFeePeriodEnd() {
        return this.feePeriodEnd;
    }
    
    public String getLandlordName() {
        return this.landlordName;
    }
    
    public String getLandlordEmail() {
        return this.landlordEmail;
    }

    
}