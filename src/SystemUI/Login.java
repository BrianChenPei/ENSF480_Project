package SystemUI;

import java.util.ArrayList;

import PRMS.*;
import Users.*;
/**
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan <a
 * href="mailto: kaitlin.culligan@ucalgary.ca">kaitlin.culligan@ucalgary.ca</a>
 */
class Login {
    public static Login onlyInstance;
    private PRMS prms;
    private RegisteredRenter registeredRenter;
    private Landlord landlord;
    private Manager manager;
    String type;
    /**
     * logs in user
     * @param username username to use
     * @param password password to use
     * @return true if user exists, false if not
     */
    public boolean login(String username, String password){
        String accountType = prms.signIn(username, password);
        type = accountType;
        if(accountType.equals("NE")){
            return false;
        }else if(accountType.equals("Landlord")){
            landlord = prms.getLandlord(username);
            if(landlord.getPassword().equals(password)){
                 return true;
            }
            landlord = null;
        }
        else if(accountType.equals("Manager")){
            manager = prms.getManager(username);
            if(manager.getPassword().equals(password)){
                return true;
           }
           manager = null;
        }
        else if(accountType.equals("Registered Renter")){
            registeredRenter = prms.getRegisteredRenter(username);
            registeredRenter.setNewProperties(prms.getRR().getNewProperties());
            if(registeredRenter.getPassword().equals(password)){
                return true;
           }
           registeredRenter = null;
        }
        return false;
    }


    public boolean logout(){
        landlord = null;
        manager = null;
        registeredRenter = null;
        type = null;
        return false;
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

    public Landlord getLandlord(){
        return landlord;
    }

    public Manager getManager(){
        return manager;
    }

    public RegisteredRenter getRegisteredRenter(){
        return registeredRenter;
    }

    public String getType(){
        return type;
    }
    /**
     * constructor
     */
    private Login(){
        prms = new PRMS();
    }
}
