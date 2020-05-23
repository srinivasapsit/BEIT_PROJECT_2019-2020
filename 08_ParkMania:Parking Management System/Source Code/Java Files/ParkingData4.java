package com.example.admin.signup;


/**
 * Created by Admin on 22-09-2019.
 */



/**
 * Created by Admin on 21-09-2019.
 */

public class ParkingData4 extends UserProfile{

    // public String vehicleType;
    // public String zone;
    public String nearbyslot;

    /*public ParkingData(String userMN,String userName,String userEmail,String userPassword,
                       String vehicleType,String Zone,String slot){
        super(userMN,userName,userEmail,userPassword);
        this.vehicleType=vehicleType;
        this.Zone=Zone;
        this.slot=slot;

    }*/

    public ParkingData4(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo,
                        String nearbyslot){
        super(userMN,userName,userEmail,userPassword,userVehicleNo);
        this.nearbyslot=nearbyslot;


    }
}
