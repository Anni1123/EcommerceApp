package com.example.coursebuy.Model;

public class AdminOrders {
    private String pname;

    public AdminOrders(String pname, String phonr, String address, String city, String state, String date, String time, String totalamount) {
        this.pname = pname;
        this.phonr = phonr;
        this.address = address;
        this.city = city;
        this.state = state;
        this.date = date;
        this.time = time;
        this.totalamount = totalamount;
    }

    private String phonr;
    private String address;
    private String city;

    public AdminOrders() {
    }

    private String state;
    private String date;
    private String time;
    private String totalamount;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPhonr() {
        return phonr;
    }

    public void setPhonr(String phonr) {
        this.phonr = phonr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
