package Users;

import Server.*;
import java.util.ArrayList;

public class RegisteredRenter extends Renter{
    private String userName;
    private String fname;
    private String usertype;
    private String lname;
    private String email;
    private String password;
	
	private Observer channel = new Observer();
	
	private DatabaseManager data = new DatabaseManager();
	
    public RegisteredRenter(String userName, String type, String fname, String lname, String email, String password) {
        this.userName = userName;
        this.fname = fname;
        this.lname = lname;
        this.usertype = type;
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
	
    public void update(String searchCriteria) {
		for(Property p : data.getProperties(searchCriteria)) {
			System.out.println(p);
		}
	}
	
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
