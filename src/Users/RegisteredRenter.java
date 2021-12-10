package Users;

import Property.*;
import Server.*;
import java.util.ArrayList;

/**
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 * 
 * @author Zheng Chen <a
 * href="mailto: zheng.chen1@ucalgary.ca</a>
 * 
 * @version 1.3
 * 
 * @since 1.0
 */

public class RegisteredRenter implements User{
    private String userName;
    private String fname;
    private String usertype;
    private String lname;
    private String email;
    private String password;
	
	private Observer channel = new Observer();
	
	private DatabaseManager data = new DatabaseManager();

	/**
     * Registered renter constructor.
     * @param userName
     * @param type
     * @param fname
     * @param lname
     * @param email
     * @param password
     */
    public RegisteredRenter(String userName, String type, String fname, String lname, String email, String password) {
        this.userName = userName;
        this.fname = fname;
        this.lname = lname;
        this.usertype = type;
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
     * first name getter.
     * @return String
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * First name setter.
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
     * usertype setter.
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
     * password setter.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
	
    /**
     * fetches property gien search criteria from databaseManager and outputs properties
     * @param searchCriteria
     */
    public void update(Property searchCriteria) {
		for(Property p : data.SearchProperties(searchCriteria)) {
			System.out.println(p);
		}
	}
	
    /**
     * sets the "channel" for the criteria that the registered renter wants to subcribe to
     * @param ch
     */
	public void subscribeCriteria(Observer ch) {
		channel = ch;
	}

/*
	public static void main(String[] args){
        RegisteredRenter r1 = new RegisteredRenter("bob1", "Registered Renter", "Bob", "Ross", "blah@gmail.com", "12345");
		
		Property p = new Property("fas", "sfd", 2, 3, false, "rw", "sdf", 3, "hgfh", "ytryr", "ghjg", "sdfg");
		
		ArrayList<Property> prop = new ArrayList<Property>();
		
		prop.add(p);

		
    }
*/
}
