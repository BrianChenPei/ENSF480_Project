package Property;

/**
 * @author Andres Caicedo <a 
 * href="mailto: acaicedo@ucalgary.ca</a> 
 * 
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 * 
 * @version 1.1
 * 
 * @since 1.0
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

    /**
     * Property constructor with no id, ls, feeStart, feeEnd, Lln, Lle parameters.
     * @param type
     * @param bed
     * @param bath
     * @param furn
     * @param add
     */
    public Property (String type, int bed, int bath, boolean furn, String add){
        this.type=type;
        this.bedroom = bed;
        this.bathroom = bath;
        this.furnished = furn;
        this.quadrant = add;
    }

    /**
     * Property constructor all parameters.
     * @param id
     * @param type
     * @param bed
     * @param bath
     * @param furn
     * @param add
     * @param ls
     * @param feeStart
     * @param feeEnd
     * @param Lln
     * @param Lle
     */
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
    
    /**
     * ID setter.
     * @param ID
     */
    public void setID(String ID) {
        this.id = ID;
    }

    /**
     * State setter.
     * @param newState
     */
    public void setState(String newState) {
        this.listingState = newState;
    }
    
    /**
     * Type setter.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * bedroom setter.
     * @param bdrm
     */
    public void setBedroom(int bdrm) {
        this.bedroom = bdrm;
    }
    
    /**
     * bathroom setter.
     * @param bathroom
     */
    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }
    
    /**
     * furnished setter.
     * @param furnish
     */
    public void setFurnished(boolean furnish) {
        this.furnished = furnish;
    }
    
    /**
     * quadrant setter.
     * @param location
     */
    public void setQuadrant(String location) {
        this.quadrant = location;
    }
    
    /**
     * fee setter.
     * @param fee
     */
    public void setFee(int fee) {
        this.fee = fee;
    }
    
    /**
     * fee period start setter.
     * @param start
     */
    public void setFeePeriodStart(String start) {
        this.feePeriodStart = start;
    }
    
    /**
     * fee period end setter.
     * @param end
     */
    public void setFeePeriodEnd(String end) {
        this.feePeriodEnd = end;
    }
    
    /**
     * landlord name setter.
     * @param name
     */
    public void setLandlordName(String name) {
        this.landlordName = name;
    }
    
    /**
     * landlord email setter;
     * @param email
     */
    public void setLandlordEmail(String email) {
        this.landlordEmail = email;
    }
    
    /**
     * ID getter.
     * @return String
     */
    public String getID() {
        return this.id;
    }
    
    /**
     * State getter.
     * @return String
     */
    public String getState() {
        return this.listingState;
    }
    
    /**
     * Type getter.
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Bedroom getter.
     * @return int
     */
    public int getBedRoom() {
        return this.bedroom;
    }

    /**
     * Bathroom getter.
     * @return int
     */
    public int getBathroom() {
        return this.bathroom;
    }

    /**
     * Funish getter.
     * @return boolean
     */
    public boolean getFurnish() {
        return this.furnished;
    }

    /**
     * Quadrant getter.
     * @return String
     */
    public String getQuadrant() {
        return this.quadrant;
    }
    
    /**
     * Fee getter
     * @return int
     */
    public int getFee() {
        return this.fee;
    }
    
    /**
     * Fee period start getter.
     * @return String
     */
    public String getFeePeriodStart() {
        return this.feePeriodStart;
    }

    /**
     * Fee period end getter.
     * @return String 
     */
    public String getFeePeriodEnd() {
        return this.feePeriodEnd;
    }
    
    /**
     * Landlord name getter.
     * @return String 
     */
    public String getLandlordName() {
        return this.landlordName;
    }
    
    /**
     * Landlord email getter.
     * @return String
     */
    public String getLandlordEmail() {
        return this.landlordEmail;
    }

    
}