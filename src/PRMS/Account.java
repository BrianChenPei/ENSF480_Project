package PRMS;

/**
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 * 
 * @version 1.0
 * 
 * @since 1.0
 */

public class Account {
    private String username;
	private String password;
	private String userType;
	
	/**
	 * Account constructor.
	 * @param username
	 * @param password
	 * @param userType
	 */
	public Account(String username, String password, String userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	/**
	 * username setter
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * password setter.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * usertype setter.
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * username getter.
	 * @return String username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * password getter.
	 * @return String password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * usertype getter.
	 * @return String usertype
	 */
	public String getUserType() {
		return this.userType;
	}
}
