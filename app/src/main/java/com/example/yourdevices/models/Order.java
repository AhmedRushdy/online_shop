package com.example.yourdevices.models;

import java.util.List;

public class Order {
    private String OID,
            lang,
            lat,
            state,
            payMethod;
    private float totalPrice;
    private List<String> productsID;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProductsID(List<String> productsID) {
        this.productsID = productsID;
    }

    public String getOID() {
        return OID;
    }

    public String getLang() {
        return lang;
    }

    public String getLat() {
        return lat;
    }

    public String getState() {
        return state;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public List<String> getProductsID() {
        return productsID;
    }

    public Order() {
    }

    public Order(String lang, String lat, String state, String payMethod, float totalPrice, List<String> productsID) {
        this.lang = lang;
        this.lat = lat;
        this.state = state;
        this.payMethod = payMethod;
        this.totalPrice = totalPrice;
        this.productsID = productsID;
    }
}
