package com.example.admin.signup;


/**
 * Created by Admin on 22-09-2019.
 */



/**
 * Created by Admin on 21-09-2019.
 */

public class ParkingData3 extends UserProfile {

    // public String vehicleType;
    // public String zone;
    public String stime;
    public String etime;
    public String date;
    //public String userEmail;
    //public String userPassword;

    /*public ParkingData(String userMN,String userName,String userEmail,String userPassword,
                       String vehicleType,String Zone,String slot){
        super(userMN,userName,userEmail,userPassword);
        this.vehicleType=vehicleType;
        this.Zone=Zone;
        this.slot=slot;

    }*/
    public ParkingData3(){

    }


    public ParkingData3(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo,
                        String stime,String etime,String date){
        super(userMN,userName,userEmail,userPassword,userVehicleNo);
        this.stime=stime;
        this.etime=etime;
        this.date=date;


    }
    public String getStime() { return stime; }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() { return etime; }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }



}
