package com.example.yourdevices.models;

import java.util.List;

public class Products {
    private String Proid,
            proName,
            proDescribtion,
            image,
            categoryID;
    private int quantity;
    private float price,
    discount,
    rate;
    public void setProid(String proid) {
        Proid = proid;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setProDescribtion(String proDescribtion) {
        this.proDescribtion = proDescribtion;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getProid() {
        return Proid;
    }

    public String getProName() {
        return proName;
    }

    public String getProDescribtion() {
        return proDescribtion;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public float getRate() {
        return rate;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public Products(String proid, String proName, String proDescribtion, int quantity, float price, float rate) {
        Proid = proid;
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.quantity = quantity;
        this.price = price;
        this.rate = rate;
    }

    public Products() {
    }

    public Products(String proName, String categoryID, int quantity, float price, float discount, float rate, String image) {
        this.proName = proName;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.rate = rate;
        this.image = image;
    }

    public Products(String proid, String proName, String proDescribtion, String categoryID, int quantity, float price, float discount, float rate, String image) {
        Proid = proid;
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.rate = rate;
        this.image = image;
    }

    public Products(String proid, String proName, String proDescribtion, int quantity, float price, float discount, float rate) {
        Proid = proid;
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.rate = rate;
    }
}
