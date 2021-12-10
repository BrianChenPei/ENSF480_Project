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
        return db.getProperties(searchCriteria);
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
    public String payFee(int houseID, int money){
        if (money >= fee){
            db.getProperty(houseID).setState("Active");
            return "Fee Paid";
        }
        else
            return "Not Enough Amount";
    }

    /*public static void main(String[] args){
        PRMS system = new PRMS();
    }*/
}
