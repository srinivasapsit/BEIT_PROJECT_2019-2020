package com.example.admin.signup;

/**
 * Created by Admin on 19-09-2019.
 */

public class UserProfile {
    public String userMN;
    public String userName;
    public String userEmail;
    public String userPassword;
    public String userVehicleNo;

    public UserProfile(){

    }


public UserProfile(String userMN,String userName,String userEmail,String userPassword,String userVehicleNo){
    this.userMN=userMN;
    this.userName=userName;
    this.userEmail=userEmail;
    this.userPassword=userPassword;
    this.userVehicleNo=userVehicleNo;

}
    public String getUserMN() {
        return userMN;
    }

    public void setUserMN(String userMN) {
        this.userMN = userMN;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   public String getUserVehicleNo() {
        return userVehicleNo;
    }

    public void setUserVehicleNo(String userVehicleNo) {
        this.userVehicleNo = userVehicleNo;
    }

}


