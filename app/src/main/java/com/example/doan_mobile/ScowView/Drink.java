package com.example.doan_mobile.ScowView;

public class Drink {

    public static final int TYPE_COFFEE=1;
    public static final int TYPE_DRINKS =2;
    public static final int TYPE_CAKE =3;

    private int image;
    private String name;
    private int type;
    private Double price;
    private int star;

    public Drink(int image, String name, int type, Double price, int star) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.price = price;
        this.star = star;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
