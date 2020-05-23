package com.example.admin.signup;

/**
 * Created by Admin on 27-10-2019.
 */

public class ParkingData7 extends UserProfile{
    //public String TDuration;
    public int hours1;
    public int mins1;
    public int seconds1;


    public ParkingData7() {
    }




    public ParkingData7(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo,
                        int hours1,int mins1,int seconds1){
        super(userMN,userName,userEmail,userPassword,userVehicleNo);
        this.hours1=hours1;
        this.mins1=mins1;
        this.seconds1=seconds1;



    }

    public int getHours1() {
        return hours1;
    }

    public void setHours1(int hours1) {
        this.hours1 = hours1;
    }

    public int getMins1() {
        return mins1;
    }

    public void setMins1(int mins1) {
        this.mins1 = mins1;
    }

    public int getSeconds1() {
        return seconds1;
    }

    public void setSeconds1(int seconds1) {
        this.seconds1 = seconds1;
    }
}


// public String getTDuration() { return TDuration; }

// public void setTDuration() {
//   this.TDuration = TDuration;






