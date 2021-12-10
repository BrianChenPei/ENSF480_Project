package SystemUI;

import java.util.ArrayList;

/**
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan <a
 * href="mailto: kaitlin.culligan@ucalgary.ca">kaitlin.culligan@ucalgary.ca</a>
 */
class Login {
    private ArrayList<String> passwords;
    private ArrayList<String> usernames;
    public static Login onlyInstance;

    /**
     * logs in user
     * @param username username to use
     * @param password password to use
     * @return true if user exists, false if not
     */
    public boolean login(String username, String password){
        if(onlyInstance.usernames.contains(username) && onlyInstance.passwords.contains(password)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * returns only instance of Login class
     * @return onlyInstance
     */
    public static Login getOnlyInstance(){
        if(onlyInstance == null){
            onlyInstance = new Login();
        }
        return onlyInstance;
    }

    /**
     * adds a username to the login instance
     * @param user username to add
     */
    public void addUsername(String user){
        Login instance = getOnlyInstance();
        instance.getUsernames().add(user);
    }

    /**
     * sets a username at a particular index
     * @param index index to use
     * @param newUser new username to set
     */
    public void setUsername(int index, String newUser){
        Login instance = getOnlyInstance();
        instance.getUsernames().set(index, newUser);
    }

    /**
     * removes a username from the class
     * @param newUser username to remove
     */
    public void removeUsername(String newUser){
        Login instance = getOnlyInstance();
        int index =  instance.getUsernames().indexOf(newUser);
        instance.getUsernames().remove(index);
    }

    /**
     * returns the array list of usernames
     * @return usernames of onlyInstance
     */
    public ArrayList<String> getUsernames(){
        return getOnlyInstance().usernames;
    }
    /**
     * constructor
     */
    private Login(){
        passwords = new ArrayList<>();
        usernames = new ArrayList<>();
        //populate arraylists from PRMS
        passwords.add("test");
        usernames.add("user");
    }
}
