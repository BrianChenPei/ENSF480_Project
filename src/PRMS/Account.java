package PRMS;

/**
 *
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 */

public class Account {
    private String usernamel
	private String password;
	private String userType;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getUserType() {
		return this.userType;
	}
}
