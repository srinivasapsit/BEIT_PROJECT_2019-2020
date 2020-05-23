package com.example.admin.signup;

/**
 * Created by Admin on 09-04-2020.
 */

public class ParkingData9 extends UserProfile {
    public String mobileno;

    public ParkingData9(){

    }
    public ParkingData9(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo,String mobileno){
        this.mobileno=mobileno;

    }


    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}
