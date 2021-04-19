package com.example.yourdevices.models;

public class Users {
    private String userID;
    private String name;
    private String email;

    public Users(String userID, String name, String email, String avatar) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    public Users(String name, String email, String avatar) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    private String phone;
    private String address;
    private String avatar;
    private String favourite;
    private String cart;

    public Users() {
    }

    public Users(String userID, String name, String email, String phone, String address, String avatar) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
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

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getCart() {
        return cart;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

}
