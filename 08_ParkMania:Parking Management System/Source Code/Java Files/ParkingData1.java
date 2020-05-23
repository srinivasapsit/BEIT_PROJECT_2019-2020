package com.example.admin.signup;

/**
 * Created by Admin on 22-09-2019.
 */



/**
 * Created by Admin on 21-09-2019.
 */

public class ParkingData1 extends UserProfile{

   // public String vehicleType;
    public String zone;

    public ParkingData1(){

    }
   // public String slot;

    /*public ParkingData(String userMN,String userName,String userEmail,String userPassword,
                       String vehicleType,String Zone,String slot){
        super(userMN,userName,userEmail,userPassword);
        this.vehicleType=vehicleType;
        this.Zone=Zone;
        this.slot=slot;

    }*/

    public ParkingData1(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo,
                       String zone){
        super(userMN,userName,userEmail,userPassword,userVehicleNo);
        this.zone=zone;


    }

    public String getZone(){
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }
}

