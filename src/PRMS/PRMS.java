package PRMS;

import Users.*;
import Property.*;
import Database.*;
import java.util.ArrayList;
import SystemUI.*;

public class PRMS {
    private int postingPeriod;
    private double postingFee;
    private Database db;
    private GUI gui;

    //Renterr's controller function
    public ArrayList<Property> searchProperty(String searchCri){
        
    }
    public void sendEmail (Property p){
    
    }

    public void subscribe(String searchCri){

    }

    public void unsubscribe(){
        
    }



    //Manger's controller function
    public void askReport(){
        
    }

    public void changeFee(double fee){

    }

    public void changeFeePeriod(int period){

    }

    public void MchangeState(String houseID, String newState){

    }

    //Landlord's controller function

    public void payFee(double money){

    }

    public void LchangeState(String houseID, String newState){

    }
}
