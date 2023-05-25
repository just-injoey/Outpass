package com.example.may.Domain;

public class DomainOutpass {
    public String oid;
    public String dateGen;
    public String dateOut;
    public String timeOut;
    public String dateIn;
    public String timeIn;
    public String toPlace;
    public String status;

    public DomainOutpass(String oid, String dateGen, String dateOut, String timeOut, String dateIn, String timeIn, String toPlace, String status) {
        this.oid = oid;
        this.dateGen = dateGen;
        this.dateOut = dateOut;
        this.timeOut = timeOut;
        this.dateIn = dateIn;
        this.timeIn = timeIn;
        this.toPlace = toPlace;
        this.status = status;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDateGen() {
        return dateGen;
    }

    public void setDateGen(String dateGen) {
        this.dateGen = dateGen;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
