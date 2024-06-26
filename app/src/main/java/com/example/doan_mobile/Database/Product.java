package com.example.doan_mobile.Database;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int image;
    private String type;
    private double price;

    public Product() {
    }

    public Product(String name, int image, double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }


    public Product(int id, String name, int image, String type, double price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
    }

    public Product(String name, int image, String type, double price) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
