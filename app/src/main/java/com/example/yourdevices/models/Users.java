package com.example.yourdevices.models;

public class Users {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;

    public Users() {
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
