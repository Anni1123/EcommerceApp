package com.example.coursebuy.Model;

public class Users {
    public Users() {
    }

    private String phone;
    private String name;

    public Users(String phone, String name, String password) {
        this.phone = phone;
        this.name = name;
        this.password = password;
    }

    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
