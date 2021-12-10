package Users;
import java.util.ArrayList; 
import Property.Property;

import java.util.ArrayList;
import Property.*;

/**
 * @author Zheng Chen <a
 * href="mailto: zheng.chen1@ucalgary.ca</a>
 * 
 * @version 1.0
 * 
 * @since 1.0
 */

public class Landlord implements User{
    private String userName;
    private String fname;
    private String usertype = "Landlord";
    private String lname;
    private String email;
    private String password;

    /**
     * Landlord Constructor.
     * @param userName
     * @param type
     * @param fname
     * @param lname
     * @param email
     * @param password
     */
    public Landlord(String userName, String type, String fname, String lname, String email, String password) {
        this.userName = userName;
        this.fname = fname;
        this.usertype = type;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }
    
    /**
     * username getter.
     * @return String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * username setter.
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * First name getter.
     * @return String
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * first name setter.
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * user type getter.
     * @return String
     */
    public String getUsertype() {
        return this.usertype;
    }

    /**
     * user type setter.
     * @param usertype
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    /**
     * last name getter.
     * @return String
     */
    public String getLname() {
        return this.lname;
    }

    /**
     * last name setter.
     * @param lname
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * email getter.
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * email setter.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * password getter.
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * password setter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
