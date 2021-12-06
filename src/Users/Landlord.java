package Users;

import java.util.ArrayList;
import Property.*;

public class Landlord implements User{
    private String name;
    private String email;
    private ArrayList<Property> rgedProperties;
    private String usertype = "Landlord";

    public Landlord(){
        name="";
        email="";
        rgedProperties = new ArrayList<Property>();
    }
    public Landlord(String n, String e){
        name=n;
        email=e;
        rgedProperties = new ArrayList<Property>();
    }

    public String getUsertype(){
        return usertype;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Property> getRgedProperties() {
        return this.rgedProperties;
    }

    public void setRgedProperties(ArrayList<Property> rgedProperties) {
        this.rgedProperties = rgedProperties;
    }
    
    public void RegisterProperties(Property p) {
        this.rgedProperties.add(p);
    }

}
