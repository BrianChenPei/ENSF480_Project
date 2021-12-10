package PRMS;

import Users.*;
import Property.*;
import Server.DatabaseManager;
import Server.*;
import java.util.ArrayList;

import Email.Email;
import SystemUI.*;

public class PRMS {

    private DatabaseManager db;
    private GUI gui;

    public PRMS(){
        db = new DatabaseManager();
        gui = new GUI();
    }
    //Renterr's controller function
    public ArrayList<Property> getProperties(Property searchCriteria){
        return db.getProperties(searchCriteria);
    }


    //Manger's controller function
    public String askReport(String start, String end){
        return db.getReport(start, end).Display();
    }

    public String changeFee(int houseID, int fee){
        if(fee>=0){
            db.getProperty(houseID).setFee(fee);
            return "Property Fee changed";
        }
        else{
            return "Invalid Fee Amount.";
        }
    }

    public String changeFeePeriod(int houseID, String start, String end){
        db.getProperty(houseID).setFeePeriodStart(start);
        db.getProperty(houseID).setFeePeriodEnd(end);
        return "Property Period Changed";
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
    public String payFee(int houseID, int money){
        if (money >= db.getProperty(houseID).getFee()){
            return "Fee Paid";
        }
        else
            return "Not Enough Amount";
    }

    /*public static void main(String[] args){
        PRMS system = new PRMS();
    }*/
}
