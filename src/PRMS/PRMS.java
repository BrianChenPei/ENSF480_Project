package PRMS;

import Users.*;
import Property.*;
import Server.DatabaseManager;
import Server.*;
import java.util.ArrayList;

import Email.Email;
import SystemUI.*;

public class PRMS {
    private int postingPeriod;
    private int postingFee;
    private DatabaseManager db;
    private GUI gui;

    public PRMS(){
        postingPeriod =60;
        postingFee =200;
        db = new DatabaseManager();
        gui = new GUI();
    }
    //Renterr's controller function
    public ArrayList<Property> getProperties(Property searchCriteria){
        return db.getProperties(searchCriteria);
    }
    public void sendEmail (String message, Property p){
        Email em = new Email(message, p.getLandlordEmail());
        em.sendTo();
    }

    //Manger's controller function
    public String askReport(String start, String end){
        return db.getReport(start, end).Display();
    }

    public String changeFee(int fee){
        if(fee>=0){
            postingFee = fee;
            return "You Have Changed the Posting Fee.";
        }
        else{
            return "Invalid Fee Amount.";
        }
    }

    public String changeFeePeriod(int period){
        if(postingPeriod>0){
            postingFee = period;
            return "You Have Changed the Posting Period.";
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
        if(money<=0) return "Invalid Payment Amount";
        Property p = db.getProperty(houseID);
        if(p!=null){
            int earlyFee = p.getFee();
            db.getProperty(houseID).setFee(earlyFee+money);
            if( db.getProperty(houseID).getFee() >= postingFee){
                db.getProperty(houseID).setState("Active");
            }
            return "Fee Payment Succeed";
        }
        return "Invalid Property";
    }

    /*public static void main(String[] args){
        PRMS system = new PRMS();
    }*/
}
