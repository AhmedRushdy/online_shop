package com.example.yourdevices.models;

import java.util.List;

public class Delivary {
    private String id , name , phone , email;
    private List<String> ordersId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(List<String> ordersId) {
        this.ordersId = ordersId;
    }

    public Delivary(String name, String phone, String email, List<String> ordersId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.ordersId = ordersId;
    }

    public Delivary() {
    }

    public Delivary(String id, String name, String phone, String email, List<String> ordersId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.ordersId = ordersId;
    }
}
