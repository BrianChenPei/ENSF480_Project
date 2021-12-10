package Property;

/**
 *
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 */

public class Property {
    protected String id; //1
    private String type; //2
    
    private int bedroom; //3
    private int bathroom; //4
    private boolean furnished; //5
    private String quadrant; //6
    protected String listingState; //7

    private int fee; //8
    private String feePeriodStart; //9
    private String feePeriodEnd; //10
    
    private String landlordName; //11
    private String landlordEmail; //12

    //constructor for search criteria
    public Property (String type, int bed, int bath, boolean furn, String add){
        this.type=type;
        this.bedroom = bed;
        this.bathroom = bath;
        this.furnished = furn;
        this.quadrant = add;
    }

    public Property(String id, String type,
    int bed, int bath, boolean furn, String add, String ls,
    String feeStart, String feeEnd, String Lln, String Lle){

        this.id = id;
        this.listingState = ls;
        this.quadrant = add;
        this.type = type;
        this.bedroom = bed;
        this.bathroom = bath;
        this.furnished = furn;
        this.feePeriodStart = feeStart;
        this.feePeriodEnd = feeEnd;
        this.landlordName = Lln;
        this.landlordEmail = Lle;
    }
    
    
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
    
    public void setQuadrant(String location) {
        this.quadrant = location;
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

    public String getQuadrant() {
        return this.quadrant;
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