package Users;
import java.util.ArrayList; 
import Property.Property;



import java.util.ArrayList;
import Property.*;

public class Landlord implements User{
    private String userName;
    private String fname;
    private String usertype;
    private String lname;
    private String email;
    private String password;


    public Landlord(String userName, String type, String fname, String lname, String email, String password) {
        this.userName = userName;
        this.fname = fname;
        this.usertype = type;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }
    
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUsertype() {
        return this.usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
