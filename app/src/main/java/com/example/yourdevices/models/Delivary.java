package com.example.yourdevices.models;

import java.util.List;

public class Delivary {
    private String uid , name, phone, email, orders;

    public Delivary() {
    }

    public Delivary(String uid, String name, String phone, String email, String orders) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.orders = orders;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
