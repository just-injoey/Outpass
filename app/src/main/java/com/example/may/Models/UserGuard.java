package com.example.may.Models;


public class UserGuard {
    public String userName;
    public String mail;
    public String password;
    public String userType;

    public UserGuard(){}

    //SignUp Constructor
    public UserGuard(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userType = "Guard";
    }

}
