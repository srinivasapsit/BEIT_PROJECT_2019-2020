package com.example.admin.signup;

/**
 * Created by Admin on 27-10-2019.
 */

public class ParkingData5 extends UserProfile{
    //public String TDuration;
    public int hours;
    public int mins;
    public int seconds;


    public ParkingData5() {
    }




    public ParkingData5(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo,
                        int hours,int mins,int seconds){
        super(userMN,userName,userEmail,userPassword,userVehicleNo);
        this.hours=hours;
        this.mins=mins;
        this.seconds=seconds;



    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}


   // public String getTDuration() { return TDuration; }

   // public void setTDuration() {
     //   this.TDuration = TDuration;






