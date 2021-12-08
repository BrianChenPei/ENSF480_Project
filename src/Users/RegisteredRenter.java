package Users;

public class RegisteredRenter extends Renter{
    private String userName;
    private String fname;
    private String usertype = "Registered";
    private String lname;
    private String email;
    private String password;

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

}
