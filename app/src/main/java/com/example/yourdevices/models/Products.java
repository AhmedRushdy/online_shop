package com.example.yourdevices.models;

import java.util.List;

public class Products {
    private String Proid,
            proName,
            proDescribtion,
            image,
            categoryID;
    private int quantity;
    private float price;
    private float discount;

    public Products() {
    }

    public Products(String proName, String proDescribtion, String image, float price, float discount) {
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.image = image;
        this.price = price;
        this.discount = discount;
    }

    public Products(String proid, String proName, String proDescribtion, String image, String categoryID, int quantity, float price, float discount) {
        Proid = proid;
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.image = image;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }
    public String getProid() {
        return Proid;
    }

    public void setProid(String proid) {
        Proid = proid;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDescribtion() {
        return proDescribtion;
    }

    public void setProDescribtion(String proDescribtion) {
        this.proDescribtion = proDescribtion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

}
