package com.example.yourdevices.models;

public class Users {
    private String userID;


    private String name;
    private String email;
    private String phone;

    public Users(String userID, String name, String email, String phone, String address, String avatar) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
    }

    private String address;
    private String avatar;

    public Users() {
    }
    public String getUserID() {
        return userID;
    }

    public void setNamed(String named) {
        this.name = named;
    }

    public void setEmail(String email) {
        email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public Users(String name,String address, String email, String phone, String avatar) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }
}
