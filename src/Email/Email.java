package Email;

/**
 *
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 */

public class Email {
    private String message;
	private String emailAddress;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setEmail(String email) {
		this.emailAddress = email;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void sendTo() {
		// not completed
	}
}
