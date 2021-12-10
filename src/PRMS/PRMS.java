package PRMS;

import Users.*;
import Property.*;
import Server.DatabaseManager;
import Server.*;
import java.util.ArrayList;

import SystemUI.*;

public class PRMS {
    private int fee;
    private int period;
    private DatabaseManager db;

    public PRMS(){
        fee = 20;
        period =60;
        db = new DatabaseManager();
    }
    //Renterr's controller function
    public ArrayList<Property> getProperties(Property searchCriteria){
        return db.SearchProperties(searchCriteria);
    }


    //Manger's controller function
    public String askReport(String start, String end){
        return db.getReport(start, end).Display();
    }

    public String changeFee(int fee){
        if(fee>=0){
            this.fee = fee;
            return "Fee changed";
        }
        else{
            return "Invalid Fee Amount.";
        }
    }

    public String changeFeePeriod(int period){
        if(period>=0){
            this.period = period;
            return "Period changed";
        }
        else{
            return "Invalid Period Amount.";
        }
    }

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
    public String registerProperty(Property p){
        db.addProperty(p);
        return "Property Registered in Database";
    }
    public String payFee(int houseID, int money){
        if (money >= fee){
            db.getProperty(houseID).setState("Active");
            return "Fee Paid";
        }
        else
            return "Not Enough Amount";
    }

    public String signIn(String username, String password){
        String type = db.checkAccount(username);
        return type;
    }

    public Landlord getLandlord(String username){
        return db.getLandlord(username);
    }

    public Manager getManager(String username){
        return db.getManager(username);
    }

    public RegisteredRenter getRegisteredRenter(String username){
        return db.getRegRenter(username);
    }

    public ArrayList<Property> managerPropertyEdit(){
        return db.getAllProperties();
    }

    public ArrayList<Property> landlordPropertyEdit(String landlord){
        return db.getLandlordProperties(landlord);
    }

    public void addUser(User user){
        if(user.getUsertype().equals("Landlord")){
            db.addLandlord((Landlord)user);
        }else if(user.getUsertype().equals("Manager")){
            db.addManager((Manager)user);
        }else{
            db.addRegRenter((RegisteredRenter)user);
        }
    }
    /*public static void main(String[] args){
        PRMS system = new PRMS();
    }*/
}
