package com.example.may.Models;

public class UserWarden {
    public String userName;
    public String mail;
    public String password;
    public String hostelName;
    public String userType;

    public UserWarden( String userName, String mail, String password, String hostelName) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.hostelName = hostelName;
        this.userType = "Warden";
    }

    public UserWarden(){}

    //SignUp Constructor
    public UserWarden(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

}

