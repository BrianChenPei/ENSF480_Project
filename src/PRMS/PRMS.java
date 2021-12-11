package PRMS;

import Users.*;
import Property.*;
import Server.DatabaseManager;
import Server.*;
import java.util.ArrayList;
import SystemUI.*;

/**
 * @author Andres Caicedo <a 
 * href="mailto: acaicedo@ucalgary.ca</a> 
 * 
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 * 
 * @author Zheng Chen <a
 * href="mailto: zheng.chen1@ucalgary.ca</a>
 * 
 * @author Kaitlin Culigan <a 
 * href="mailto: kaitlin.culligan@ucalgary.ca</a>
 * 
 * @version 1.1
 * 
 * @since 1.0
 */

public class PRMS {
    private int fee;
    private int period;
    private DatabaseManager db;
    private RegisteredRenter rr;
    private Observer observer;

    public PRMS(){
        fee = 20;
        period =60;
        db = new DatabaseManager();
        rr = new RegisteredRenter();
        observer = new Observer();
        rr.subscribeCriteria(observer);
        observer.subscribe(rr);
    }

    //Renterr's controller function
    /**
     * Gets all properties in database given search criteria
     * @param searchCriteria
     * @return ArrayList of all Properties given search criteria
     */
    public ArrayList<Property> getProperties(Property searchCriteria){
        return db.SearchProperties(searchCriteria);
    }


    //Manger's controller function
    /**
     * gets report from database
     * @param start
     * @param end
     * @return String
     */
    public String askReport(String start, String end){
        return db.getReport(start, end).Display();
    }

    /**
     * changes the fee
     * @param fee
     * @return String stating if fee is changed successfully
     */
    public String changeFee(int fee){
        if(fee>=0){
            this.fee = fee;
            return "Fee changed";
        }
        else{
            return "Invalid Fee Amount.";
        }
    }

    /**
     * changes fee period
     * @param period
     * @return String stating if fee period is changed successfully
     */
    public String changeFeePeriod(int period){
        if(period>=0){
            this.period = period;
            return "Period changed";
        }
        else{
            return "Invalid Period Amount.";
        }
    }

    /**
     * changes state of property
     * @param houseID
     * @param newState
     * @return String stating if state changed Successfully
     */
    public String changeState(int houseID, String newState){
        if(newState.equals("Rented")||newState.equals("Suspended")||
            newState.equals("Cancelled")||newState.equals("Active")){
            db.changeState(newState, houseID);
            return "You Have Changed Property State Successfully.";
            }
        else{
            return "Invalid State Entered.";
        }
    }

    //Landlord's controller function
    /**
     * registers property
     * @param p
     * @return String stating property has been registered
     */
    public String registerProperty(Property p){
        db.addProperty(p);
        observer.newPost(p);
        return "Property Registered in Database";
    }

    /**
     * pays fee a given property
     * @param houseID
     * @param money
     * @return String stating if fee is paid
     */
    public String payFee(int houseID, int money){
        if (money >= fee){
            db.getProperty(houseID).setState("Active");
            return "Fee Paid";
        }
        else
            return "Not Enough Amount";
    }

    /**
     * user sign in function.
     * @param username
     * @param password
     * @return String of user type
     */
    public String signIn(String username, String password){
        String type = db.checkAccount(username);
        return type;
    }

    /**
     * gets landlord information from database
     * @param username
     * @return
     */
    public Landlord getLandlord(String username){
        return db.getLandlord(username);
    }

    /**
     * gets manager information from database
     * @param username
     * @return
     */
    public Manager getManager(String username){
        return db.getManager(username);
    }

    /**
     * gets registered renter information from database
     * @param username
     * @return
     */
    public RegisteredRenter getRegisteredRenter(String username){
        return db.getRegRenter(username);
    }

    /**
     * gets all properties from database
     * @return ArrayList<Property>
     */
    public ArrayList<Property> managerPropertyEdit(){
        return db.getAllProperties();
    }

    /**
     * gets all landlord properties from database
     * @param landlord
     * @return ArrayList<Property>
     */
    public ArrayList<Property> landlordPropertyEdit(String landlord){
        return db.getLandlordProperties(landlord);
    }

    /**
     * adds a user to database using databaseManager functions
     * @param user
     */
    public void addUser(User user){
        if(user.getUsertype().equals("Landlord")){
            db.addLandlord((Landlord)user);
        }else if(user.getUsertype().equals("Manager")){
            db.addManager((Manager)user);
        }else{
            db.addRegRenter((RegisteredRenter)user);
        }
    }

    public RegisteredRenter getRR(){
        return rr;
    }

    /*public static void main(String[] args){
        PRMS system = new PRMS();
    }*/
}
