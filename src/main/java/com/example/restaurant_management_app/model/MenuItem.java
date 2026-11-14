package com.example.restaurant_management_app.model;

import java.util.UUID;

public class MenuItem {

    //private static int counter=1;
    private String id;
    private String name;
    private double price;

    public MenuItem( String name, double price) {
       // this.id = String.valueOf(counter);
       // counter++;
        this.id= UUID.randomUUID().toString();

        this.name = name;
        this.price = price;
    }

    public MenuItem(){}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
