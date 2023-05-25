package com.example.may.Models;


public class UserStudent {
    //    private final String world;
    public String userName;
    public String mail;
    public String password;
    public String rollNum;
    public String roomNum;
    public String hostelName;
    public long contactNum;
    public String userType;

    public String OutPasses;
    public UserStudent( String rollNum,String userName, String mail, String password,long contactNum, String hostelName, String roomNum, String OutPasses) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.rollNum = rollNum;
        this.roomNum = roomNum;
        this.hostelName = hostelName;
        this.contactNum = contactNum;
        this.userType = "Student";
        this.OutPasses = OutPasses;
    }
    public UserStudent( String rollNum,String userName, String mail, String password,long contactNum, String hostelName, String roomNum) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.rollNum = rollNum;
        this.roomNum = roomNum;
        this.hostelName = hostelName;
        this.contactNum = contactNum;
        this.userType = "Student";

    }

    public UserStudent(){}

    //SignUp Constructor
    public UserStudent(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public long getContactNum() {
        return contactNum;
    }

    public void setContactNum(long contactNum) {
        this.contactNum = contactNum;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

//    public void setOutPasses(String outPasses) {
//        OutPasses = outPasses;
//    }
}
