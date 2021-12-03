package Property;

/**
 *
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 */

public class Property {
    protected String id;
    protected String state;
    
    private String type;
    private int bedroom;
    private int bathroom; 
    private boolean furnished;
    private String location;
    
    public void setState(String newState) {
        this.state = newState;
    }
    
    public String getState() {
        return this.state;
    }
    
    public String getID() {
        return this.id;
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

    public String getType() {
        return this.type;
    }
}
