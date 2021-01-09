package com.example.yourdevices.models;

public class Products {
    private String id;
    private String proName, proDescribtion, image;
    private int quantity;
    private float price;
    private float discount;

    public Products() {
    }


    public Products(String id, String proName, String proDescribtion, String image, float price) {
        this.id = id;
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.image = image;
        this.price = price;

    }
    public Products( String proName, String proDescribtion, String image, float price) {
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.image = image;
        this.price = price;

    }

    public Products(String id, String proName, String proDescribtion, String image, int quantity, float price, float discount) {
        this.id = id;
        this.proName = proName;
        this.proDescribtion = proDescribtion;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
