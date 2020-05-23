package com.example.admin.signup;


/**
 * Created by Admin on 22-09-2019.
 */



/**
 * Created by Admin on 21-09-2019.
 */

public class ParkingData2 extends UserProfile{

    // public String vehicleType;
   // public String zone;
     public String slot;
    public ParkingData2(){

    }

    /*public ParkingData(String userMN,String userName,String userEmail,String userPassword,
                       String vehicleType,String Zone,String slot){
        super(userMN,userName,userEmail,userPassword);
        this.vehicleType=vehicleType;
        this.Zone=Zone;
        this.slot=slot;

    }*/

    public ParkingData2(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo,
                        String slot){
        super(userMN,userName,userEmail,userPassword,userVehicleNo);
        this.slot=slot;


    }

    public String getSlot(){
        return slot;
    }
    public void setSlot(String slot) {
        this.slot = slot;
    }
    }

   /* public String getSlot1() {
        return slot;
    }
    public void setSlot1(String slot) {
        this.slot = slot;
    }

    public String getSlot2() {
        return slot;
    }
    public void setSlot2(String slot) {
        this.slot = slot;
    }

    public String getSlot3() {
        return slot;
    }
    public void setSlot3(String slot) {
        this.slot = slot;
    }

    public String getSlot4() {
        return slot;
    }
    public void setSlot4(String slot) {
        this.slot = slot;
    }
*/


