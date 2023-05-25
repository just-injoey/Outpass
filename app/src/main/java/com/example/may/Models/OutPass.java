package com.example.may.Models;

public class OutPass {
    //    public static int outpassCount=1000;  //removed by justin
//    public int outpassCount;
    public String oid;
    public String dateGenerated;
    public String outDate;
    public String outTime;
    public String inDate;
    public String inTime;
    public String studentName;
    public String towerName;
    public String toPlace;
    public String roomNum;
    public String approvedBy;
    public long contactNum;
    public OutPass(Long outpassCount,String dateGenerated,String outDate,String outTime,String inDate,String inTime,String studentName,String towerName,
                   String toPlace,String roomNum,String approvedBy,long contactNum){
        this.oid=String.valueOf(outpassCount);
        this.dateGenerated=dateGenerated;
        this.outDate=outDate;
        this.outTime=outTime;
        this.inDate=inDate;
        this.inTime=inTime;
        this.studentName=studentName;
        this.towerName=towerName;
        this.toPlace=toPlace;
        this.roomNum=roomNum;
        this.approvedBy=approvedBy;
        this.contactNum=contactNum;
//        outpassCount++; removed by justin
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDateGenerated() {
        return dateGenerated;
    }

    public void setDateGenerated(String dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public long getContactNum() {
        return contactNum;
    }

    public void setContactNum(long contactNum) {
        this.contactNum = contactNum;
    }
}